/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameManager {

    private   final int TAMX = 1100;
    private   final int TAMY = 618;
    private float segundos = 0.017f;
    ViewManager vm = null;

    private  int a = 0;
    private  int b = 0;

    private   int misilx = 0;
    private  int misily = 0;
    private  float misilspeed = 0;

    private  int misilmalox = 0;
    private  int misilmaloy = 0;
    private  float misilmalospeed = 0;

    private   int hp=99;
    private   int hp2=5;
    private Timeline animationball;

    Clip clip;
    
    public void inicializarJuego (){
       
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,TAMX, TAMY, Color.BLACK);
        String hpvida = String.valueOf(hp);
            String hpvida2 = String.valueOf(hp2);

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("Inicio.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

           Stage primaryStage = new Stage();
            primaryStage.setTitle("ExceptionHunter");
            primaryStage.setScene(scene);
            primaryStage.show();


            Image image3 = new Image("file:Missile.png");
            ImageView mv3 = new ImageView(image3);
            root.getChildren().add(mv3);
            mv3.setRotate(90);
            mv3.setLayoutX(50);
            mv3.setLayoutY(325);
            mv3.setFitHeight(50);
            mv3.setFitWidth(50);
            mv3.setPreserveRatio(true);

            Image image5 = new Image("file:MissilVerde.png");
            ImageView mv5 = new ImageView(image5);
            root.getChildren().add(mv5);
            mv5.setLayoutX(1030);
            mv5.setLayoutY(330);
            mv5.setFitHeight(50);
            mv5.setFitWidth(50);
            mv5.setRotate(-90);
            mv5.setPreserveRatio(true);

            Image image = new Image("file:spaceShipSingleShoot.png");
            ImageView mv = new ImageView(image);
            root.getChildren().add(mv);
            mv.setRotate(-90);
            mv.setLayoutX(0);
            mv.setLayoutY(300);
            mv.setFitHeight(100);
            mv.setFitWidth(100);
            mv.setPreserveRatio(true);

            Image image4 = new Image("file:ovni.png");
            ImageView mv4 = new ImageView(image4);
            root.getChildren().add(mv4);
            mv4.setLayoutX(1000);
            mv4.setLayoutY(330);
            mv4.setFitHeight(100);
            mv4.setFitWidth(100);
            mv4.setPreserveRatio(true);

            Image image6 = new Image("file:Hearth.png");
            ImageView mv6 = new ImageView(image6);
            root.getChildren().add(mv6);
            mv6.setLayoutX(965);
            mv6.setLayoutY(100);
            mv6.setFitHeight(150);
            mv6.setFitWidth(150);
            mv6.setPreserveRatio(true);

            Image image7 = new Image("file:Hearth.png");
            ImageView mv7 = new ImageView(image7);
            root.getChildren().add(mv7);
            mv7.setLayoutX(-10);
            mv7.setLayoutY(100);
            mv7.setFitHeight(150);
            mv7.setFitWidth(150);
            mv7.setPreserveRatio(true);


            Text textTiempo = new Text(hpvida);
            textTiempo.setFont(Font.font(40));
            textTiempo.setFill(Color.BLACK);
            textTiempo.setX(40);
            textTiempo.setY(190);
            root.getChildren().add(textTiempo);

            Text textTiempo2 = new Text(hpvida2);
            textTiempo2.setFont(Font.font(40));
            textTiempo2.setFill(Color.BLACK);
            textTiempo2.setX(1015);
            textTiempo2.setY(190);
            root.getChildren().add(textTiempo2);


            BackgroundImage myBI= new BackgroundImage(new Image("file:cityskyline.png",1100,634,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
                //then you set to your node
            root.setBackground(new Background(myBI));
             animationball = new Timeline(
                    new KeyFrame(Duration.seconds(segundos), (ActionEvent ae)-> {
 
                        misilx += misilspeed;
                        mv3.setY(misily);
                        mv3.setX(misilx);

                        misilmalox += misilmalospeed;
                        mv5.setY(misilmaloy);
                        mv5.setX(misilmalox);


                        if (misilx >= 995){
                            misilspeed = 0.0001f;
                            misilmalospeed = 0.0001f;
                            animationball.pause();
                            mv5.setLayoutX(1030);
                            mv5.setLayoutY(330);
                            misilmalox = 0;
                            mv3.setLayoutX(50);
                            mv3.setLayoutY(325);
                            misilx = 0;

                            int numero = (int)(Math.random()*65+1);
                            Map<Integer, String> map = Preguntas.crearLista();
                            System.out.println(numero);
                            String pregunta = Preguntas.darPreguntas(numero, map);
                            Tiempo temp = new Tiempo();
                            temp.Contar();
                            String respuesta = JOptionPane.showInputDialog(pregunta);
                            boolean correcion = Preguntas.comprobarRespuesta(numero, respuesta);
                            if (correcion == false || correcion == true){
                               a = temp.getSegundos();
                                b = temp.getSobrante();
                                System.out.println("Tiempo:" + a);
                                temp.Detener();
                               JFrame frame = temp.getJframe();
                               frame.setVisible(false);
                               if (correcion == true){
                                   textTiempo.setText(String.valueOf(hp-b));
                                   textTiempo2.setText(String.valueOf(hp2-a));
                                   hp = hp-b;
                                   hp2 = hp2-a;
                               }
                               else if (correcion == false){

                                   textTiempo.setText(String.valueOf(hp-10));
                                   hp =hp -10;
                               }
                               Ficheros fich = new Ficheros();
                               fich.crearFichero(respuesta, correcion, map, numero);
                            }

                            animationball.play();
                        }

                    if(hp <= 0 || hp2 <= 0){
                           primaryStage.close();
                           if (hp <= 0){
                               String alias=JOptionPane.showInputDialog("GAME OVER! TYPE YOUR AKA");
                               String auxHp= hp+"";
                               Statement sentence = null;
                               Connection c = null;
                               Conexion cn = new Conexion(sentence, c, "Puntuaciones");
                               animationball.stop();
                               clip.stop();
                               try {
                                   cn.insertar(alias, auxHp);
                               } catch (SQLException ex) {
                                   Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (ClassNotFoundException ex) {
                                   Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                               }
                              
                           }
                           else if (hp2 <= 0){
                               String alias=JOptionPane.showInputDialog("WINNER! TYPE YOUR AKA");
                               String auxHp= hp+"";
                               Statement sentence = null;
                               Connection c = null;
                               Conexion cn = new Conexion(sentence, c, "Puntuaciones");
                               animationball.stop();
                               clip.stop();
                                try {
                                   cn.insertar(alias, auxHp);
                               } catch (SQLException ex) {
                                   Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                               } catch (ClassNotFoundException ex) {
                                   Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           }
                           primaryStage.close();

                       }




                    })
            );
            animationball.setCycleCount(Timeline.INDEFINITE);
            animationball.play();



            scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                public void handle(KeyEvent event){
                    switch(event.getCode()){
                        case SPACE:
                            misilspeed = 8;
                            misilmalospeed = -8;
                            break;
                        case ESCAPE:
                            animationball.stop();
                            break;
                        case P:
                            animationball.play();
                            break;
                    }
                }
            });
        
    }
}
