package Fabricas;

import Frutas.Fruta;
import Frutas.ListaFrutas;
import Frutas.Manzana;
import Frutas.Pera;

import java.util.Map;
import java.util.Queue;

public class FabricaManzana extends FabricaFrutas{


    private  ListaFrutas disponibles;

    /**
     * Metodo contructor que inicializa valores
     * nos pasa las frutas  de disponibless
     * @param disponibles
     */
    public FabricaManzana(ListaFrutas disponibles){
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
        Fruta fruta = disponibles.obtenerFruta(Manzana.class);
        if(fruta == null) return new Manzana(posX, posY);
        else fruta.actualizarPosicion(posX, posY);
        return fruta;
    }
}
