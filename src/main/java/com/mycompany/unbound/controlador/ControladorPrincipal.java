package com.mycompany.unbound.controlador;

import com.mycompany.unbound.modelo.AreaJuego;
import com.mycompany.unbound.vista.GUIGuia;
import com.mycompany.unbound.vista.GUIHistoria;
import com.mycompany.unbound.vista.GUIInGameMenu;
import com.mycompany.unbound.vista.GUILaberinto;
import com.mycompany.unbound.vista.GUIPrincipal;
import com.mycompany.unbound.vista.GUIScore;
import com.mycompany.unbound.vista.PanelLaberinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
///**
// *
// * @author lab3-09
// */

public class ControladorPrincipal implements ActionListener, MouseListener, KeyListener {

    private GUIPrincipal guiPrincipal;
    private GUIGuia guiGuia;
    private GUILaberinto guiLaberinto;
    private GUIHistoria guiHistoria;

    private GUIInGameMenu guiMenu;
    private GUIScore guiScore;
    private PanelLaberinto panelLaberinto;
    private ControladorJuego controladorJuego;
    private ControladorHilo hilo;

    private AreaJuego areaJuego;

    public ControladorPrincipal() {

        areaJuego = new AreaJuego();
        //  GUI
        guiPrincipal = new GUIPrincipal();
        guiGuia = new GUIGuia();
        guiLaberinto = new GUILaberinto();
        guiMenu = new GUIInGameMenu(this);
        guiScore = new GUIScore(this);
        guiHistoria = new GUIHistoria(this);
        //
        panelLaberinto = guiLaberinto.getPanelLaberinto();
        controladorJuego = new ControladorJuego(guiLaberinto);
        controladorJuego.setPanelLaberinto(panelLaberinto);
        panelLaberinto.setControlador(controladorJuego);

        //instancia al hilo 
        hilo = new ControladorHilo(controladorJuego);
//
        guiPrincipal.escuchar(this);
        guiGuia.escuchar(this);

        guiLaberinto.escuchar(this);
        guiMenu.escuchar(this);
        guiScore.escuchar(this);
        guiHistoria.escuchar(this);

        // visibilidad inicial GUIframes panel
        guiPrincipal.setVisible(true);
        guiLaberinto.setVisible(false);
        guiGuia.setVisible(false);
        guiMenu.setVisible(false);
        guiScore.setVisible(false);
        //    panelLaberinto.setVisible(true);

        //escucha de eventos a traves de la referencia
        panelLaberinto.escucharMouse(controladorJuego);
        guiLaberinto.escucharKey(controladorJuego);
        guiLaberinto.setFocusable(true);
        guiLaberinto.requestFocusInWindow();

    }

    //___________________________Action Performed/manejo de eventos ____________
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "play":
                guiPrincipal.setVisible(false);
                guiLaberinto.setVisible(true);
                         hilo.start();
                break;

            case "guide":
                guiPrincipal.setVisible(false);
                guiGuia.setVisible(true);
                break;

            case "quit":
                System.exit(0);
                break;

            case "show":
                guiPrincipal.setVisible(false);
                guiGuia.setVisible(true);
                break;

            case "quitguide":
                guiGuia.setVisible(false);
                guiPrincipal.setVisible(true);
                break;

            case "goBack":
                guiHistoria.setVisible(false);
                guiGuia.setVisible(true);
                break;

            case "showstory":
                guiHistoria.setVisible(true);
                guiGuia.setVisible(false);

                break;

            case "showInGameMenu":
                System.out.println("menu presionado");
                guiMenu.setVisible(true);
           
                  //  hilo.wait();
           
 break;
            case "showScore":
                guiScore.setVisible(true);
                JOptionPane.showMessageDialog(null,"Quesos recoloectados: "+areaJuego.getQuesos());
                guiMenu.setVisible(false);
                break; 

            case "goBackMenu":
                guiScore.setVisible(false);
                guiMenu.setVisible(true);
                break;

            case "resumeGame":
                guiMenu.setVisible(false);
                guiLaberinto.setVisible(true);
                //hilo.
                System.out.println("resume game");
                break;
            case "endGame":
                guiMenu.setVisible(false);
                guiLaberinto.setVisible(false);
                guiPrincipal.setVisible(true);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("reflejado desde el controlador peincipal");
        System.out.println("presiono la cordenada X:  " + e.getX() + "  cordenada Y:  " + e.getY());

    }

    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
