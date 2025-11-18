package atividade2;

public class PlataformaAnalise {
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("   PLATAFORMA DE ANALISE DE INVESTIMENTOS");
        System.out.println("          Design Pattern: Strategy");
        System.out.println("====================================================");
        
        Cliente cliente1 = new Cliente(
            "João Silva", 28, 500000, 15000, 3, true
        );
        
        Cliente cliente2 = new Cliente(
            "Maria Santos", 45, 1200000, 25000, 8, false
        );
        
        Cliente cliente3 = new Cliente(
            "Pedro Oliveira", 60, 2500000, 35000, 15, false
        );
        
        AnalisadorInvestimentos analisador = new AnalisadorInvestimentos(
            new ModeloAgressivo()
        );
        
        System.out.println("\n### ANALISE COM MODELO AGRESSIVO ###");
        analisador.analisarCliente(cliente1);
        analisador.analisarCliente(cliente2);
        
        System.out.println("\n### MUDANCA DINAMICA PARA MODELO MODERADO ###");
        analisador.setEstrategia(new ModeloModerado());
        analisador.analisarCliente(cliente1);
        analisador.analisarCliente(cliente2);
        
        System.out.println("\n### MUDANCA DINAMICA PARA MODELO CONSERVADOR ###");
        analisador.setEstrategia(new ModeloConservador());
        analisador.analisarCliente(cliente2);
        analisador.analisarCliente(cliente3);
        
        System.out.println("\n### DEMONSTRACAO: MESMO CLIENTE, MODELOS DIFERENTES ###");
        System.out.println("\nCliente: " + cliente1.getNome());
        
        analisador.setEstrategia(new ModeloAgressivo());
        PerfilRisco perfilAgressivo = analisador.analisarCliente(cliente1);
        
        analisador.setEstrategia(new ModeloModerado());
        PerfilRisco perfilModerado = analisador.analisarCliente(cliente1);
        
        analisador.setEstrategia(new ModeloConservador());
        PerfilRisco perfilConservador = analisador.analisarCliente(cliente1);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EXTENSIBILIDADE:");
        System.out.println("Para adicionar um novo modelo (ex: ModeloDinamico):");
        System.out.println("1. Criar classe ModeloDinamico implements EstrategiaCalculoRisco");
        System.out.println("2. Implementar metodo calcularPerfil() com nova logica");
        System.out.println("3. Usar: analisador.setEstrategia(new ModeloDinamico())");
        System.out.println("\nSem modificar codigo existente (Open/Closed Principle)");
        System.out.println("=".repeat(50));
    }
}
