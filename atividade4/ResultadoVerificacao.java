package atividade4;

public class ResultadoVerificacao {
    private boolean aprovado;
    private String motivo;
    private String etapa;
    
    public ResultadoVerificacao(boolean aprovado, String motivo, String etapa) {
        this.aprovado = aprovado;
        this.motivo = motivo;
        this.etapa = etapa;
    }
    
    public boolean isAprovado() {
        return aprovado;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public String getEtapa() {
        return etapa;
    }
    
    public static ResultadoVerificacao aprovado(String etapa) {
        return new ResultadoVerificacao(true, "Verificacao aprovada", etapa);
    }
    
    public static ResultadoVerificacao reprovado(String etapa, String motivo) {
        return new ResultadoVerificacao(false, motivo, etapa);
    }
}
