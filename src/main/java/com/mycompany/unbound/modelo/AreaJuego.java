package com.mycompany.unbound.modelo;

import com.mycompany.unbound.controlador.ControladorPrincipal;
import com.mycompany.unbound.vista.PanelLaberinto;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

///**
// *
// * @author lab3-09
// */
public class AreaJuego {

    private LaberintoLogico laberintoLogico;
    private ControladorPrincipal controladorPrincipal;

//coordenadas del laberinto
    private int coordenadasX[] = {9, 46, 98, 150, 202, 254, 306, 358, 410, 462, 514, 566, 618, 670, 722, 774, 826};
    private int coordenadasY[] = {9, 21, 73, 125, 177, 229, 281, 333, 385, 437, 489, 530, 590, 645, 697, 749, 801, 853};

//elementos
    private Personaje personaje;
    private Enemigo enemigo;
    private Trampa trampa;
    private Queso queso;
    private PanelLaberinto panelLaberinto;
    private ArrayList<Queso> quesos;
//direcciones cambio imagenes
    private ImageIcon imagenArriba;
    private ImageIcon imagenAbajo;
    private ImageIcon imagenIzquierda;
    private ImageIcon imagenDerecha;
    private ImageIcon imagenCastigo;
//______________________________________________________________________________

    public AreaJuego() {
        laberintoLogico = new LaberintoLogico();
        panelLaberinto = new PanelLaberinto();
//imasgenes de cambio
        imagenArriba = new ImageIcon("./src/main/resources/img/arriba.gif");
        imagenAbajo = new ImageIcon("./src/main/resources/img/abajo.gif");
        imagenIzquierda = new ImageIcon("./src/main/resources/img/izquierda.gif");
        imagenDerecha = new ImageIcon("./src/main/resources/img/derecha.gif");
        imagenCastigo = new ImageIcon("./src/main/resources/img/castigo.jpg");
        //saquito de almacenamiento
        quesos = new ArrayList<>();
        posicionarElementos();
    }
//______________________________________________________________________________
//poner cosas en el lab

    public void posicionarElementos() {
//personaje
        personaje = new Personaje(15, 1, coordenadasX[1], coordenadasY[15], new ImageIcon("./src/main/resources/img/abajo.gif"));
//enemigo
        enemigo = new Enemigo(4, 1, coordenadasX[1], coordenadasY[4], new ImageIcon("./src/main/resources/img/enemigo.gif"));
//trampa
        trampa = new Trampa(6, 3, coordenadasX[3], coordenadasY[6], new ImageIcon("./src/main/resources/img/trampa.gif"));

// anadir quesos al array
        queso = new Queso(8, 13, coordenadasX[13], coordenadasY[8], new ImageIcon("./src/main/resources/img/queso.png"));
        quesos.add(queso);
        queso = new Queso(3, 15, coordenadasX[15], coordenadasY[3], new ImageIcon("./src/main/resources/img/queso.png"));
        quesos.add(queso);
        queso = new Queso(4, 11, coordenadasX[11], coordenadasY[4], new ImageIcon("./src/main/resources/img/queso.png"));
        quesos.add(queso);
        queso = new Queso(8, 7, coordenadasX[7], coordenadasY[8], new ImageIcon("./src/main/resources/img/queso.png"));
        quesos.add(queso);

    }
//METODOS DE ACCION
//______________________________________________________________________________
//metodo que toma las imagenes, las direcciones y los movimientos en x y y para que reacione

    public void caminar(String direccion) {
        int filaDestino = personaje.getFila();
        int columnaDestino = personaje.getColumna();
        switch (direccion) {
            case "arriba":
                filaDestino--;
                personaje.setImageIcon(imagenArriba);
                break;
            case "abajo":
                filaDestino++;
                personaje.setImageIcon(imagenAbajo);
                break;
            case "izquierda":
                columnaDestino--;
                personaje.setImageIcon(imagenIzquierda);
                break;
            case "derecha":
                columnaDestino++;
                personaje.setImageIcon(imagenDerecha);
                break;
        }
//si en isvalid se cumple el rango por donde se puede caminar, y la celda del laberinto es 0 o 2 entonces va a setearse tanto las xy como las coordenadas
        if (isValid(filaDestino, columnaDestino) && laberintoLogico.getCelda(filaDestino, columnaDestino) == 0 || laberintoLogico.getCelda(filaDestino, columnaDestino) == 2) {
            personaje.setFila(filaDestino);
            personaje.setColumna(columnaDestino);
            personaje.setX(coordenadasX[columnaDestino]);
            personaje.setY(coordenadasY[filaDestino]);
        }
//reaccion
        verificarMeta();
        isColisionQueso();
        reaccionarTrampa();
    }
//______________________________________________________________________________
//vamos a controlar las coordendas validas

    public boolean isValid(int filaDestino, int columnaDestino) {
        System.out.println("fila destino: " + filaDestino + ", columna destino: " + columnaDestino);
// System.out.println("x "+coordenadasX+"y "+coordenadasY);
// Validar los limites/rango entre 0 y 17 entre x y y;
        if (filaDestino >= 0 && filaDestino <= 16 && columnaDestino >= 0 && columnaDestino <= 16) {
            System.out.println("caminando dentro");
            return true;
        }
        return false;
    }
//______________________________________________________________________________
//dibujar en el panel

    public void dibujar(Graphics g) {
        trampa.show(g);
//  personaje
        personaje.show(g);
// enemigo
        enemigo.show(g);
//queso
        for (int posicion = 0; posicion < quesos.size(); posicion++) {
            //pintar en las posiciones que guarda el array
            quesos.get(posicion).show(g);
        }
//actuaolzarlos
        panelLaberinto.repaint();
    }
//______________________________________________________________________________

    public void moverEnemigo() {
//llamar metodo de movimiento del enemigo
        enemigo.moverHaciaPersonaje(personaje);
//si dentro del rango valido las coordenadas del enemigo y la celda del lablogico en la que se encunetra el enemigo, si todo eso equivale a 0 entonces:
        if ((isValid(enemigo.getFila(), enemigo.getColumna())) && (laberintoLogico.getCelda(enemigo.getFila(), enemigo.getColumna()) == 0)) {
//esto no lo entendi
            enemigo.setX(coordenadasX[enemigo.getColumna()]);
            enemigo.setY(coordenadasY[enemigo.getFila()]);
//si colisiona con el enemigo entonces mandar esos mensajes y restar vidas;
            if (isColision(enemigo)) {
                System.out.println("choque con el enemigo");
                if (personaje.getVidas() >= 0) {
                    personaje.perderVida();
                    JOptionPane.showMessageDialog(null, "Perdiste una vida, ten cuidado " + personaje.getVidas());
                }
            }
            perder();
        }
    }
//______________________________________________________________________________

    public int getVidas() {
        return personaje.getVidas();
    }
//______________________________________________________________________________

    public int getQuesos() {
        return personaje.getQuesos();
    }
//______________________________________________________________________________
//choque con enemigo

    public boolean isColision(Enemigo enemigo) {
//si las coordenadas del enemigo son iguales al personaje 
        if (personaje.getFila() == enemigo.getFila() && personaje.getColumna() == enemigo.getColumna()) {
            return true;
        }
        return false;
    }
//______________________________________________________________________________
//colision de

    public void isColisionQueso() {
//si las coordenadas del queso son iguales a las del personaje
        for (int posicion = 0; posicion < quesos.size(); posicion++) {
            queso = quesos.get(posicion);
            //  if (personaje.getX()>queso.getX() && personaje.getX()+200<queso.getX() && personaje.getY()>queso.getY() && personaje.getY()+200<queso.getY()){        
            if (personaje.getFila() == queso.getFila() && personaje.getColumna() == queso.getColumna()) {
// quitar el queso
                quesos.remove(queso);
//y acumularlo en el personaje
                personaje.sumarQueso();
                JOptionPane.showMessageDialog(null, "Obtienes un quesom" + personaje.getQuesos());
            }
            break;
        }
    }
//______________________________________________________________________________
//JumpScare

    public void perder() {
//si las vidas del personaje son 0
        if (personaje.getVidas() == 0) {
            JOptionPane.showMessageDialog(null, "Perdiste el Juego");
//reiniciar
            int opcion = JOptionPane.showConfirmDialog(null, "Quieres jugar otra partida", "si o no?", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciar();
            } else if (opcion == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }

//panelLaberinto.
    }
//______________________________________________________________________________

    public void reiniciar() {
        personaje.setVidas(3);
        personaje.setQuesos(0);
        //borrar todos los quesos
        quesos.clear();
        //volver a generralos
        posicionarElementos();
    }
//______________________________________________________________________________
//ver si cuando toque el 2 del lablogico tiene suficientes quesos para ganar

    public void verificarMeta() {
        if (personaje.getFila() == 1 && personaje.getColumna() == 2) {
            if (personaje.getQuesos() >= 3) {
                JOptionPane.showMessageDialog(null, " \"LLegaste a la meta, escapaste con exito del pisuicas\"");
                reiniciar();

            } else if (personaje.getQuesos() < 3) {
                JOptionPane.showMessageDialog(null, """
                                                    No tienes quesos suficientes, te van a atrapar, apurate.
                                                    Quesos recolectados: """ + personaje.getQuesos());
            }
        }
    }
//______________________________________________________________________________

    public void reaccionarTrampa() {
        //tomar posicion actual del personaje, que seria la que el llegue a pisar, y despues settear otra coordenda dentro del lab
        if (personaje.getFila() == trampa.getFila() && personaje.getColumna() == trampa.getColumna()) {
            //setear el personaje desde trampa en este metodo
            trampa.setPersonaje(personaje);
            //despues a ese personaje moverlo 
            trampa.cambiarPosicion();
            JOptionPane.showMessageDialog(null, "Ups, caiste en un vortice!");
        }
    }
    public void hacerInmune(){
        
    }
    
    
}//class end 

