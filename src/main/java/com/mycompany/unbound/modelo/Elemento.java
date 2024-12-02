//Clase padre para todas las clases de elemetos 
package com.mycompany.unbound.modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Elemento {
//variables de la parte visual del laberinto

    protected int fila;
    protected int columna;
    protected int x;
    protected int y;
    protected ImageIcon imageIcon;

    public Elemento(int fila, int columna, int x, int y, ImageIcon imageIcon) {
        this.fila = fila;
        this.columna = columna;
        this.x = x;
        this.y = y;
        this.imageIcon = imageIcon;
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

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

  

    //en esta clase se define el metodo que va a mostrar los elementos, porque los
    //que vamos anecesitar dibujar son cosas que van en la lberinto de forma visual
    //y es necesario para dibujarlos
    public void show(Graphics g) {
        imageIcon.paintIcon(null, g, x, y);
    }

}
