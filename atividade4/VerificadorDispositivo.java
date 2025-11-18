package atividade4;

import java.util.HashMap;
import java.util.Map;

public class VerificadorDispositivo extends VerificadorAntifraude {
    private static Map<String, DispositivoInfo> dispositivosConhecidos;
    
    static {
        dispositivosConhecidos = new HashMap<>();
        dispositivosConhecidos.put("DEV001", new DispositivoInfo(true, false, 100));
        dispositivosConhecidos.put("DEV002", new DispositivoInfo(true, false, 50));
        dispositivosConhecidos.put("DEV003", new DispositivoInfo(false, true, 5));
        dispositivosConhecidos.put("DEV004", new DispositivoInfo(true, false, 200));
    }
    
    public VerificadorDispositivo() {
        super("Deteccao de Dispositivo");
    }
    
    @Override
    protected ResultadoVerificacao executarVerificacao(Transacao transacao) {
        String dispositivoId = transacao.getDispositivoId();
        
        DispositivoInfo dispositivo = dispositivosConhecidos.get(dispositivoId);
        
        if (dispositivo == null) {
            System.out.println("    -> Alerta: Dispositivo novo detectado (" + 
                             dispositivoId + ")");
            
            if (transacao.getValor() > 5000) {
                return ResultadoVerificacao.reprovado(nomeVerificacao, 
                    "Dispositivo desconhecido com transacao de alto valor");
            }
            
            return ResultadoVerificacao.aprovado(nomeVerificacao);
        }
        
        if (dispositivo.bloqueado) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Dispositivo bloqueado: " + dispositivoId);
        }
        
        if (dispositivo.marcadoSuspeito) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Dispositivo marcado como suspeito");
        }
        
        if (dispositivo.usosAnteriores < 10) {
            System.out.println("    -> Alerta: Dispositivo com poucos usos (" + 
                             dispositivo.usosAnteriores + ")");
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
    
    private static class DispositivoInfo {
        boolean bloqueado;
        boolean marcadoSuspeito;
        int usosAnteriores;
        
        DispositivoInfo(boolean bloqueado, boolean marcadoSuspeito, int usosAnteriores) {
            this.bloqueado = bloqueado;
            this.marcadoSuspeito = marcadoSuspeito;
            this.usosAnteriores = usosAnteriores;
        }
    }
}
