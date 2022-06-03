package view;

import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tiempo {
     private Timer timer = new Timer();
    private int segundos=0;
    private int sobrante=0;
    private JLabel jlabel;
    private JFrame jframe;



    //Clase interna que funciona como contador
    class Contador extends TimerTask {

        public void run() {
            segundos--;
            sobrante++;
            if (segundos==0){
                Detener();
                jframe.setVisible(false);
            }
             jlabel.setText("Tiempo restante:" + segundos);
            System.out.println("segundo: " + segundos);
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar()
    {

        jframe = new JFrame();
        jlabel = new JLabel();
        jframe.setLayout(new FlowLayout());
        jframe.setBounds(500, 300, 400, 100);

        jframe.add(jlabel);
        jframe.setVisible(true);

        this.sobrante=0;
        this.segundos=10;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 1000);

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

    public int getSobrante() {
        return sobrante;
    }

    public JFrame getJframe() {
        return jframe;
    }
    
}
