package atividade4;

public abstract class VerificadorAntifraude {
    protected VerificadorAntifraude proximo;
    protected String nomeVerificacao;
    
    public VerificadorAntifraude(String nomeVerificacao) {
        this.nomeVerificacao = nomeVerificacao;
    }
    
    public void setProximo(VerificadorAntifraude proximo) {
        this.proximo = proximo;
    }
    
    public ResultadoVerificacao verificar(Transacao transacao) {
        System.out.println("  [" + nomeVerificacao + "] Verificando...");
        
        ResultadoVerificacao resultado = executarVerificacao(transacao);
        
        if (!resultado.isAprovado()) {
            System.out.println("  [" + nomeVerificacao + "] REPROVADO: " + 
                             resultado.getMotivo());
            return resultado;
        }
        
        System.out.println("  [" + nomeVerificacao + "] Aprovado");
        
        if (proximo != null) {
            return proximo.verificar(transacao);
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
    
    protected abstract ResultadoVerificacao executarVerificacao(Transacao transacao);
}
