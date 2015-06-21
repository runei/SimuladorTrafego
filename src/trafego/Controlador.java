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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author UCS
 */
public class Controlador extends JPanel {

    private Timer timerMoverCarro;
    private Timer timerCriarCarro;
    private Timer timerSemaforo;
    private ArrayList<Rua> ruas;
    private final int widthRua = 200;
    private final int heightRua = 150;
    private final int widthCarro = 50;
    private final int heightCarro = 20;
    private final int velocidadeCarro = 10;
    private final int tempoMinimo = 1500;
    private final int tempoMaximo = 5000;

    public Controlador(Trafego t) {
        initControlador(t);
    }

    private void initControlador(Trafego t) {
        setFocusable(true);
        timerMoverCarro = new Timer(400, new ActionMoverCarro());
        timerCriarCarro = new Timer(1, new ActionCriarCarro());
        timerSemaforo = new Timer(10000, new ActionSemaforo());

        Rua r1 = new Rua(0, widthRua, "H");
        r1.setSemaforo(new Semaforo(true));
        Rua r2 = new Rua(widthRua, 0, "V");
        r2.setSemaforo(new Semaforo(false));
        Rua r3 = new Rua(widthRua + heightRua, widthRua, "H");
        Rua r4 = new Rua(widthRua, widthRua + heightRua, "V");
        Rua r5 = new Rua(widthRua, widthRua, "C");
        ruas = new ArrayList<>();
        ruas.add(r1);
        ruas.add(r2);
        ruas.add(r3);
        ruas.add(r4);
        ruas.add(r5);
    }

    public void iniciar() {
        // random
        //ruas.get(0).addCarro(new Carro());
        //ruas.get(1).addCarro(new Carro());

        timerCriarCarro.start();
        timerMoverCarro.start();
        timerSemaforo.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pintar(g);
    }

    private synchronized void pintar(Graphics g) {
        Iterator<Rua> itrua = ruas.iterator();
        while (itrua.hasNext()) {
            Rua r = itrua.next();
            pintarRua(g, r.getX(), r.getY(), r.getTipo());
            if (r.temSemaforo()) {
                pintarSemaforo(g, r.getSemaforo(), r.getX(), r.getY(), r.getTipo());
            }
            Iterator<Carro> itcarro = r.getCarros().iterator();
            while (itcarro.hasNext()) {
                Carro c = itcarro.next();
                pintarCarro(g, c.getX(), c.getY(), r.getTipo());
            }
        }
    }

    private void pintarRua(Graphics g, int x, int y, String tipo) {
        g.setColor(Color.BLACK);
        if (null != tipo) {
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
    }

    private void pintarSemaforo(Graphics g, Semaforo s, int x, int y, String tipo) {
        if (s.isSituacao()) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
        }
        if ("H".equals(tipo)) {
            g.fillRect(widthRua - 20, y, 20, heightRua);
        } else {
            g.fillRect(x, widthRua - 20, heightRua, 20);
        }
    }

    private Color corCarro() {
        int h = (int) (Math.random() * 255);
        int s = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return Color.getHSBColor(h, s, b);
    }
    
    private void pintarCarro(Graphics g, int x, int y, String tipo) {
        g.setColor(corCarro());
        if ("H".equals(tipo)) {
            g.fillRect(x, y, widthCarro, heightCarro);
        } else // if (tipo == "V"
        {
            g.fillRect(x, y, heightCarro, widthCarro);
        }
    }

    public class ActionSemaforo implements ActionListener {

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            Iterator<Rua> itRua = ruas.iterator();
            while (itRua.hasNext()) {
                Rua r = itRua.next();
                if (r.temSemaforo()) {
                    r.mudarEstadoSemaforo();
                }
            }
            repaint();
        }

    }

    public class ActionCriarCarro implements ActionListener {

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            ((Timer) e.getSource()).stop();

            int nrorua = (int) (Math.random() * 2);//5 nro de ruas
            /*if (nrorua == 3) {//3 rua central
                nrorua++;
            }*/

            Carro c = new Carro();

            int proxNroRua = (int) (Math.random() * 2) + 2;
            /*while (proxNroRua == nrorua || proxNroRua == 3) {
                proxNroRua++;
                if (proxNroRua > 4) {
                    proxNroRua = 0;
                }
            }*/

            c.setProxRua(proxNroRua);

            ruas.get(nrorua).addCarro(c);

            int delay = (int) (tempoMinimo + Math.random() * tempoMaximo);
            ((Timer) e.getSource()).setInitialDelay(delay);

            ((Timer) e.getSource()).start();
        }

    }

    public class ActionMoverCarro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ((Timer) e.getSource()).stop();
            moverCarros();
            repaint();
            ((Timer) e.getSource()).start();
        }

        public synchronized void moverCarros() {
            int indexRua = 0;
            Iterator<Rua> itRua = ruas.iterator();
            LinkedHashMap<Integer, Carro> rcRemove = new LinkedHashMap<>();
            LinkedHashMap<Integer, Carro> rcAdd = new LinkedHashMap<>();
            while (itRua.hasNext()) {
                Rua r = itRua.next();
                Iterator<Carro> itCarro = r.getCarros().iterator();
                while (itCarro.hasNext()) {
                    Carro c = itCarro.next();
                    if (null != r.getTipo()) {
                        switch (r.getTipo()) {
                            case "H":
                                if ((c.getX() + widthCarro) != (widthRua + r.getX())) {
                                    c.setX(c.getX() + velocidadeCarro);
                                } else if (r.temSemaforo() && r.getSemaforo().isSituacao()) // vira pra direita
                                {
                                    if (c.getProxRua() != indexRua && indexRua != 4) {
//                                        ruas.get(3).addCarro(c);
                                        rcAdd.put(4, c);
                                    }
                                   // r.removeCarro();
//                                    rcRemove.put(indexRua, c);
                                    itCarro.remove();
                                }
                                break;
                            case "V":
                                if ((c.getY() + widthCarro) != (widthRua + r.getY())) {
                                    c.setY(c.getY() + velocidadeCarro);
                                } else if (r.temSemaforo() && r.getSemaforo().isSituacao())// if (r.getY() == 0) vira esquerda
                                {
                                    if (c.getProxRua() != indexRua && indexRua != 4) {
//                                        ruas.get(3).addCarro(c);
                                        rcAdd.put(4, c);
                                    }
                               //     r.removeCarro();
                                    itCarro.remove();
//                                    rcRemove.put(indexRua, c);
                                }
                                break;
                            default:
                                if ((c.getY() + widthCarro) != (heightRua + r.getY())) {
                                    c.setY(c.getY() + velocidadeCarro);
                                } else // if (r.getY() == 0) vira esquerda
                                {
                             //       r.removeCarro();
//                                    ruas.get(c.getProxRua()).addCarro(c);;
                                    rcAdd.put(c.getProxRua(), c);
//                                    rcAdd.put(indexRua, c);
                                    
                                    itCarro.remove();
                                }
                                break;
                        }
                    }
                }
                indexRua++;
            }
            
            for(Map.Entry<Integer, Carro> e : rcAdd.entrySet())
            {
                ruas.get(e.getKey()).addCarro(e.getValue());
            }

            /*for(Map.Entry<Integer, Carro> e : rcRemove.entrySet())
            {
                ruas.get(e.getKey()).removeCarro()
            }*/

        }
    }
}
