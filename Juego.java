import Fabricas.FabricaSelector;
import Frutas.Fruta;
import Frutas.ListaFrutas;
import Frutas.Sandia;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Iterator;
import java.util.Random;

import Utilidades.Imagenes;

public class Juego extends JFrame implements Runnable, KeyListener {


    private int ultimaTecla = 1, estado = 1, reloj = 0, tiempoInvensible = 0, score;

    private JLabel fondo, vida1,vida2,vida3, win, puntuacion;

    public Thread hilo = new Thread(this);

    private Jugador jugador = new Jugador(Imagenes.cargarImagenEscalada("/Imagenes/ParadoDerecha1.png",200,200));

    private ListaFrutas listaFrutas = new ListaFrutas();

    private FabricaSelector fabricaSelector = new FabricaSelector(listaFrutas);




    /**
     * Metodo Constructor de la clase que inicializa
     * valores y da inicio metodos de logica
     */
    Juego(){
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        escenario();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        addKeyListener(this);
        this.requestFocus();

        hilo.start();
    }

    /**
     * Metodo que crea las caracteristicas de la ventana y utiliza
     *  JLayeredPane para dividir por capas(no funciona sin esto)
     */
    private void escenario() {

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(700, 500));
        setContentPane(layeredPane);

        fondo = new JLabel(Imagenes.cargarImagenEscalada("/Imagenes/fondo.jpeg",700,500));
        fondo.setBounds(0, 0, 700, 500);
        layeredPane.add(fondo, Integer.valueOf(0));


        jugador.actualizarCordenadas(200, 250);
        jugador.setVisible(true);
        jugador.setBounds(jugador.getPosX(), jugador.getPosY(), 200, 200);
        layeredPane.add(jugador, Integer.valueOf(1));


        vida1 = new JLabel(Imagenes.cargarImagenEscalada("/Imagenes/corazon.png",100,100));
        vida1.setVisible(true);
        vida1.setBounds(20, 20, 50, 50);
        layeredPane.add(vida1, Integer.valueOf(1));

        vida2 = new JLabel(Imagenes.cargarImagenEscalada("/Imagenes/corazon.png",100,100));
        vida2.setVisible(true);
        vida2.setBounds(70, 20, 50, 50);
        layeredPane.add(vida2, Integer.valueOf(1));

        vida3 = new JLabel(Imagenes.cargarImagenEscalada("/Imagenes/corazon.png",100,100));
        vida3.setVisible(true);
        vida3.setBounds(120, 20, 50, 50);
        layeredPane.add(vida3, Integer.valueOf(1));

        puntuacion = new JLabel("SCORE: 0");
        puntuacion.setFont(new Font("Arial", Font.BOLD, 24));
        puntuacion.setForeground(Color.ORANGE);
        puntuacion.setVisible(true);
        puntuacion.setBounds(500, 20, 200, 50);
        layeredPane.add(puntuacion, Integer.valueOf(3));

        win = new JLabel();
        win.setVisible(false);
        win.setBounds(200,0,300,300);
        layeredPane.add(win, Integer.valueOf(3));
    }


    /**
     * Metodo que nos ayuda a cambiar los corazones
     * en pantalla
     * @param valor para indicar la vida del personaje
     */
    public  void cambiarVidas(int valor){
        if(valor == 3){
            vida1.setVisible(true);
            vida2.setVisible(true);
            vida3.setVisible(true);
        }
        if(valor == 2){
            vida1.setVisible(true);
            vida2.setVisible(true);
            vida3.setVisible(false);
        }
        if(valor == 1){
            vida1.setVisible(true);
            vida2.setVisible(false);
            vida3.setVisible(false);
        }
        if(valor == 0){
            vida1.setVisible(false);
            vida2.setVisible(false);
            vida3.setVisible(false);
        }

    }


    /**
     * Metoodo que no usamos pero es necesaio
     * ya que al heredarlo de la interfaz no es
     * posible quitarlo
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e ) {

    }


    /**
     * Metodo que registra cuando presionamos una tecla
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(estado != 3){
            if (e.getKeyCode() != KeyEvent.VK_A && e.getKeyCode() != KeyEvent.VK_D) estado = 1;
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }

            if(e.getKeyCode() == KeyEvent.VK_A) {
                jugador.actualizarCordenadas(jugador.getPosX() - 40, jugador.getPosY());
                ultimaTecla = 1;
                estado = 2;

            }
            if(e.getKeyCode() == KeyEvent.VK_D) {
                jugador.actualizarCordenadas(jugador.getPosX() + 40, jugador.getPosY());
                ultimaTecla = 2;
                estado = 2;

            }
        }
    }

    /**
     * Metodo que registra cuando soltamos una tecla
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(estado != 3){
            if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                estado = 1;
            }
        }
    }


    /**
     * Metodo que heredamos de Runnable que hace que no se bloquee el hilo principal
     * En este metodo se encuentra toda la logica del juego
     */
    @Override
    public void run() {
        int time = 30;
        int contador = 0;



        while (true){

            //Cuando morimos hace animacion y pone cartel
            if(!(jugador.estaVivo())){

                jugador.setIcon(Imagenes.cargarImagenEscalada("/Imagenes/llorar.png",200,200));
                win.setVisible(true);

                win.setIcon(Imagenes.cargarImagenEscalada("/Imagenes/over.png", 300,300));
                break;
            }

            //si llegamos a 15 ganamos
            if(score > 15){
                win.setVisible(true);

                win.setIcon(Imagenes.cargarImagenEscalada("/Imagenes/victory.png", 300,300));
                break;
            }

            jugador.actualizarIcono(ultimaTecla,estado);


            //Creacion de nueva fruta cada 30 vueltas aprox 5 segundos
            if (time > 30) {
                int valorFruta = new Random().nextInt(3) + 1;
                Fruta fruta = fabricaSelector.decidirFabrica(valorFruta, listaFrutas);
                JLayeredPane layeredPane = (JLayeredPane) getContentPane();
                layeredPane.add((JLabel) fruta, Integer.valueOf(2));
                listaFrutas.agregarFrutaEnUso(fruta);  // Añadir esta línea
                layeredPane.revalidate();
                layeredPane.repaint();
                time = 0;
            }
            listaFrutas.actualizarFrutas();

            //iteramos sobre la lista para verificar colisiones
            Iterator<Fruta> iterator = listaFrutas.getFrutasEnUso().iterator();
            while (iterator.hasNext()) {
                Fruta fruta = iterator.next();
                if (jugador.getBounds().intersects(fruta.getBounds())) {
                    fruta.desaparecer(1);
                        getContentPane().remove((JLabel) fruta);
                        listaFrutas.devolverFruta(fruta);
                    iterator.remove();
                    actualizarPuntuacion(fruta.getValor());
                    //caso de sandia
                    if(fruta.getClass() == Sandia.class && tiempoInvensible == 0){
                        jugador.setVida(jugador.getvida() - 1);
                        cambiarVidas(jugador.getvida());
                        time = 0;
                        tiempoInvensible = 20;
                        estado = 3;
                        reloj = time++;
                        contador = time++;
                    }
                }
            }

            ///Nos un tiempo de invensibilidad para no morir funciona como un cronometro
            if(tiempoInvensible > 0){
                tiempoInvensible = tiempoInvensible - 1;
            }

            //Funciona como reloj para calcular cuanto tiempo mantiene la animacion de mareado
            if(estado == 3){
                if(reloj  > contador + 50) estado = 1;
                reloj++;
            }


            time++;             //agregamos tiempo al reloj

            try {
                Thread.sleep(60); ///Medida de tiempo de espera entre ciclo y cliclo

            } catch (InterruptedException ignored) {
            }
        }

    }

    /**
     * Meotodo que actualiza la puntuacione en pantalla
     * y en logica
     * @param valor para indicar cuanto le sumamos al score
      */
    private void actualizarPuntuacion(int valor ) {

        score = score + valor;
        if(score < 0) score = 0;
        puntuacion.setText("Puntuacion:" + score);

    }
}
