package Frutas;

import javax.swing.*;
import java.awt.*;
import Utilidades.Imagenes;

public class Pera extends JLabel implements Fruta {

    private int posX, posY;      //posiciones de la fruta

    /**
     * Contructor de Frutas.Pera que sirve para inicializar
     * los valores de las posiciones
     * @param posX para indicar la posicion del eje X
     * @param posY para indicar la posicion del eje Y
     */
    public  Pera(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.setIcon(Imagenes.cargarImagenEscalada("/Imagenes/pera.png",50,50));
        this.setBounds(posX,posY,50,50);
        this.setVisible(true);
    }

    /**
     * Metodo que nos sirve para dejar de mostrar en
     * pantalla y reiniciar valores
     *
     * @return
     */
    @Override
    public int desaparecer(int valor) {
        this.setBounds(-100,100,50,50);
        return valor;
    }


    /**
     * Metodo que regresa la posicion x del objeto
     * @return posX;
     */
    @Override
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo que regresa la posicion y del objeto
     * @return posY;
     */
    @Override
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo que asigna una nueva cordenada X
     * @param posX par indicar nueva posicion
     */
    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Metodo que asigna una nueva cordenada Y
     * @param posY par indicar nueva posicion
     */
    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public void actualizarPosicion(int posX, int posY) {
        setPosX(posX);
        setPosY(posY);
        this.setBounds(posX,posY,50,50);
    }


    /**
     * Metodo que simula la caida de la pera
     * modificando su posicion y
     */
    @Override
    public void caer() {
        posY = posY + 10;
        actualizarPosicion(posX,posY);
    }

    /**
     * Metodo que detecta si la fruta salio de pantalla
     */
    @Override
    public boolean fueraDePantalla() {
       if(posY  > 500) return true;
       else return false;
    }


    /**
     * Metodo que regresa el valor de puntuacion que
     * da la fruta
     * @return
     */
    @Override
    public int getValor() {
        return 2;
    }
}
