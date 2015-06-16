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
    
    private boolean situacao;
    private int x;
    private int y;
    
    public Semaforo(boolean _situacao)
    {
        situacao = _situacao;
    }

    /**
     * @return the situacao
     */
    public boolean isSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(boolean situacao) {
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
    
}
