package com.mycompany.unbound.modelo;

import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Personaje extends Elemento {
//posicion en el laberinto con coordenada


    //atributos del perssonaje
    private int velocidad = 10;
    private int vidas = 3;
    private int quesos = 0;

    public Personaje(int fila, int columna, int x, int y, ImageIcon imageIcon) {
        super(fila, columna, x, y, imageIcon);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getQuesos() {
        return quesos;
    }

    public void setQuesos(int quesos) {
        this.quesos = quesos;
    }
    
    public void perderVida(){
        vidas--;
    }
    
    public void sumarQueso(){
        quesos++;
    }

}
