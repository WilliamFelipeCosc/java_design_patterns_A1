package atividade4;

import java.util.Arrays;
import java.util.List;

public class VerificadorGeolocalizacao extends VerificadorAntifraude {
    private static final List<String> PAISES_ALTO_RISCO = Arrays.asList(
        "PaisX", "PaisY", "PaisZ"
    );
    
    private static final List<String> PAISES_BLOQUEADOS = Arrays.asList(
        "PaisBloqueado1", "PaisBloqueado2"
    );
    
    public VerificadorGeolocalizacao() {
        super("Analise de Geolocalizacao");
    }
    
    @Override
    protected ResultadoVerificacao executarVerificacao(Transacao transacao) {
        String pais = transacao.getPais();
        String localizacao = transacao.getLocalizacaoAtual();
        
        if (PAISES_BLOQUEADOS.contains(pais)) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Pais bloqueado: " + pais);
        }
        
        if (PAISES_ALTO_RISCO.contains(pais)) {
            System.out.println("    -> Alerta: Transacao de pais de alto risco (" + 
                             pais + "), requer atencao");
        }
        
        if (localizacao == null || localizacao.trim().isEmpty()) {
            return ResultadoVerificacao.reprovado(nomeVerificacao, 
                "Localizacao nao identificada");
        }
        
        return ResultadoVerificacao.aprovado(nomeVerificacao);
    }
}
