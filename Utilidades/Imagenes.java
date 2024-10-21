package Utilidades;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Imagenes {

    /**
     * Método para escalar imagenes
     * @return Un ImageIcon con la imagen escalada
     */
    public static ImageIcon cargarImagenEscalada(String rutaRecurso, int ancho, int alto) {

        URL ruta = Imagenes.class.getResource(rutaRecurso);

        if (ruta != null) {
            ImageIcon iconoOriginal = new ImageIcon(ruta);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT);
            return new ImageIcon(imagenEscalada);
        } else {
            System.out.println("Imagen no encontrada:   " + rutaRecurso);
            return null;
        }
    }
}
