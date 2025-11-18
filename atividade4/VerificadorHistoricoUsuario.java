package atividade4;

import java.util.HashMap;
import java.util.Map;

public class VerificadorHistoricoUsuario extends VerificadorAntifraude {
    private static Map<String, HistoricoUsuario> bancoDados;
    
    static {
        bancoDados = new HashMap<>();
        bancoDados.put("USR001", new HistoricoUsuario(true, 50, 0));
        bancoDados.put("USR002", new HistoricoUsuario(true, 120, 2));
        bancoDados.put("USR003", new HistoricoUsuario(false, 5, 0));
        bancoDados.put("USR004", new HistoricoUsuario(true, 80, 5));
        bancoDados.put("USR005", new HistoricoUsuario(false, 0, 0));
    }
    
    public VerificadorHistoricoUsuario() {
        super("Verificacao de Historico");
    }
    
    @Override
    protected ResultadoVerificacao executarVerificacao(Transacao transacao) {
        String idUsuario = transacao.getIdUsuario();
        
        HistoricoUsuario historico = bancoDados.get(idUsuario);
        
        if (historico == null) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Usuario nao encontrado: " + idUsuario);
        }
        
        if (!historico.contaVerificada) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Conta nao verificada");
        }
        
        if (historico.transacoesAnteriores < 3) {
            System.out.println("    -> Alerta: Usuario novo (apenas " + 
                             historico.transacoesAnteriores + " transacoes anteriores)");
        }
        
        if (historico.fradesDetectadas > 3) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Historico de fraudes: " + historico.fradesDetectadas + " casos");
        }
        
        if (historico.fradesDetectadas > 0) {
            System.out.println("    -> Alerta: Usuario com " + 
                             historico.fradesDetectadas + " fraude(s) anteriore(s)");
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
    
    private static class HistoricoUsuario {
        boolean contaVerificada;
        int transacoesAnteriores;
        int fradesDetectadas;
        
        HistoricoUsuario(boolean contaVerificada, int transacoesAnteriores, 
                        int fradesDetectadas) {
            this.contaVerificada = contaVerificada;
            this.transacoesAnteriores = transacoesAnteriores;
            this.fradesDetectadas = fradesDetectadas;
        }
    }
}
