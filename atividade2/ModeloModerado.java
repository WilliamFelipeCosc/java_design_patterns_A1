package atividade2;

public class ModeloModerado implements EstrategiaCalculoRisco {
    
    @Override
    public PerfilRisco calcularPerfil(Cliente cliente) {
        double pontuacao = 0.0;
        
        if (cliente.getIdade() < 35) {
            pontuacao += 30;
        } else if (cliente.getIdade() < 50) {
            pontuacao += 20;
        } else {
            pontuacao += 10;
        }
        
        double ratioPatrimonio = cliente.getPatrimonioLiquido() / 
                                (cliente.getRendaMensal() * 12);
        if (ratioPatrimonio > 10) {
            pontuacao += 25;
        } else if (ratioPatrimonio > 5) {
            pontuacao += 15;
        } else {
            pontuacao += 5;
        }
        
        pontuacao += cliente.getExperienciaInvestimentos() * 3;
        
        if (cliente.isAceitaVolatilidade()) {
            pontuacao += 15;
        } else {
            pontuacao += 5;
        }
        
        if (cliente.getRendaMensal() > 20000) {
            pontuacao += 10;
        }
        
        String classificacao = determinarClassificacao(pontuacao);
        String recomendacoes = gerarRecomendacoes(classificacao);
        
        return new PerfilRisco(classificacao, pontuacao, recomendacoes, "Modelo Moderado");
    }
    
    private String determinarClassificacao(double pontuacao) {
        if (pontuacao >= 70) {
            return "MODERADO AGRESSIVO";
        } else if (pontuacao >= 50) {
            return "MODERADO";
        } else if (pontuacao >= 30) {
            return "MODERADO CONSERVADOR";
        } else {
            return "CONSERVADOR";
        }
    }
    
    private String gerarRecomendacoes(String classificacao) {
        switch (classificacao) {
            case "MODERADO AGRESSIVO":
                return "50% acoes blue chip, 30% renda fixa, 20% fundos";
            case "MODERADO":
                return "40% acoes, 40% renda fixa, 20% fundos imobiliarios";
            case "MODERADO CONSERVADOR":
                return "30% acoes, 60% renda fixa, 10% fundos";
            default:
                return "80% renda fixa, 20% acoes defensivas";
        }
    }
}
