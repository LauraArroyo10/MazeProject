
package com.mycompany.unbound.modelo;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Queso extends Elemento{

    public Queso(int fila, int columna, int x, int y, ImageIcon imageIcon) {
        super(fila, columna, x, y, imageIcon);
    }
  
     public void show(Graphics g) {
        imageIcon.paintIcon(null, g, x, y);
    }
}
