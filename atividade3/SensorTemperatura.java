package atividade3;

public class SensorTemperatura extends Sensor {
    
    public SensorTemperatura(String regiao) {
        super(regiao, "Temperatura");
    }
    
    public void setTemperatura(double temperatura) {
        setValor(temperatura);
    }
    
    public double getTemperatura() {
        return getValor();
    }
}
