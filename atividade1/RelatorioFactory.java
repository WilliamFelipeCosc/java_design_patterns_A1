package atividade1;

public abstract class RelatorioFactory {
    
    public abstract Relatorio criarRelatorio();
    
    public void processarRelatorio() {
        Relatorio relatorio = criarRelatorio();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Factory: " + this.getClass().getSimpleName());
        System.out.println("Tipo: " + relatorio.getInfo());
        System.out.println("=".repeat(50));
        
        relatorio.preparar();
        System.out.println();
        relatorio.gerar();
        
        System.out.println("\nProcessamento concluido!\n");
    }
}
