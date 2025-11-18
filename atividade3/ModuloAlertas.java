package atividade3;

public class ModuloAlertas implements Observer {
    private double limiteTemperatura = 35.0;
    private double limiteUmidade = 80.0;
    private double limitePoluicao = 100.0;
    
    @Override
    public void atualizar(String tipoSensor, String regiao, double valor) {
        boolean alerta = false;
        String mensagem = "";
        
        switch (tipoSensor) {
            case "Temperatura":
                if (valor > limiteTemperatura) {
                    alerta = true;
                    mensagem = "TEMPERATURA CRITICA: " + valor + "°C (Limite: " + 
                             limiteTemperatura + "°C)";
                } else if (valor < 0) {
                    alerta = true;
                    mensagem = "TEMPERATURA ABAIXO DE ZERO: " + valor + "°C";
                }
                break;
                
            case "Umidade":
                if (valor > limiteUmidade) {
                    alerta = true;
                    mensagem = "UMIDADE EXCESSIVA: " + valor + "% (Limite: " + 
                             limiteUmidade + "%)";
                } else if (valor < 20) {
                    alerta = true;
                    mensagem = "UMIDADE MUITO BAIXA: " + valor + "%";
                }
                break;
                
            case "Poluicao":
                if (valor > limitePoluicao) {
                    alerta = true;
                    String nivel = valor > 200 ? "PERIGOSO" : "ALTO";
                    mensagem = "POLUICAO " + nivel + ": " + valor + " AQI (Limite: " + 
                             limitePoluicao + " AQI)";
                }
                break;
        }
        
        if (alerta) {
            System.out.println("  [ALERTA] >>> REGIAO " + regiao + " - " + mensagem + " <<<");
        }
    }
}
