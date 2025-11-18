package atividade2;

public class AnalisadorInvestimentos {
    private EstrategiaCalculoRisco estrategia;
    
    public AnalisadorInvestimentos(EstrategiaCalculoRisco estrategia) {
        this.estrategia = estrategia;
    }
    
    public void setEstrategia(EstrategiaCalculoRisco estrategia) {
        this.estrategia = estrategia;
    }
    
    public PerfilRisco analisarCliente(Cliente cliente) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Analisando cliente: " + cliente.getNome());
        System.out.println("=".repeat(50));
        
        PerfilRisco perfil = estrategia.calcularPerfil(cliente);
        
        perfil.exibir();
        System.out.println("=".repeat(50));
        
        return perfil;
    }
}
