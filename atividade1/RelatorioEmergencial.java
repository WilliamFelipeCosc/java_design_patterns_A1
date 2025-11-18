package atividade1;

public class RelatorioEmergencial implements Relatorio {
    private String fonteDados;
    private String metricas;
    private String formato;
    
    @Override
    public void preparar() {
        this.fonteDados = "Sistema de monitoramento em tempo real + GPS";
        this.metricas = "Incidentes criticos, atrasos severos, veiculos parados";
        this.formato = "Formato de alerta - Notificacao imediata";
        
        System.out.println("Preparando Relatorio Emergencial...");
        System.out.println("  Fonte de dados: " + fonteDados);
        System.out.println("  Metricas priorizadas: " + metricas);
        System.out.println("  Formato: " + formato);
    }
    
    @Override
    public void gerar() {
        System.out.println("Gerando Relatorio Emergencial...");
        System.out.println("  ===============================================");
        System.out.println("  ALERTA OPERACIONAL EMERGENCIAL");
        System.out.println("  Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("  -----------------------------------------------");
        System.out.println("  CRITICO: 3 veiculos parados ha mais de 2h");
        System.out.println("  ATENCAO: 12 entregas com atraso > 1h");
        System.out.println("  CRITICO: Acidente na Rota 7 - replanejamento necessario");
        System.out.println("  Acao imediata requerida");
        System.out.println("  ===============================================");
    }
    
    @Override
    public String getInfo() {
        return "Relatorio Emergencial - Alertas criticos em tempo real";
    }
}
