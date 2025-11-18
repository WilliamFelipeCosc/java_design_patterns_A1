package atividade4;

public class SistemaAntifraude {
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("        SISTEMA ANTIFRAUDE - PAGAMENTOS ONLINE");
        System.out.println("       Design Pattern: Chain of Responsibility");
        System.out.println("====================================================\n");
        
        System.out.println("=== CONFIGURANDO CADEIA DE VERIFICACOES (Padrao) ===\n");
        
        VerificadorValorSuspeito verificadorValor = new VerificadorValorSuspeito();
        VerificadorGeolocalizacao verificadorGeo = new VerificadorGeolocalizacao();
        VerificadorHistoricoUsuario verificadorHistorico = new VerificadorHistoricoUsuario();
        VerificadorDispositivo verificadorDispositivo = new VerificadorDispositivo();
        
        verificadorValor.setProximo(verificadorGeo);
        verificadorGeo.setProximo(verificadorHistorico);
        verificadorHistorico.setProximo(verificadorDispositivo);
        
        ProcessadorPagamento processador = new ProcessadorPagamento(verificadorValor);
        
        System.out.println("Cadeia configurada: Valor -> Geo -> Historico -> Dispositivo\n");
        
        System.out.println("\n### CENARIO 1: Transacao normal - Deve ser aprovada ###");
        Transacao t1 = new Transacao("TXN001", 1500.00, "Sao Paulo, SP", 
                                     "USR001", "DEV001", "Brasil");
        processador.processarTransacao(t1);
        
        System.out.println("\n### CENARIO 2: Valor extremamente alto - Deve ser bloqueada ###");
        Transacao t2 = new Transacao("TXN002", 75000.00, "Rio de Janeiro, RJ", 
                                     "USR001", "DEV001", "Brasil");
        processador.processarTransacao(t2);
        
        System.out.println("\n### CENARIO 3: Pais bloqueado - Deve ser bloqueada ###");
        Transacao t3 = new Transacao("TXN003", 500.00, "Cidade X", 
                                     "USR001", "DEV001", "PaisBloqueado1");
        processador.processarTransacao(t3);
        
        System.out.println("\n### CENARIO 4: Usuario nao verificado - Deve ser bloqueada ###");
        Transacao t4 = new Transacao("TXN004", 2000.00, "Brasilia, DF", 
                                     "USR005", "DEV001", "Brasil");
        processador.processarTransacao(t4);
        
        System.out.println("\n### CENARIO 5: Historico de fraudes - Deve ser bloqueada ###");
        Transacao t5 = new Transacao("TXN005", 1000.00, "Curitiba, PR", 
                                     "USR004", "DEV001", "Brasil");
        processador.processarTransacao(t5);
        
        System.out.println("\n### CENARIO 6: Dispositivo suspeito - Deve ser bloqueada ###");
        Transacao t6 = new Transacao("TXN006", 800.00, "Porto Alegre, RS", 
                                     "USR001", "DEV003", "Brasil");
        processador.processarTransacao(t6);
        
        System.out.println("\n### CENARIO 7: Dispositivo novo com alto valor - Deve ser bloqueada ###");
        Transacao t7 = new Transacao("TXN007", 8000.00, "Salvador, BA", 
                                     "USR001", "DEV999", "Brasil");
        processador.processarTransacao(t7);
        
        System.out.println("\n### CENARIO 8: Usuario novo mas dentro dos limites - Deve ser aprovada ###");
        Transacao t8 = new Transacao("TXN008", 500.00, "Recife, PE", 
                                     "USR003", "DEV002", "Brasil");
        processador.processarTransacao(t8);
        
        System.out.println("\n=== REORGANIZANDO CADEIA - Nova Ordem ===\n");
        
        VerificadorHistoricoUsuario novoHistorico = new VerificadorHistoricoUsuario();
        VerificadorDispositivo novoDispositivo = new VerificadorDispositivo();
        VerificadorGeolocalizacao novaGeo = new VerificadorGeolocalizacao();
        VerificadorValorSuspeito novoValor = new VerificadorValorSuspeito();
        
        novoHistorico.setProximo(novoDispositivo);
        novoDispositivo.setProximo(novaGeo);
        novaGeo.setProximo(novoValor);
        
        processador.setPrimeiroVerificador(novoHistorico);
        
        System.out.println("Nova cadeia: Historico -> Dispositivo -> Geo -> Valor\n");
        
        System.out.println("\n### TESTANDO COM NOVA ORDEM ###");
        Transacao t9 = new Transacao("TXN009", 1200.00, "Fortaleza, CE", 
                                     "USR001", "DEV001", "Brasil");
        processador.processarTransacao(t9);
        
        System.out.println("\n=== ESTENDENDO CADEIA - Adicionando nova verificacao ===\n");
        
        VerificadorHorarioIncomum verificadorHorario = new VerificadorHorarioIncomum();
        
        VerificadorValorSuspeito v1 = new VerificadorValorSuspeito();
        VerificadorGeolocalizacao v2 = new VerificadorGeolocalizacao();
        VerificadorHistoricoUsuario v3 = new VerificadorHistoricoUsuario();
        VerificadorDispositivo v4 = new VerificadorDispositivo();
        
        v1.setProximo(v2);
        v2.setProximo(v3);
        v3.setProximo(v4);
        v4.setProximo(verificadorHorario);
        
        processador.setPrimeiroVerificador(v1);
        
        System.out.println("Cadeia estendida: Valor -> Geo -> Historico -> Dispositivo -> Horario\n");
        
        System.out.println("\n### TESTANDO COM VERIFICACAO DE HORARIO ###");
        Transacao t10 = new Transacao("TXN010", 2500.00, "Belo Horizonte, MG", 
                                      "USR002", "DEV002", "Brasil");
        processador.processarTransacao(t10);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXTENSIBILIDADE:");
        System.out.println("Para adicionar nova verificacao:");
        System.out.println("1. Criar classe extends VerificadorAntifraude");
        System.out.println("2. Implementar executarVerificacao(Transacao)");
        System.out.println("3. Inserir na cadeia: verificador.setProximo(novaVerificacao)");
        System.out.println("\nSem modificar verificadores existentes (Open/Closed Principle)");
        System.out.println("Cadeia pode ser reorganizada dinamicamente");
        System.out.println("Processamento interrompido ao primeiro erro");
        System.out.println("=".repeat(60));
    }
}
