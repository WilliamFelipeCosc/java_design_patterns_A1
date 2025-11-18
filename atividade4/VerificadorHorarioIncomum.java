package atividade4;

import java.time.LocalTime;

public class VerificadorHorarioIncomum extends VerificadorAntifraude {
    private static final int HORA_INICIO_SUSPEITO = 2;
    private static final int HORA_FIM_SUSPEITO = 5;
    
    public VerificadorHorarioIncomum() {
        super("Analise de Horario");
    }
    
    @Override
    protected ResultadoVerificacao executarVerificacao(Transacao transacao) {
        LocalTime horario = transacao.getDataHora().toLocalTime();
        int hora = horario.getHour();
        
        if (hora >= HORA_INICIO_SUSPEITO && hora <= HORA_FIM_SUSPEITO) {
            if (transacao.getValor() > 5000) {
                return ResultadoVerificacao.reprovado(nomeVerificacao, 
                    "Transacao de alto valor em horario suspeito (madrugada)");
            }
            
            System.out.println("    -> Alerta: Transacao em horario incomum (" + 
                             horario.getHour() + "h)");
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
}
