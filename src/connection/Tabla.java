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

        String[] columnNames = { "ID", "Score Trivia"};

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
        btnAadirLnea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                model.setRowCount(0);
                Conexion c = Conexion.getInstance();
                try {
                    c.consultar("Puntuaciones");

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }






            }
        });
    }


    public  static void insertarColumna(String nombre, String vida){
        Object[] aux = {nombre, vida};
        model.addRow(aux);
    }
}
