/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author UCS
 */
public class Rua {

    private int x;
    private int y;
    private Queue<Carro> carros;
    private Semaforo semaforo;
    private String tipo;
    private final int maxCarrosRua = 3;

    public Rua(int _x, int _y, String _tipo) {
        carros = new LinkedList<>();
        x = _x;
        y = _y;
        tipo = _tipo;
        semaforo = null;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the carros
     */
    public Queue<Carro> getCarros() {
        return carros;
    }

    /**
     * @param carros the carros to set
     */
    public void setCarros(Queue carros) {
        this.carros = carros;
    }

    public void addCarro(Carro c) {
        if (this.carros.size() < maxCarrosRua) {
            c.setX(this.getX());
            c.setY(this.getY());
            this.carros.add(c);
        }
    }

    public Carro removeCarro() {
        return this.carros.remove();
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the semaforo
     */
    public Semaforo getSemaforo() {
        return semaforo;
    }

    /**
     * @param semaforo the semaforo to set
     */
    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public boolean temSemaforo() {
        return semaforo != null;
    }

    void mudarEstadoSemaforo() {
        if (semaforo.getSituacao() == ECorSemaforo.GREEN) {
            semaforo.setSituacao(ECorSemaforo.YELLOW);
        } else if (semaforo.getSituacao() == ECorSemaforo.YELLOW) {
            semaforo.setSituacao(ECorSemaforo.RED);
        } else {
            semaforo.setSituacao(ECorSemaforo.GREEN);
        }
    }

}
