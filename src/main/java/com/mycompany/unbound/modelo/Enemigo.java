package com.mycompany.unbound.modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Enemigo extends Elemento {

    private Personaje personaje;
    private int velocidad;

    public Enemigo(int fila, int columna, int x, int y, ImageIcon imageIcon) {
        super(fila, columna, x, y, imageIcon);
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

//seguimiento del pesonaje principal (Atrapar al personaje
    //se pasa un personaje por parametro, del cual se va a usar sus coordenadas
    public void moverHaciaPersonaje(Personaje personaje) {
        // Mover en el eje de las filas (arriba o abajo)
        //si el valor de la variable fila en esta clase es menor al valor de la fila obteneida desde el personaje , a el valor de la fila disminuir
        if (this.fila < personaje.getFila()) {
            //abajo
            this.fila++;
            //arribo
        } else if (this.fila > personaje.getFila()) {
            this.fila--;
        }
        
        // Mover en el eje de las columnas arriba abajo)
        if (this.columna < personaje.getColumna()) {
            this.columna++;
            // izquierda          
        } else if (this.columna > personaje.getColumna()) {
            this.columna--;
        }
    }
}
