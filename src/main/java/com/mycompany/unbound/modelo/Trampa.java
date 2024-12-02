package com.mycompany.unbound.modelo;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Trampa extends Elemento {

    private Personaje personaje;

    public Trampa(int fila, int columna, int x, int y, ImageIcon imageIcon) {
        super(fila, columna, x, y, imageIcon);
        this.personaje = personaje;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public void show(Graphics g) {
        imageIcon.paintIcon(null, g, x, y);
    }

//cambiar posicion del eprsonaje'
    public void cambiarPosicion() {
//verificar si es diferente de null y si lo es ontinuar      
        if (personaje != null) {
            //coordenadas de cambio
            this.personaje.setFila(13);
            this.personaje.setColumna(12);
        }
    }

}//class end

