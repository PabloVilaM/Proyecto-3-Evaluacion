package view;

import connection.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import modelado.*;



public class ViewManager {
    
  private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private boolean sbs;
    private final static int MENU_BOTONES_INICIAL_X=100;
    private final static int MENU_BOTONES_INICIAL_Y=600;
    private ExcSubEscenas subScenaCreditos;
    private ExcSubEscenas subScenaAyuda;
    private Statement sentence=null;
    private Connection c=null;
    
    private String nombreTabla="Puntuaciones";
    private Conexion cn= new Conexion(sentence, c, nombreTabla);
    private ExcSubEscenas subElegirJuego;
    private ExcSubEscenas ocultarSubScena;
    private ExcSubEscenas subEscenas;
    List<JuegoBotones> botonesMenu;
    
    public ViewManager(){
        botonesMenu = new ArrayList<>();
        mainPane= new AnchorPane();
        mainScene = new Scene (mainPane, WIDTH, HEIGHT );
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        crearImagenFondoExtra();
        crearSubScenas();
        crearBoton();
        crearFondo();
        
        
       
    }
    
    private void noSolaparSubScenas(ExcSubEscenas subScene){
//        SI HAY ALGUNA SUBSCENA EN PANTALLA LLAMAMOS AL MÉTODO PARA HACER LA TRANSICION
        if(ocultarSubScena != null){
            ocultarSubScena.moverSubScene();
        }
//        HACEMOS LO MISMO CON LA SUBSCENA QUE PASAMOS POR PARAMETRO
        subScene.moverSubScene();
//        AHORA LA SUBSCENA ACTIVA PASA A SER LA SUBSCENA PASADA POR PARAMETRO
        ocultarSubScena= subScene;
    }
//    INICIALIZAMOS CADA SUBSCENEA CON LAS IMAGENES CORRESPONDIENTES Y LAS AÑADIMOS AL PANE
    private void crearSubScenas(){
        subScenaCreditos = new ExcSubEscenas("File:ayuda.png");
        mainPane.getChildren().add(subScenaCreditos);
        
        subScenaAyuda = new ExcSubEscenas("File:creditos.png");
        mainPane.getChildren().add(subScenaAyuda);
        



    }
    
    
    public Stage getMainStage(){
        
        return mainStage;
    }
//    UN METODO PARA DARLE POSICION A CADA BOTON A MEDIDA QUE SE VAN AÑADIENDO A UNA LISTA
//            NO TENER ASI QUE ASIGNAR COORDENADAS MANUALMENTE
    private void añadirBotonesMenu(JuegoBotones boton){
        boton.setLayoutX(MENU_BOTONES_INICIAL_X + botonesMenu.size()*200);
        boton.setLayoutY(MENU_BOTONES_INICIAL_Y);
        botonesMenu.add(boton);
        mainPane.getChildren().add(boton);
        
    }
    
//    AGRUPAR TODOS LOS METODOS DE CREACION DE BOTONES EN UNO SOLO, 
//            PARA QUE SEA ÉSTE EL QUE AÑADIMOS EN EL CONSTRUCTOR
     private void crearBoton(){
     crearBotonInicio();
     crearBotonAyuda();
     crearBotonCreditos();
     crearBotonSalir();
     crearBotonExtraPong();
     crearBotonExtraPong2();
     crearBotonScores();
    }
//CREAMOS BOTON SE LO PASAMOS AL METODO DE AÑADIR LOS BOTONES
//        INSTANCIAMOS OBJETO DE LA CLASE DONDE TENEMOS EL JUEGO
//                LLAMAMOS AL MÉTODO DE INICIALIZARLO
//                        PUESTO QUE ES EL BOTÓN DE JUGAR
//                                ADEMÁS ESTABLECEMOS LA CONEXIÓN Y LA CREACIÓN SI NO
//                                        EXISTIERA CON LA BASE DE DATOS
    private void crearBotonInicio (){
        JuegoBotones botonInicio = new JuegoBotones("JUGAR");
        añadirBotonesMenu(botonInicio);
        
        botonInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                
  
                 GameManager gm = new GameManager();
                 gm.inicializarJuego();
               cn.conectarBase();
                try {
                    cn.crearBase(nombreTabla);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
               
            }
        });
        
    }
//    EN ESTOS DOS MÉTODOS SIGUIENTES EN EL ACTIONEVENT LLAMAMOS AL METODO
//            QUE EFECTUA LAS TRANSICIONES Y LE PASAMOS LA SUBSCENA CORRESPONDIENTE
     private void crearBotonAyuda (){
        JuegoBotones botonAyuda = new JuegoBotones("AYUDA");
      
        añadirBotonesMenu(botonAyuda);
        
        botonAyuda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
      
                 noSolaparSubScenas(subScenaAyuda);
               
                
                 
              
            }
        });
    }
      private void crearBotonCreditos (){
        JuegoBotones botonCreditos = new JuegoBotones("CREDITOS");
     
        añadirBotonesMenu(botonCreditos);
        
        botonCreditos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  
       
               noSolaparSubScenas(subScenaCreditos);
            }
        });
         
    }
//      CERRAMOS LA APLICACION
       private void crearBotonSalir (){
        JuegoBotones botonSalir = new JuegoBotones("SALIR");
        añadirBotonesMenu(botonSalir);
        
        botonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              mainStage.close();
            }
        });
        
    }
//     ESTABLECEMOS LA POSICION MANUALMENTE DADO QUE NO IRAN EN FILA CON EL RESTO
//             LLAMAMOS EN EL EVENTO AL METODO EN EL QUE ESTÁ CODIFICADO EL JUEGO
            private void crearBotonExtraPong (){
        JuegoBotones botonExtraPong = new JuegoBotones("PONG SP");
       botonExtraPong.setLayoutX(281);
       botonExtraPong.setLayoutY(680);
       botonExtraPong.setScaleX(1.2);
       botonExtraPong.setStyle("-fx-background-color: transparent; -fx-background-image: url('/modelado/recursos/yellow_button00.png');");
         mainPane.getChildren().add(botonExtraPong);
        botonExtraPong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              PongInd pi = new PongInd();
              pi.inicializarJuegoPong();
            }
        });
        
    }
         private void crearBotonExtraPong2 (){
        JuegoBotones botonExtraPong2 = new JuegoBotones("PONG MP");
    
      botonExtraPong2.setLayoutX(521);
      botonExtraPong2.setLayoutY(680);
      botonExtraPong2.setScaleX(1.2);
      botonExtraPong2.setStyle("-fx-background-color: transparent; -fx-background-image: url('/modelado/recursos/yellow_button00.png');");
        mainPane.getChildren().add(botonExtraPong2);
        
        botonExtraPong2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              PongMulti pm = new PongMulti();
              pm.inicializarJuegoPong();
            }
        });
        
    }
//         ESTABLECEMOS UN FONDO DEL PANEL PRINCIPAL
   private void crearFondo(){
        BackgroundImage myBI= new BackgroundImage(new Image("file:fondoMenu.jpg",1024,768,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(myBI));
       
   }
   private void crearImagenFondoExtra (){
     
       Image img3 = new Image("File:beta.png");
    
       ImageView iv3 = new ImageView(img3);
//       mainPane.getChildren().add(iv1);
//       mainPane.getChildren().add(iv2);
       mainPane.getChildren().add(iv3);
  
       iv3.setLayoutX(200);
       iv3.setLayoutY(160);
       iv3.setScaleX(0.7);
       iv3.setScaleY(0.7);
   }

//   CREAMOS Y DAMOS POSICION AL BOTÓN PARA MOSTRAR LAS PUNTUACIONES
//           LLAMANDO AL METODO QUE HACE LA CONSULTA EN LA BASE DE DATOS
    private void crearBotonScores (){
        JuegoBotones botonScores = new JuegoBotones("RANKING");
      
       
         botonScores.setLayoutX(400);
      botonScores.setLayoutY(200);
      botonScores.setScaleX(2.0);
      botonScores.setScaleY(2.0);
      botonScores.setStyle("-fx-background-color: transparent; -fx-background-image: url('/modelado/recursos/red_button00.png');");
        mainPane.getChildren().add(botonScores);
        
        botonScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
      
                
                    try{
                        ResultSet rs;
                        cn.consultar(nombreTabla);
                    }catch(ClassNotFoundException | SQLException e){
                        
                    }
            }
        });
    }
}
