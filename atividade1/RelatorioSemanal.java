package atividade1;

public class RelatorioSemanal implements Relatorio {
    private String fonteDados;
    private String metricas;
    private String formato;
    
    @Override
    public void preparar() {
        this.fonteDados = "Data Warehouse - ultimos 7 dias + historico";
        this.metricas = "Tendencias, comparativos, eficiencia de rotas, custos";
        this.formato = "Formato detalhado - PDF com graficos";
        
        System.out.println("Preparando Relatorio Semanal...");
        System.out.println("  Fonte de dados: " + fonteDados);
        System.out.println("  Metricas priorizadas: " + metricas);
        System.out.println("  Formato: " + formato);
    }
    
    @Override
    public void gerar() {
        System.out.println("Gerando Relatorio Semanal...");
        System.out.println("  ===============================================");
        System.out.println("  RELATORIO OPERACIONAL SEMANAL");
        System.out.println("  Periodo: " + java.time.LocalDate.now().minusDays(6) + 
                         " a " + java.time.LocalDate.now());
        System.out.println("  -----------------------------------------------");
        System.out.println("  Total de entregas: 987");
        System.out.println("  Media diaria: 141 entregas");
        System.out.println("  Eficiencia de rotas: 89%");
        System.out.println("  Custo medio por entrega: R$ 12,50");
        System.out.println("  Tendencia: 5% em relacao a semana anterior");
        System.out.println("  ===============================================");
    }
    
    @Override
    public String getInfo() {
        return "Relatorio Semanal - Analise consolidada dos ultimos 7 dias";
    }
}
