package Frutas;


import java.util.*;

/**
 * Clase que registra las frutas nuevas y las mete en un
 * HashMap si ya se dejaron de utilizar
 * Si estan en uso las metemos en una lista normal
 */
public class ListaFrutas {

    private Map<Class<? extends Fruta>, Queue<Fruta>> frutasDisponibles;   //Para las clases disponibles
    private List<Fruta> frutasEnUso;                                       //Para las frutas en uso


    /**
     * Metodo constructor de la clase donde incializamos valores
     */
    public ListaFrutas(){
        frutasDisponibles = new HashMap<>();
        frutasEnUso = new ArrayList<>();
    }


    /**
     * Metodo que noda una fruta en caso de que la haya disponible en su
     * respectiva cola
     * @param tipoFruta para indicar que tipo de fruta buscamos
     * @return fruta encontrada o no
     */
    public Fruta obtenerFruta(Class<? extends Fruta> tipoFruta) {
        Queue<Fruta> pool = frutasDisponibles.get(tipoFruta);

        if (pool == null) {
            return null;
        }
        Fruta fruta = pool.poll();
        frutasEnUso.add(fruta);
        return fruta;

    }


    /**
     *Metodo que vuelve a meter a las frutas que ya terminaron su ciclo
     * @param fruta para indicar la fruta que queremos meter
     */
    public void devolverFruta(Fruta fruta) {
        if (!frutasDisponibles.containsKey(fruta.getClass())) {
            frutasDisponibles.put(fruta.getClass(), new LinkedList<>());
        }
        frutasDisponibles.get(fruta.getClass()).add(fruta);
    }

    /**
     * Metodo que regresa la lista de Frutas en uso
     */
    public List<Fruta> getFrutasEnUso() {
        return frutasEnUso;
    }

    /**
     * Metodo que itera sanre las frutas en uso
     * y decid i las mete en frutas disponibles
     */
    public void actualizarFrutas() {
        Iterator<Fruta> iterator = frutasEnUso.iterator();
        while (iterator.hasNext()) {
            Fruta fruta = iterator.next();
            fruta.caer();
            if (fruta.fueraDePantalla()) {
                iterator.remove();
                devolverFruta(fruta);
            }
        }
    }

    /**
     * Metodo qye agrega una fruto a la lista de en uso
     * @param fruta para indicar la fruta
     */
    public void agregarFrutaEnUso(Fruta fruta) {
        frutasEnUso.add(fruta);
    }
}
