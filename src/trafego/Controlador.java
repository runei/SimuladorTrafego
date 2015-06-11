/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author UCS
 */
public class Controlador extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Rua> ruas;
    private final int widthRua = 200;
    private final int heightRua = 150;
    private final int widthCarro = 50;
    private final int heightCarro = 20;
    private final int velocidadeCarro = 10;

    public Controlador(Trafego t)
    {
        initControlador(t);
    }
    
    private void initControlador(Trafego t)
    {
       setFocusable(true);
       timer = new Timer(400, this);
       timer.start();
       
       Rua r1 = new Rua(0, widthRua, "H");
       Rua r2 = new Rua(widthRua, 0, "V");
       Rua r3 = new Rua(widthRua+heightRua, widthRua, "H");
       Rua r4 = new Rua(widthRua, widthRua, "C");
       Rua r5 = new Rua(widthRua, widthRua+heightRua, "V");
       ruas = new ArrayList<>();
       ruas.add(r1);
       ruas.add(r2);
       ruas.add(r3);
       ruas.add(r4);
       ruas.add(r5);
    }
    
    public void iniciar()
    {
        // random
        ruas.get(0).addCarro(new Carro());
        ruas.get(1).addCarro(new Carro());
        
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        pintar(g);
    }
    
    private void pintar(Graphics g)
    {
        for(Rua r : ruas)
        {
            pintarRua(g, r.getX(), r.getY(), r.getTipo());
            for(Carro c : r.getCarros())
            {
                pintarCarro(g, c.getX(), c.getY(), r.getTipo());
            }
        }
        // pintar ruas
        // pintar carros
        // pintar semaforos
        
    }
    
    private void pintarRua(Graphics g, int x, int y, String tipo)
    {
        g.setColor(Color.BLACK);
        if(null != tipo) 
        switch (tipo) {
            case "H":
                g.fillRect(x, y, widthRua, heightRua);
                break;
            case "V":
                g.fillRect(x, y, heightRua, widthRua);
                break;
            default:
                g.fillRect(x, y, heightRua, heightRua);
                break;
        }
    }
    
    private void pintarCarro(Graphics g, int x, int y, String tipo)
    {
        g.setColor(Color.YELLOW);
        if("H".equals(tipo))
        {
            g.fillRect(x, y, widthCarro, heightCarro);
        }
        else // if (tipo == "V"
        {
            g.fillRect(x, y, heightCarro, widthCarro);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        moverCarros();
        repaint();
    }
    
    public void moverCarros()
    {
        for(Rua r : ruas)
        {
            for(Carro c : r.getCarros())
            {
                if(null != r.getTipo()) 
                switch (r.getTipo()) 
                {
                    case "H":
                        if ((c.getX() + widthCarro) != (widthRua + r.getX()))
                        {
                            c.setX(c.getX() + velocidadeCarro);
                        }
                        else // vira pra direita
                        {
                            r.removeCarro();
                            ruas.get(3).addCarro(c);
                        }
                        break;
                    case "V":
                        if ((c.getY() + widthCarro) != (widthRua + r.getY()))
                        {
                            c.setY(c.getY() + velocidadeCarro);
                        }
                        else // if (r.getY() == 0) vira esquerda
                        {
                            r.removeCarro();
                            ruas.get(3).addCarro(c);
                        }
                        break;
                    default:
                        if ((c.getY() + widthCarro) != (heightRua + r.getY()))
                        {
                            c.setY(c.getY() + velocidadeCarro);
                        }
                        else // if (r.getY() == 0) vira esquerda
                        {
                            r.removeCarro();
                            ruas.get(4).addCarro(c);
                        }
                        break;
                }
            }
        }
    }
}
