package atividade2;

public class PerfilRisco {
    private String classificacao;
    private double pontuacao;
    private String recomendacoes;
    private String modeloUtilizado;
    
    public PerfilRisco(String classificacao, double pontuacao, 
                       String recomendacoes, String modeloUtilizado) {
        this.classificacao = classificacao;
        this.pontuacao = pontuacao;
        this.recomendacoes = recomendacoes;
        this.modeloUtilizado = modeloUtilizado;
    }
    
    public String getClassificacao() {
        return classificacao;
    }
    
    public double getPontuacao() {
        return pontuacao;
    }
    
    public String getRecomendacoes() {
        return recomendacoes;
    }
    
    public String getModeloUtilizado() {
        return modeloUtilizado;
    }
    
    public void exibir() {
        System.out.println("  Modelo Utilizado: " + modeloUtilizado);
        System.out.println("  Classificacao: " + classificacao);
        System.out.println("  Pontuacao de Risco: " + String.format("%.2f", pontuacao));
        System.out.println("  Recomendacoes: " + recomendacoes);
    }
}
