package atividade3;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("      SISTEMA DE MONITORAMENTO AMBIENTAL");
        System.out.println("            Design Pattern: Observer");
        System.out.println("====================================================\n");
        
        SensorTemperatura sensorTempZonaNorte = new SensorTemperatura("Zona Norte");
        SensorTemperatura sensorTempZonaSul = new SensorTemperatura("Zona Sul");
        SensorUmidade sensorUmidZonaNorte = new SensorUmidade("Zona Norte");
        SensorPoluicao sensorPoluZonaCentro = new SensorPoluicao("Zona Centro");
        
        PainelControle painelPrincipal = new PainelControle("Principal");
        PainelControle painelSecundario = new PainelControle("Secundario");
        ModuloAlertas moduloAlertas = new ModuloAlertas();
        RegistroHistorico registroHistorico = new RegistroHistorico(100);
        ModuloEstatisticas moduloEstatisticas = new ModuloEstatisticas();
        
        System.out.println("=== REGISTRANDO OBSERVERS ===\n");
        
        sensorTempZonaNorte.registrarObserver(painelPrincipal);
        sensorTempZonaNorte.registrarObserver(moduloAlertas);
        sensorTempZonaNorte.registrarObserver(registroHistorico);
        sensorTempZonaNorte.registrarObserver(moduloEstatisticas);
        
        sensorTempZonaSul.registrarObserver(painelPrincipal);
        sensorTempZonaSul.registrarObserver(moduloAlertas);
        sensorTempZonaSul.registrarObserver(registroHistorico);
        
        sensorUmidZonaNorte.registrarObserver(painelSecundario);
        sensorUmidZonaNorte.registrarObserver(moduloAlertas);
        sensorUmidZonaNorte.registrarObserver(registroHistorico);
        sensorUmidZonaNorte.registrarObserver(moduloEstatisticas);
        
        sensorPoluZonaCentro.registrarObserver(painelPrincipal);
        sensorPoluZonaCentro.registrarObserver(painelSecundario);
        sensorPoluZonaCentro.registrarObserver(moduloAlertas);
        sensorPoluZonaCentro.registrarObserver(registroHistorico);
        sensorPoluZonaCentro.registrarObserver(moduloEstatisticas);
        
        System.out.println("\n=== SIMULACAO DE LEITURAS DOS SENSORES ===\n");
        
        sensorTempZonaNorte.setTemperatura(28.5);
        
        sensorTempZonaSul.setTemperatura(32.0);
        
        sensorUmidZonaNorte.setUmidade(65.0);
        
        sensorPoluZonaCentro.setIndicePoluicao(85.0);
        
        System.out.println("\n=== ATUALIZACOES QUE GERAM ALERTAS ===\n");
        
        sensorTempZonaNorte.setTemperatura(38.5);
        
        sensorUmidZonaNorte.setUmidade(85.0);
        
        sensorPoluZonaCentro.setIndicePoluicao(150.0);
        
        sensorTempZonaSul.setTemperatura(-2.0);
        
        System.out.println("\n=== MAIS LEITURAS PARA ESTATISTICAS ===\n");
        
        sensorTempZonaNorte.setTemperatura(29.0);
        sensorTempZonaNorte.setTemperatura(31.5);
        sensorUmidZonaNorte.setUmidade(70.0);
        sensorPoluZonaCentro.setIndicePoluicao(95.0);
        
        registroHistorico.exibirHistorico();
        moduloEstatisticas.exibirResumo();
        
        System.out.println("=== DEMONSTRACAO: ADICIONAR NOVO OBSERVER ===\n");
        
        PainelControle painelNovo = new PainelControle("Novo Painel");
        sensorTempZonaNorte.registrarObserver(painelNovo);
        
        System.out.println("\nAtualizacao apos adicionar novo observer:");
        sensorTempZonaNorte.setTemperatura(30.0);
        
        System.out.println("\n=== DEMONSTRACAO: REMOVER OBSERVER ===\n");
        
        sensorTempZonaNorte.removerObserver(painelNovo);
        
        System.out.println("\nAtualizacao apos remover observer:");
        sensorTempZonaNorte.setTemperatura(29.5);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EXTENSIBILIDADE:");
        System.out.println("Para adicionar um novo modulo observador:");
        System.out.println("1. Criar classe implements Observer");
        System.out.println("2. Implementar metodo atualizar(tipo, regiao, valor)");
        System.out.println("3. Registrar: sensor.registrarObserver(novoModulo)");
        System.out.println("\nSem modificar codigo dos sensores (Open/Closed Principle)");
        System.out.println("Baixo acoplamento - Sensores nao conhecem observers concretos");
        System.out.println("=".repeat(50));
    }
}
