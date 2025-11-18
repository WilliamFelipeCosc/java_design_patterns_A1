package atividade3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroHistorico implements Observer {
    private List<String> historico;
    private int capacidadeMaxima;
    
    public RegistroHistorico(int capacidadeMaxima) {
        this.historico = new ArrayList<>();
        this.capacidadeMaxima = capacidadeMaxima;
    }
    
    @Override
    public void atualizar(String tipoSensor, String regiao, double valor) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        String registro = timestamp + " | " + tipoSensor + " | " + regiao + " | " + valor;
        historico.add(registro);
        
        if (historico.size() > capacidadeMaxima) {
            historico.remove(0);
        }
        
        System.out.println("  [HISTORICO] Registro salvo: " + tipoSensor + 
                         " em " + regiao + " = " + valor + 
                         " (Total: " + historico.size() + " registros)");
    }
    
    public void exibirHistorico() {
        System.out.println("\n=== HISTORICO DE REGISTROS ===");
        if (historico.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            for (int i = 0; i < historico.size(); i++) {
                System.out.println((i + 1) + ". " + historico.get(i));
            }
        }
        System.out.println("==============================\n");
    }
    
    public int getTotalRegistros() {
        return historico.size();
    }
}
