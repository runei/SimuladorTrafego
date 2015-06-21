/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

import java.awt.Color;

/**
 *
 * @author W7
 */
public enum ECorSemaforo {

    GREEN(Color.GREEN), YELLOW(Color.YELLOW), RED(Color.RED);

    public Color cor;

    ECorSemaforo(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return this.cor;
    }

}
