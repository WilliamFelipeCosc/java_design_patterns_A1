package atividade5;

import java.util.Random;

public class TarefaSimulacao implements Runnable {
    private final String nomeModulo;
    private final int numeroOperacoes;
    private final Random random;
    
    public TarefaSimulacao(String nomeModulo, int numeroOperacoes) {
        this.nomeModulo = nomeModulo;
        this.numeroOperacoes = numeroOperacoes;
        this.random = new Random();
    }
    
    @Override
    public void run() {
        GerenciadorLog logger = GerenciadorLog.getInstancia();
        
        logger.info("Modulo iniciado", nomeModulo);
        
        for (int i = 0; i < numeroOperacoes; i++) {
            simularOperacao(logger, i + 1);
            
            try {
                Thread.sleep(random.nextInt(50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        logger.info("Modulo finalizado", nomeModulo);
    }
    
    private void simularOperacao(GerenciadorLog logger, int numeroOp) {
        int tipo = random.nextInt(100);
        
        if (tipo < 10) {
            logger.erro("Erro simulado na operacao " + numeroOp, 
                       nomeModulo, 
                       "Stack trace simulado");
        } else if (tipo < 30) {
            logger.aviso("Aviso na operacao " + numeroOp, 
                        nomeModulo, 
                        "Situacao que requer atencao");
        } else if (tipo < 50) {
            logger.auditoria("Acao auditada na operacao " + numeroOp, 
                           nomeModulo, 
                           "Usuario: user" + random.nextInt(100));
        } else {
            logger.evento("Evento normal na operacao " + numeroOp, nomeModulo);
        }
    }
}
