package atividade5;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("        SISTEMA CORPORATIVO DE LOGGING");
        System.out.println("            Design Pattern: Singleton");
        System.out.println("====================================================\n");
        
        System.out.println("=== DEMONSTRACAO 1: Instancia Unica ===\n");
        
        System.out.println("Tentando obter primeira instancia...");
        GerenciadorLog logger1 = GerenciadorLog.getInstancia();
        System.out.println("Instancia 1 obtida: " + logger1.hashCode());
        
        System.out.println("\nTentando obter segunda instancia...");
        GerenciadorLog logger2 = GerenciadorLog.getInstancia();
        System.out.println("Instancia 2 obtida: " + logger2.hashCode());
        
        System.out.println("\nTentando obter terceira instancia...");
        GerenciadorLog logger3 = GerenciadorLog.getInstancia();
        System.out.println("Instancia 3 obtida: " + logger3.hashCode());
        
        System.out.println("\nVerificacao: Todas as referencias apontam para mesma instancia?");
        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("logger2 == logger3: " + (logger2 == logger3));
        System.out.println("logger1 == logger3: " + (logger1 == logger3));
        
        System.out.println("\n=== DEMONSTRACAO 2: Acesso Global ===\n");
        
        GerenciadorLog logger = GerenciadorLog.getInstancia();
        
        logger.info("Sistema iniciado", "Main");
        logger.evento("Usuario admin fez login", "Autenticacao");
        logger.auditoria("Consulta ao banco de dados", "DAO", "SELECT * FROM usuarios");
        logger.erro("Falha na conexao", "DatabaseService", "Timeout apos 30 segundos");
        logger.aviso("Memoria em 85%", "MonitorRecursos", "Considerar escalonamento");
        
        logger.exibirEstatisticas();
        
        System.out.println("\n=== DEMONSTRACAO 3: Thread-Safety (Multi-Threading) ===\n");
        
        System.out.println("Criando 5 threads concorrentes...\n");
        
        List<Thread> threads = new ArrayList<>();
        
        Thread t1 = new Thread(new TarefaSimulacao("ModuloVendas", 5));
        Thread t2 = new Thread(new TarefaSimulacao("ModuloEstoque", 5));
        Thread t3 = new Thread(new TarefaSimulacao("ModuloFinanceiro", 5));
        Thread t4 = new Thread(new TarefaSimulacao("ModuloRH", 5));
        Thread t5 = new Thread(new TarefaSimulacao("ModuloRelatorios", 5));
        
        threads.add(t1);
        threads.add(t2);
        threads.add(t3);
        threads.add(t4);
        threads.add(t5);
        
        for (Thread thread : threads) {
            thread.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrompida");
            }
        }
        
        System.out.println("\n[INFO] Todas as threads finalizaram\n");
        
        logger.exibirEstatisticas();
        
        System.out.println("\n=== DEMONSTRACAO 4: Configuracao Global ===\n");
        
        System.out.println("Desativando envio para servidor externo...");
        logger.setServidorExterno(false);
        
        logger.info("Log apos desativar servidor", "ConfigTest");
        logger.evento("Outro evento de teste", "ConfigTest");
        
        System.out.println("\nReativando envio para servidor externo...");
        logger.setServidorExterno(true);
        
        logger.info("Log apos reativar servidor", "ConfigTest");
        
        System.out.println("\n=== DEMONSTRACAO 5: Simulacao de Aplicacao Real ===\n");
        
        simularFluxoAplicacao();
        
        logger.exibirEstatisticas();
        
        logger.finalizar();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("VANTAGENS DO SINGLETON:");
        System.out.println("=".repeat(60));
        System.out.println("1. Instancia unica garantida");
        System.out.println("2. Acesso global de qualquer ponto da aplicacao");
        System.out.println("3. Thread-safe (com Double-Checked Locking)");
        System.out.println("4. Inicializacao tardia (lazy initialization)");
        System.out.println("5. Previne inconsistencias de multiplas instancias");
        System.out.println("6. Controle centralizado de recursos");
        System.out.println("=".repeat(60));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("IMPLEMENTACAO:");
        System.out.println("=".repeat(60));
        System.out.println("- Construtor privado");
        System.out.println("- Variavel estatica volatile");
        System.out.println("- Double-Checked Locking com ReentrantLock");
        System.out.println("- Thread-safe para ambientes multi-thread");
        System.out.println("- Metodos sincronizados para operacoes criticas");
        System.out.println("=".repeat(60));
    }
    
    private static void simularFluxoAplicacao() {
        GerenciadorLog logger = GerenciadorLog.getInstancia();
        
        logger.info("Iniciando processamento de pedido", "ServicoVendas");
        logger.auditoria("Pedido #12345 criado", "ServicoVendas", "Usuario: joao.silva");
        
        logger.info("Verificando estoque", "ServicoEstoque");
        logger.evento("Produto disponivel", "ServicoEstoque");
        
        logger.info("Processando pagamento", "ServicoFinanceiro");
        logger.auditoria("Pagamento aprovado", "ServicoFinanceiro", "Valor: R$ 1.500,00");
        
        logger.info("Atualizando estoque", "ServicoEstoque");
        logger.evento("Estoque atualizado", "ServicoEstoque");
        
        logger.info("Enviando notificacao", "ServicoNotificacao");
        logger.evento("Email enviado ao cliente", "ServicoNotificacao");
        
        logger.info("Pedido finalizado com sucesso", "ServicoVendas");
        logger.auditoria("Pedido #12345 concluido", "ServicoVendas", "Status: CONCLUIDO");
    }
}
