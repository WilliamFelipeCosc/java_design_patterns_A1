package atividade2;

public class ModeloAgressivo implements EstrategiaCalculoRisco {
    
    @Override
    public PerfilRisco calcularPerfil(Cliente cliente) {
        double pontuacao = 0.0;
        
        if (cliente.getIdade() < 30) {
            pontuacao += 40;
        } else if (cliente.getIdade() < 45) {
            pontuacao += 25;
        } else {
            pontuacao += 10;
        }
        
        if (cliente.getPatrimonioLiquido() > 1000000) {
            pontuacao += 30;
        } else if (cliente.getPatrimonioLiquido() > 500000) {
            pontuacao += 20;
        } else {
            pontuacao += 5;
        }
        
        pontuacao += cliente.getExperienciaInvestimentos() * 5;
        
        if (cliente.isAceitaVolatilidade()) {
            pontuacao += 20;
        }
        
        String classificacao = determinarClassificacao(pontuacao);
        String recomendacoes = gerarRecomendacoes(classificacao);
        
        return new PerfilRisco(classificacao, pontuacao, recomendacoes, "Modelo Agressivo");
    }
    
    private String determinarClassificacao(double pontuacao) {
        if (pontuacao >= 80) {
            return "SUPER AGRESSIVO";
        } else if (pontuacao >= 60) {
            return "AGRESSIVO";
        } else if (pontuacao >= 40) {
            return "MODERADO AGRESSIVO";
        } else {
            return "MODERADO";
        }
    }
    
    private String gerarRecomendacoes(String classificacao) {
        switch (classificacao) {
            case "SUPER AGRESSIVO":
                return "80-100% acoes growth, startups, criptomoedas";
            case "AGRESSIVO":
                return "60-80% acoes, fundos multimercado agressivos";
            case "MODERADO AGRESSIVO":
                return "40-60% acoes, 40-60% renda fixa";
            default:
                return "Maior parte em renda fixa com alguma exposicao a acoes";
        }
    }
}
