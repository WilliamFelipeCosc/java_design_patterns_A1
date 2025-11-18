package atividade1;

public class RelatorioDiario implements Relatorio {
    private String fonteDados;
    private String metricas;
    private String formato;
    
    @Override
    public void preparar() {
        this.fonteDados = "Banco de dados operacional - ultimas 24h";
        this.metricas = "Entregas realizadas, tempo medio, pendencias";
        this.formato = "Formato resumido - Dashboard";
        
        System.out.println("Preparando Relatorio Diario...");
        System.out.println("  Fonte de dados: " + fonteDados);
        System.out.println("  Metricas priorizadas: " + metricas);
        System.out.println("  Formato: " + formato);
    }
    
    @Override
    public void gerar() {
        System.out.println("Gerando Relatorio Diario...");
        System.out.println("  ===================================");
        System.out.println("  RELATORIO OPERACIONAL DIARIO");
        System.out.println("  Data: " + java.time.LocalDate.now());
        System.out.println("  -----------------------------------");
        System.out.println("  Total de entregas: 142");
        System.out.println("  Tempo medio: 45 min");
        System.out.println("  Pendencias: 8");
        System.out.println("  ===================================");
    }
    
    @Override
    public String getInfo() {
        return "Relatorio Diario - Operacoes das ultimas 24 horas";
    }
}
