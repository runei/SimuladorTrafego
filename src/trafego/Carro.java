/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

import java.awt.Color;

/**
 *
 * @author UCS
 */
public class Carro 
{
    private int x;
    private int y;
    private int proxRua;
    private Color cor;
    
    public Carro() 
    {
        x = 0;
        y = 0;
    }
    
    public Carro rotacionarEsq()
    {
        return null;
    }
    
    public Carro rotacionarDir()
    {
        return null;
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
     * @return the proxRua
     */
    public int getProxRua() {
        return proxRua;
    }

    /**
     * @param proxRua the proxRua to set
     */
    public void setProxRua(int proxRua) {
        this.proxRua = proxRua;
    }

    /**
     * @return the cor
     */
    public Color getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
}
