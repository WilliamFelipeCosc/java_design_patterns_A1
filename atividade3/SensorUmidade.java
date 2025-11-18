package atividade3;

public class SensorUmidade extends Sensor {
    
    public SensorUmidade(String regiao) {
        super(regiao, "Umidade");
    }
    
    public void setUmidade(double umidade) {
        setValor(umidade);
    }
    
    public double getUmidade() {
        return getValor();
    }
}
