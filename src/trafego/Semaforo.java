/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

/**
 *
 * @author UCS
 */
public class Semaforo {

    private ECorSemaforo situacao;
    private int x;
    private int y;

    public Semaforo(ECorSemaforo situacao) {
        this.situacao = situacao;
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
     * @return the situacao
     */
    public ECorSemaforo getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(ECorSemaforo situacao) {
        this.situacao = situacao;
    }

    public boolean sinalAberto() {
        return this.situacao == ECorSemaforo.GREEN;
    }

}
