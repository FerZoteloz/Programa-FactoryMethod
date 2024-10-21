package Fabricas;

import Frutas.Fruta;

/**
 * Clase abstracta que sirve como bsae para que las demas
 * clases
 */
public abstract class FabricaFrutas {

    public abstract Fruta crearFruta(int posX, int posY);

}
