package connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro {


    public static  void crearVentana(){
        JFrame framecr = new JFrame("Añadir usuario");
        framecr.setSize(600,500);
        framecr.setLocationRelativeTo(framecr);

        JPanel panelcr = new JPanel();
        panelcr.setSize(600,500);
        panelcr.setLayout(null);

        JButton boton = new JButton("Crear");
        boton.setBounds(300,350, 100, 50);


        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(50, 50, 100, 100);

        JLabel primerApellido = new JLabel("Primer apellido:");
        primerApellido.setBounds(50, 100, 150, 100);

        JLabel segundoApellido = new JLabel("Segundo apellido:");
        segundoApellido.setBounds(50, 150, 150, 100);

        JLabel dni = new JLabel("DNI:");
        dni.setBounds(50, 200, 150, 100);

        JLabel telf = new JLabel("Número telefono:");
        telf.setBounds(50, 250, 150, 100);

        JTextField nombreCampo = new JTextField();
        nombreCampo.setBounds(220, 85, 300, 30);

        JTextField primerApellidoCampo = new JTextField();
        primerApellidoCampo.setBounds(220, 135, 300, 30);

        JTextField segundoApellidoCampo = new JTextField();
        segundoApellidoCampo.setBounds(220, 185, 300, 30);

        JTextField dniCampo = new JTextField();
        dniCampo.setBounds(220, 235, 300, 30);

        JTextField telfCampo = new JTextField();
        telfCampo.setBounds(220, 285, 300, 30);

        boton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                framecr.dispose();
                String nome = nombreCampo.getText();
                String apel = primerApellidoCampo.getText();
                String segunApel = segundoApellidoCampo.getText();
                String dnii = dniCampo.getText();
                String telefono = telfCampo.getText();



            }

        });

        framecr.add(boton);
        framecr.add(nombreCampo);
        framecr.add(primerApellidoCampo);
        framecr.add(segundoApellidoCampo);
        framecr.add(dniCampo);
        framecr.add(telfCampo);
        framecr.add(telf);
        framecr.add(primerApellido);
        framecr.add(segundoApellido);
        framecr.add(dni);
        framecr.add(nombre);
        framecr.add(panelcr);
        framecr.setVisible(true);
    }
}
