import javax.swing.*;
import Utilidades.Imagenes;

public class Jugador extends JLabel {


    private int vida, posX, posY, animaciones = 0, timer = 0, contador = 0;

    /**
     * Metodo contructor de la clase
     * @param imageIcon para indicar el icono que llevara por defecto
     */
    public Jugador(ImageIcon imageIcon) {
        super(imageIcon);
        vida =3;
    }

    //GETTERS que nos don acceso a variables privadas
    public int getvida(){
        return vida;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    //SETTERS que nos dan acceso a modficarr variables privadas
    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setVida(int vida){
        this.vida = vida;
    }


    /**
     * Metodo que dice si aun tiene vidas el jugador
     * @return true/fase dependiendo si aun sobtran vidas
     */
    public boolean estaVivo(){
        return vida != 0;
    }

    /**
     * Metodo que actualiza las cordenadas del jugador
     * y les pone tambien un limite
     * @param posX para el eje x
     * @param posY para el eje y
     */
    public void actualizarCordenadas(int posX, int posY){
        if(posX > 0 && posX < 500) this.posX = posX;
        this.posY = posY;
    }


    /**
     * Meotodo que funciona con la logica de Juego
     * y ayuda  alas animaciones del jugador
     * @param ultimaTecla para indicar la ultima tecla que se
     *                    presiono
     * @param estado para indicar como comportarse
     */
    public void actualizarIcono(int ultimaTecla, int estado){
        setBounds(getPosX(),getPosY(), 200,200);
         /*
            INICIO ANIMACIONES CUANDO LA ULTIMA LETRA FUE A ****************************************************************************************  */
        if(ultimaTecla == 1){
            if(estado == 1){
                if(animaciones > 30) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/ParadoIzquierda2.png",200,200));
                    if(animaciones > 40) animaciones = 0;
                }
                if(animaciones <= 20) {
                    if(estado == 1) {
                        setIcon(Imagenes.cargarImagenEscalada("/Imagenes/ParadoIzquierda1.png",200,200));
                    }
                }
            }
            else if (estado == 2) {
                if(animaciones > 6) animaciones = 0;
                if(animaciones > 3) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/CorriendoIzquierda2.png",200,200));

                }
                if(animaciones <= 3) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/CorriendoIzquierda1.png",200,200));
                }
            }
        }

        //FINAL ANIMACIONES CUANDO LA ULTIMA LETRA FUE A ***********************************************************************************************************************************************/



        /* INICIO ANIMACIONES CUANDO LA ULTIMA LETRA FUE D *********************************************************************************************************************************************/
        if(ultimaTecla ==2 ){

            if(estado == 1){
                if(animaciones > 30) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/ParadoDerecha2.png",200,200));
                    if(animaciones > 40) animaciones = 0;
                }
                if(animaciones <= 20) {
                    if(estado == 1) {
                        setIcon(Imagenes.cargarImagenEscalada("/Imagenes/ParadoDerecha1.png",200,200));
                    }
                }
            }
            else if (estado == 2) {
                if(animaciones > 6) animaciones = 0;
                if(animaciones > 3) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/Corriendo2.png",200,200));

                }
                if(animaciones <= 3) {
                    setIcon(Imagenes.cargarImagenEscalada("/Imagenes/Corriendo1.png",200,200));
                }
            }
        }
        //FINAL ANIMACIONES CUANDO LA ULTIMA LETRA FUE D ***********************************************************************************************************************************************/


        //AQUI ESTA LA ANIMACION DE MAREADO CUANDO CAE LA SANDIA****************************************************************************************************
        if(estado == 3){
                if(animaciones < contador + 40){
                    if(contador < 7){
                        setIcon(Imagenes.cargarImagenEscalada("/Imagenes/JugadorMareado.png",200,200));
                    }
                    else if (contador > 7) setIcon(Imagenes.cargarImagenEscalada("/Imagenes/JugadorMareado2.png",200,200));
                            if (contador > 14) contador = 0;
                }

                contador++;
        }
        //AQUI ESTA LA ANIMACION DE MAREADO CUANDO CAE LA SANDIA****************************************************************************************************

        animaciones++;
    }

}
