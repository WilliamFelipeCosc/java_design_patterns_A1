package atividade4;

public class ProcessadorPagamento {
    private VerificadorAntifraude primeiroVerificador;
    
    public ProcessadorPagamento(VerificadorAntifraude primeiroVerificador) {
        this.primeiroVerificador = primeiroVerificador;
    }
    
    public void setPrimeiroVerificador(VerificadorAntifraude primeiroVerificador) {
        this.primeiroVerificador = primeiroVerificador;
    }
    
    public boolean processarTransacao(Transacao transacao) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Processando: " + transacao);
        System.out.println("=".repeat(60));
        
        if (primeiroVerificador == null) {
            System.out.println("ERRO: Nenhuma verificacao configurada!");
            return false;
        }
        
        ResultadoVerificacao resultado = primeiroVerificador.verificar(transacao);
        
        System.out.println("-".repeat(60));
        
        if (resultado.isAprovado()) {
            System.out.println("STATUS: TRANSACAO APROVADA");
            System.out.println("Todas as verificacoes passaram com sucesso!");
            System.out.println("=".repeat(60));
            return true;
        } else {
            System.out.println("STATUS: TRANSACAO BLOQUEADA");
            System.out.println("Etapa que reprovou: " + resultado.getEtapa());
            System.out.println("Motivo: " + resultado.getMotivo());
            System.out.println("=".repeat(60));
            return false;
        }
    }
}
