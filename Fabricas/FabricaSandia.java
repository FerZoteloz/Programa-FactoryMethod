package Fabricas;

import Frutas.Fruta;
import Frutas.ListaFrutas;
import Frutas.Sandia;

public class FabricaSandia extends FabricaFrutas{
    private ListaFrutas disponibles;

    /**
     * Metodo contructor que inicializa valores
     * nos pasa las frutas  de disponibless
     * @param disponibles
     */
    public FabricaSandia(ListaFrutas disponibles){
        this.disponibles  = disponibles;
    }

    /**
     * Metodo que crea una fruta solo si no hay
     * disponibles, si hay disponbles regresa
     * ese objero
     * @param posX par indicar posicion nueva eje
     *             x del objeto
     * @param posY para indicar posicion nueva eje
     *             y del objeo
     * @return fruta que es un Objeto de una clase
     *         hija de Fruta
     */
    @Override
    public Fruta crearFruta(int posX, int posY) {
        Fruta fruta = disponibles.obtenerFruta(Sandia.class);
        if(fruta == null) return new Sandia(posX, posY);
        else fruta.actualizarPosicion(posX, posY);
        return fruta;
    }
}
