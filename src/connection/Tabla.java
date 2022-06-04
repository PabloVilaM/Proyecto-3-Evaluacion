package connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Tabla {
    static  JButton btnAadirLnea = new JButton("Ver ranking");
    // versión
    private static final long serialVersionUID = 1L;

    // la tabla
    private JTable table;

    // el modelo de tabla, aquí van a estar los datos.
    private  static  DefaultTableModel model;

    // función principal


    // constructor del frame que contruye toda la ventana...
    public void crearTabla() {
        JFrame frame = new JFrame();
        frame.setTitle("Tabla");
        frame.setBounds(100,100,596,331);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 560, 227);

        frame.getContentPane().add(scrollPane);

        String[] columnNames = { "ID", "Score Trivia", "Score Pong", "Score PongMP", "Score laberinto" };

        // creo un modelo de datos, sin datos por eso 'null' y establezco los
        // nombres de columna
        model = new DefaultTableModel(null, columnNames);
        // creo la tabla con el modelo de datos creado
        table = new JTable(model);

        // se pone la tabla en el scroll
        scrollPane.setViewportView(table);
        // dimensiones y posición del botón
        btnAadirLnea.setBounds(10, 249, 267, 23);

        // pongo el botón en la ventana
        frame.getContentPane().add(btnAadirLnea);
        frame.setVisible(true);

    }

    public static void insercionTabla(String nombre, String vida, String Pong, String PongMp, String Laberinto){

        btnAadirLnea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Conexion c = Conexion.getInstance();

                Object[] aux = {nombre, vida,null, null, null };
                model.addRow(aux);

            }
        });

    }
}
