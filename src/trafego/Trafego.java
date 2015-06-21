/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author UCS
 */
public class Trafego extends JFrame {

    public Trafego()
    {
        initUI();
    }
    
    private void initUI()
    {
        Controlador c = new Controlador(this);
        add(c);
        c.iniciar();

        setSize(600, 600);
        setTitle("Trafego");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Trafego t = new Trafego();
                t.setVisible(true);
            }
        });                
    }
    
}
