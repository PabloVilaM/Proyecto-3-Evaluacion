package view;

import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tiempo {
    //El timer a crear
     private Timer timer = new Timer();
     //El tiempo restante
    private int segundos=0;
    //El tiempo que sobra al restante para llegar a 10
    private int sobrante=0;
    //El label
    private JLabel jlabel;
    //El frame
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
    public void Contar(int segundos2)
    {

        jframe = new JFrame();
        jlabel = new JLabel();
        jframe.setLayout(new FlowLayout());
        jframe.setBounds(500, 300, 400, 100);

        jframe.add(jlabel);
        jframe.setVisible(true);

        this.sobrante=0;
        this.segundos= segundos2;
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
   //Metodo que retorna los segundosSobrantes
    public int getSobrante() {
        return sobrante;
    }
   //metodo para conseguir el Frame
    public JFrame getJframe() {
        return jframe;
    }
    
}
