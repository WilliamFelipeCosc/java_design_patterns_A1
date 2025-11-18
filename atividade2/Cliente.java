package atividade2;

public class Cliente {
    private String nome;
    private int idade;
    private double patrimonioLiquido;
    private double rendaMensal;
    private int experienciaInvestimentos;
    private boolean aceitaVolatilidade;
    
    public Cliente(String nome, int idade, double patrimonioLiquido, 
                   double rendaMensal, int experienciaInvestimentos, 
                   boolean aceitaVolatilidade) {
        this.nome = nome;
        this.idade = idade;
        this.patrimonioLiquido = patrimonioLiquido;
        this.rendaMensal = rendaMensal;
        this.experienciaInvestimentos = experienciaInvestimentos;
        this.aceitaVolatilidade = aceitaVolatilidade;
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public double getPatrimonioLiquido() {
        return patrimonioLiquido;
    }
    
    public double getRendaMensal() {
        return rendaMensal;
    }
    
    public int getExperienciaInvestimentos() {
        return experienciaInvestimentos;
    }
    
    public boolean isAceitaVolatilidade() {
        return aceitaVolatilidade;
    }
}
