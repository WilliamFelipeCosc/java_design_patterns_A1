package atividade3;

import java.util.HashMap;
import java.util.Map;

public class ModuloEstatisticas implements Observer {
    private Map<String, DadosEstatisticos> estatisticas;
    
    public ModuloEstatisticas() {
        this.estatisticas = new HashMap<>();
    }
    
    @Override
    public void atualizar(String tipoSensor, String regiao, double valor) {
        String chave = tipoSensor + "-" + regiao;
        
        DadosEstatisticos dados = estatisticas.get(chave);
        if (dados == null) {
            dados = new DadosEstatisticos();
            estatisticas.put(chave, dados);
        }
        
        dados.adicionarLeitura(valor);
        
        System.out.println("  [ESTATISTICAS] " + tipoSensor + " em " + regiao + 
                         " - Media: " + String.format("%.2f", dados.getMedia()) + 
                         " | Min: " + dados.getMinimo() + 
                         " | Max: " + dados.getMaximo() + 
                         " | Leituras: " + dados.getNumeroLeituras());
    }
    
    public void exibirResumo() {
        System.out.println("\n=== RESUMO ESTATISTICO ===");
        for (Map.Entry<String, DadosEstatisticos> entry : estatisticas.entrySet()) {
            String chave = entry.getKey();
            DadosEstatisticos dados = entry.getValue();
            System.out.println(chave + ":");
            System.out.println("  Media: " + String.format("%.2f", dados.getMedia()));
            System.out.println("  Minimo: " + dados.getMinimo());
            System.out.println("  Maximo: " + dados.getMaximo());
            System.out.println("  Total de leituras: " + dados.getNumeroLeituras());
        }
        System.out.println("==========================\n");
    }
    
    private static class DadosEstatisticos {
        private double soma;
        private double minimo;
        private double maximo;
        private int numeroLeituras;
        
        public DadosEstatisticos() {
            this.soma = 0.0;
            this.minimo = Double.MAX_VALUE;
            this.maximo = Double.MIN_VALUE;
            this.numeroLeituras = 0;
        }
        
        public void adicionarLeitura(double valor) {
            soma += valor;
            numeroLeituras++;
            if (valor < minimo) minimo = valor;
            if (valor > maximo) maximo = valor;
        }
        
        public double getMedia() {
            return numeroLeituras > 0 ? soma / numeroLeituras : 0.0;
        }
        
        public double getMinimo() {
            return minimo;
        }
        
        public double getMaximo() {
            return maximo;
        }
        
        public int getNumeroLeituras() {
            return numeroLeituras;
        }
    }
}
