package atividade2;

public class ModeloConservador implements EstrategiaCalculoRisco {
    
    @Override
    public PerfilRisco calcularPerfil(Cliente cliente) {
        double pontuacao = 0.0;
        
        int anosParaAposentadoria = 65 - cliente.getIdade();
        if (anosParaAposentadoria > 20) {
            pontuacao += 25;
        } else if (anosParaAposentadoria > 10) {
            pontuacao += 15;
        } else {
            pontuacao += 5;
        }
        
        if (cliente.getPatrimonioLiquido() > 2000000) {
            pontuacao += 20;
        } else if (cliente.getPatrimonioLiquido() > 500000) {
            pontuacao += 10;
        } else {
            pontuacao += 5;
        }
        
        if (cliente.getRendaMensal() > 30000) {
            pontuacao += 15;
        } else if (cliente.getRendaMensal() > 15000) {
            pontuacao += 10;
        } else {
            pontuacao += 5;
        }
        
        pontuacao += cliente.getExperienciaInvestimentos() * 2;
        
        if (!cliente.isAceitaVolatilidade()) {
            pontuacao -= 10;
        }
        
        if (pontuacao < 0) pontuacao = 0;
        
        String classificacao = determinarClassificacao(pontuacao);
        String recomendacoes = gerarRecomendacoes(classificacao);
        
        return new PerfilRisco(classificacao, pontuacao, recomendacoes, "Modelo Conservador");
    }
    
    private String determinarClassificacao(double pontuacao) {
        if (pontuacao >= 50) {
            return "MODERADO";
        } else if (pontuacao >= 30) {
            return "CONSERVADOR";
        } else if (pontuacao >= 15) {
            return "MUITO CONSERVADOR";
        } else {
            return "ULTRA CONSERVADOR";
        }
    }
    
    private String gerarRecomendacoes(String classificacao) {
        switch (classificacao) {
            case "MODERADO":
                return "60% renda fixa, 30% acoes blue chip, 10% fundos";
            case "CONSERVADOR":
                return "80% renda fixa, 15% acoes defensivas, 5% fundos";
            case "MUITO CONSERVADOR":
                return "90% renda fixa (CDB, Tesouro), 10% acoes defensivas";
            default:
                return "100% renda fixa com liquidez diaria (Tesouro Selic, CDB)";
        }
    }
}
