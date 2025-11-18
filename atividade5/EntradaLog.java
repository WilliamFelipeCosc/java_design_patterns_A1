package atividade5;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaLog {
    private final TipoLog tipo;
    private final String mensagem;
    private final LocalDateTime timestamp;
    private final String origem;
    private final String detalhes;
    
    public EntradaLog(TipoLog tipo, String mensagem, String origem, String detalhes) {
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.origem = origem;
        this.detalhes = detalhes;
        this.timestamp = LocalDateTime.now();
    }
    
    public TipoLog getTipo() {
        return tipo;
    }
    
    public String getMensagem() {
        return mensagem;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getOrigem() {
        return origem;
    }
    
    public String getDetalhes() {
        return detalhes;
    }
    
    public String formatarParaArquivo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return String.format("[%s] [%s] [%s] %s - %s",
            timestamp.format(formatter),
            tipo,
            origem,
            mensagem,
            detalhes != null ? detalhes : "");
    }
    
    @Override
    public String toString() {
        return formatarParaArquivo();
    }
}
