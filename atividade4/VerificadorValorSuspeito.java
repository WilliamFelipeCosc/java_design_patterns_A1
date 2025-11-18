package atividade4;

public class VerificadorValorSuspeito extends VerificadorAntifraude {
    private static final double LIMITE_VALOR_ALTO = 10000.0;
    private static final double LIMITE_VALOR_MUITO_ALTO = 50000.0;
    
    public VerificadorValorSuspeito() {
        super("Analise de Valor");
    }
    
    @Override
    protected ResultadoVerificacao executarVerificacao(Transacao transacao) {
        double valor = transacao.getValor();
        
        if (valor <= 0) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Valor invalido: R$ " + valor);
        }
        
        if (valor > LIMITE_VALOR_MUITO_ALTO) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Valor extremamente alto: R$ " + String.format("%.2f", valor) + 
                " (limite: R$ " + LIMITE_VALOR_MUITO_ALTO + ")");
        }
        
        if (valor > LIMITE_VALOR_ALTO) {
            System.out.println("    -> Alerta: Valor alto detectado (R$ " + 
                             String.format("%.2f", valor) + "), mas dentro do limite aceitavel");
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
}
