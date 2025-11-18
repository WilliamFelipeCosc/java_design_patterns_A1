package atividade3;

import java.util.ArrayList;
import java.util.List;

public abstract class Sensor implements Subject {
    protected List<Observer> observers;
    protected String regiao;
    protected double valorAtual;
    protected String tipoSensor;
    
    public Sensor(String regiao, String tipoSensor) {
        this.observers = new ArrayList<>();
        this.regiao = regiao;
        this.tipoSensor = tipoSensor;
        this.valorAtual = 0.0;
    }
    
    @Override
    public void registrarObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registrado no sensor de " + tipoSensor + " - " + regiao);
    }
    
    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removido do sensor de " + tipoSensor + " - " + regiao);
    }
    
    @Override
    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.atualizar(tipoSensor, regiao, valorAtual);
        }
    }
    
    public void setValor(double novoValor) {
        System.out.println("\n[SENSOR] " + tipoSensor + " - " + regiao + 
                         ": valor atualizado de " + valorAtual + " para " + novoValor);
        this.valorAtual = novoValor;
        notificarObservers();
    }
    
    public double getValor() {
        return valorAtual;
    }
    
    public String getRegiao() {
        return regiao;
    }
    
    public String getTipoSensor() {
        return tipoSensor;
    }
}
