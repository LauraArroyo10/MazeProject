package com.mycompany.unbound.controlador;

import com.mycompany.unbound.modelo.AreaJuego;
import com.mycompany.unbound.vista.GUILaberinto;
import com.mycompany.unbound.vista.PanelLaberinto;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

///**
// *
// * @author lab3-09
// */
public class ControladorJuego implements KeyListener, MouseListener {

    private GUILaberinto guiLaberinto;
    private PanelLaberinto panelLaberinto;
    private AreaJuego areaJuego;

    public ControladorJuego(GUILaberinto guiLaberinto) {
        
        this.guiLaberinto = guiLaberinto;
        areaJuego = new AreaJuego();

    }

    
    public boolean ganar(){
        if(areaJuego.getVidas()==0){
               return true;
        }
        return false;
    }
            
            
    public void setPanelLaberinto(PanelLaberinto panelLaberinto) {
        this.panelLaberinto = panelLaberinto;
    }

    public void dibujar(Graphics g) {
        areaJuego.dibujar(g);
        panelLaberinto.repaint();
    }

    public void moverEnemigo() {
       areaJuego.moverEnemigo();
        panelLaberinto.repaint();
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

switch(e.getKeyCode()){
    
    //no se mueve porque no lo tengo mapeado
    
    case KeyEvent.VK_UP:
        areaJuego.caminar("arriba");
        break;
        
    case KeyEvent.VK_DOWN:
        areaJuego.caminar("abajo");
        break;
        
    case KeyEvent.VK_LEFT:
        areaJuego.caminar("izquierda");
        break;
        
    case KeyEvent.VK_RIGHT:
        areaJuego.caminar("derecha");
        break;
}
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

//______________________________________________________________________________
    // obtener informacion de coordenadas con el mouse
    public void mouseClicked(MouseEvent e) {
         System.out.println("reflejado desde el conrolador del juego");
        System.out.println("Mouse en X: " + e.getX() + " Y: " + e.getY());

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}//class end
