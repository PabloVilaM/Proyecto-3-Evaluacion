package view;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerEventos {
    //El timer a crear
    private Timer timer = new Timer();


    //El tiempo restante
    private int segundos=0;
    //El tiempo que sobra al restante para llegar a 10
    private int sobrante=0;


    //Clase interna que funciona como contador
    class Contador2 extends TimerTask {

        public void run() {
            segundos--;
            sobrante++;
            if (segundos==0){
                Detener();
            }
            System.out.println("segundo: " + segundos);
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar(int segundos2)
    {

        this.sobrante=0;
        this.segundos= segundos2;
        timer = new Timer();
        timer.schedule(new Contador2(), 0, 1000);

    }
    //Detiene el contador
    public void Detener() {
        timer.cancel();
    }
    //Metodo que retorna los segundos transcurridos
    public int getSegundos()
    {
        return this.segundos;
    }
    //Metodo que retorna los segundosSobrantes
    public int getSobrante() {
        return sobrante;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

}
