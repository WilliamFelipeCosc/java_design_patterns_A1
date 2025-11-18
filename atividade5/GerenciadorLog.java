package atividade5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GerenciadorLog {
    
    private static volatile GerenciadorLog instancia;
    
    private static final Lock lock = new ReentrantLock();
    
    private final String caminhoArquivo;
    private final List<EntradaLog> bufferLogs;
    private final int tamanhoBuffer;
    private boolean servidorExterno;
    private int totalLogsRegistrados;
    private final DateTimeFormatter formatter;
    
    private GerenciadorLog() {
        this.caminhoArquivo = "application.log";
        this.bufferLogs = new ArrayList<>();
        this.tamanhoBuffer = 10;
        this.servidorExterno = true;
        this.totalLogsRegistrados = 0;
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("[SINGLETON] Instancia do GerenciadorLog criada.");
        System.out.println("[SINGLETON] Thread: " + Thread.currentThread().getName());
        System.out.println("[SINGLETON] Timestamp: " + LocalDateTime.now().format(formatter));
        inicializarArquivo();
    }
    
    public static GerenciadorLog getInstancia() {
        if (instancia == null) {
            lock.lock();
            try {
                if (instancia == null) {
                    instancia = new GerenciadorLog();
                }
            } finally {
                lock.unlock();
            }
        }
        return instancia;
    }
    
    private void inicializarArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
            writer.write("=".repeat(80));
            writer.newLine();
            writer.write("SISTEMA DE LOGGING CENTRALIZADO - Inicializado em " + 
                        LocalDateTime.now().format(formatter));
            writer.newLine();
            writer.write("=".repeat(80));
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("[ERRO] Falha ao inicializar arquivo de log: " + e.getMessage());
        }
    }
    
    public void registrarLog(TipoLog tipo, String mensagem, String origem, String detalhes) {
        lock.lock();
        try {
            EntradaLog entrada = new EntradaLog(tipo, mensagem, origem, detalhes);
            bufferLogs.add(entrada);
            totalLogsRegistrados++;
            
            System.out.println("[LOG] " + entrada.formatarParaArquivo());
            
            if (bufferLogs.size() >= tamanhoBuffer) {
                flush();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public void registrarLog(TipoLog tipo, String mensagem, String origem) {
        registrarLog(tipo, mensagem, origem, null);
    }
    
    public void erro(String mensagem, String origem, String detalhes) {
        registrarLog(TipoLog.ERRO, mensagem, origem, detalhes);
    }
    
    public void evento(String mensagem, String origem) {
        registrarLog(TipoLog.EVENTO, mensagem, origem, null);
    }
    
    public void auditoria(String mensagem, String origem, String detalhes) {
        registrarLog(TipoLog.AUDITORIA, mensagem, origem, detalhes);
    }
    
    public void info(String mensagem, String origem) {
        registrarLog(TipoLog.INFO, mensagem, origem, null);
    }
    
    public void aviso(String mensagem, String origem, String detalhes) {
        registrarLog(TipoLog.AVISO, mensagem, origem, detalhes);
    }
    
    public void flush() {
        if (bufferLogs.isEmpty()) {
            return;
        }
        
        gravarEmArquivo();
        
        if (servidorExterno) {
            enviarParaServidorExterno();
        }
        
        bufferLogs.clear();
    }
    
    private void gravarEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            for (EntradaLog entrada : bufferLogs) {
                writer.write(entrada.formatarParaArquivo());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("[ERRO] Falha ao gravar logs em arquivo: " + e.getMessage());
        }
    }
    
    private void enviarParaServidorExterno() {
        System.out.println("[SERVIDOR] Enviando " + bufferLogs.size() + 
                         " logs para servidor de monitoramento...");
        
        try {
            Thread.sleep(100);
            System.out.println("[SERVIDOR] Logs enviados com sucesso!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[ERRO] Falha ao enviar logs para servidor externo");
        }
    }
    
    public void setServidorExterno(boolean ativo) {
        lock.lock();
        try {
            this.servidorExterno = ativo;
            System.out.println("[CONFIG] Envio para servidor externo: " + 
                             (ativo ? "ATIVO" : "INATIVO"));
        } finally {
            lock.unlock();
        }
    }
    
    public int getTotalLogsRegistrados() {
        lock.lock();
        try {
            return totalLogsRegistrados;
        } finally {
            lock.unlock();
        }
    }
    
    public int getLogsNoBuffer() {
        lock.lock();
        try {
            return bufferLogs.size();
        } finally {
            lock.unlock();
        }
    }
    
    public void exibirEstatisticas() {
        lock.lock();
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ESTATISTICAS DO GERENCIADOR DE LOG");
            System.out.println("=".repeat(60));
            System.out.println("Total de logs registrados: " + totalLogsRegistrados);
            System.out.println("Logs no buffer atual: " + bufferLogs.size());
            System.out.println("Tamanho do buffer: " + tamanhoBuffer);
            System.out.println("Arquivo de log: " + caminhoArquivo);
            System.out.println("Servidor externo: " + (servidorExterno ? "ATIVO" : "INATIVO"));
            System.out.println("=".repeat(60) + "\n");
        } finally {
            lock.unlock();
        }
    }
    
    public void finalizar() {
        lock.lock();
        try {
            System.out.println("\n[FINALIZACAO] Finalizando GerenciadorLog...");
            flush();
            System.out.println("[FINALIZACAO] Total de logs processados: " + totalLogsRegistrados);
        } finally {
            lock.unlock();
        }
    }
}
