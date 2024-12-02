package com.mycompany.unbound.controlador;

import com.mycompany.unbound.modelo.AreaJuego;
import static java.lang.Thread.sleep;

/**
 *
 * @author Admin
 */
public class ControladorHilo extends Thread {

    private AreaJuego areaJuego;
    private ControladorJuego controladorJuego;
    private boolean estado;

    public ControladorHilo(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
        estado = true;

    }

    public void run() {
        while (true) {

            try {
                if (estado) {
                    controladorJuego.moverEnemigo();
                    if(controladorJuego.ganar()){
                        estado=false;
                    }
                    //System.out.println("me estoy moviendo");

                }//if end

                sleep(700);

            }//try end
            catch (InterruptedException ex) {
                System.out.println("" + ex);
            }

        }

    }
}
