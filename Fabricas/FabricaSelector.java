package Fabricas;

import Frutas.Fruta;
import Frutas.ListaFrutas;

import java.util.Random;

/**
 * Clase que decide que fabrica creara la fruta
 * esta actua como la fabrica "Grande"
 */
public class FabricaSelector {


    private ListaFrutas listaFrutas = null;                                         //Objetos que serviran para funcionamiento del programa
    private FabricaManzana manzana = new FabricaManzana(listaFrutas);
    private FabricaPeras pera = new FabricaPeras(listaFrutas);
    private FabricaSandia sandia = new FabricaSandia(listaFrutas);
    private Random random = new Random();


    /**
     * Metodo contructor donde inicializamos variables
     * @param listaFrutas
     */
    public FabricaSelector(ListaFrutas listaFrutas) {
        this.listaFrutas = listaFrutas;
        this.manzana = new FabricaManzana(listaFrutas);
        this.pera = new FabricaPeras(listaFrutas);
        this.sandia = new FabricaSandia(listaFrutas);
    }


    /**
     * Metodo que dedice que fabrica creara el objetp
     * @param valor para indicar que fruta crear
     * @param listaFrutas para pasar la lista de
     *                    frutas disponibles
     * @return fruta
     */
    public Fruta decidirFabrica(int valor, ListaFrutas listaFrutas){
        switch (valor){
            case 1:
                return manzana.crearFruta(decidirPosicion( 1),0);
            case 2:
                return pera.crearFruta(decidirPosicion(1),0);
            case 3:
                return sandia.crearFruta(decidirPosicion(1),0);
            default:
                throw new IllegalArgumentException("Valor no válido: " + valor);
        }
    }


    /**
     * Metodo que nos ayuda con decidir la posicion x del
     * objeto
     * @param valor para indicar si es para eje x o eje y
     * @return un valor random dentro de los limites
     */
    public int decidirPosicion(int valor) {
       if(valor == 1) valor = random.nextInt(600);
       else valor = random.nextInt(400);
        return  valor;
    }
}
