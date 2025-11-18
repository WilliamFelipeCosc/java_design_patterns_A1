package atividade3;

public class SensorPoluicao extends Sensor {
    
    public SensorPoluicao(String regiao) {
        super(regiao, "Poluicao");
    }
    
    public void setIndicePoluicao(double indice) {
        setValor(indice);
    }
    
    public double getIndicePoluicao() {
        return getValor();
    }
}
