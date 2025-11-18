package atividade4;

import java.time.LocalDateTime;

public class Transacao {
    private String id;
    private double valor;
    private String localizacaoAtual;
    private String idUsuario;
    private String dispositivoId;
    private String pais;
    private LocalDateTime dataHora;
    
    public Transacao(String id, double valor, String localizacaoAtual, 
                     String idUsuario, String dispositivoId, String pais) {
        this.id = id;
        this.valor = valor;
        this.localizacaoAtual = localizacaoAtual;
        this.idUsuario = idUsuario;
        this.dispositivoId = dispositivoId;
        this.pais = pais;
        this.dataHora = LocalDateTime.now();
    }
    
    public String getId() {
        return id;
    }
    
    public double getValor() {
        return valor;
    }
    
    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }
    
    public String getDispositivoId() {
        return dispositivoId;
    }
    
    public String getPais() {
        return pais;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    @Override
    public String toString() {
        return "Transacao [ID: " + id + ", Valor: R$ " + 
               String.format("%.2f", valor) + ", Usuario: " + idUsuario + 
               ", Pais: " + pais + "]";
    }
}
