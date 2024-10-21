package Frutas;

import java.awt.*;

public interface Fruta {

    int desaparecer(int valor);   //Metodo para desaparecer

    int getPosX();                //Metodos para obtener la posicion de la fruta
    int getPosY();

    void setPosX(int posX);       //Metodos para modificar la posicion de la fruta
    void setPosY(int posY);

    void actualizarPosicion(int posX, int posY);  //meto para actualizar al Jlabel

    void caer();                   //Metodo para la animacion de caida

    boolean fueraDePantalla();     //Metodo que nos dice si la fruta salio de pantalla

    Rectangle getBounds();         //Metodo para detectar colisiones

    int getValor();                //Metodo que nso da el valor de la fruta, es decir el valor que agrega al score
}