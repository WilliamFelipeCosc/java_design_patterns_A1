package atividade3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PainelControle implements Observer {
    private String nome;
    
    public PainelControle(String nome) {
        this.nome = nome;
    }
    
    @Override
    public void atualizar(String tipoSensor, String regiao, double valor) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        
        System.out.println("  [PAINEL: " + nome + "] " + timestamp + 
                         " | " + tipoSensor + " em " + regiao + ": " + 
                         formatarValor(tipoSensor, valor));
    }
    
    private String formatarValor(String tipo, double valor) {
        switch (tipo) {
            case "Temperatura":
                return String.format("%.1f°C", valor);
            case "Umidade":
                return String.format("%.1f%%", valor);
            case "Poluicao":
                return String.format("%.0f AQI", valor);
            default:
                return String.format("%.2f", valor);
        }
    }
}
