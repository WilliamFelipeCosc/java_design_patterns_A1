package atividade1;

public class Main {
    
    public static void main(String[] args) {        
        RelatorioFactory factoryDiario = new RelatorioDiarioFactory();
        factoryDiario.processarRelatorio();
        
        RelatorioFactory factorySemanal = new RelatorioSemanalFactory();
        factorySemanal.processarRelatorio();
        
        RelatorioFactory factoryEmergencial = new RelatorioEmergencialFactory();
        factoryEmergencial.processarRelatorio();
        
    }
}
