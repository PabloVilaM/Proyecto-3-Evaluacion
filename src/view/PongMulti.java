package view;

import javafx.animation.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PongMulti {

    //Tamaño del texto
    private static final double TEXT_SIZE = 15;
    //Ancho del stick
    private final int STICK_ANCHO = 7;
    //Altura del stick
    private int STICK_ALTURA = 50;
    //Ancho de la pantalla
    private int TAMXP = 600;
    //Altura de la pantalla
    private final int TAMYP = 400;
    //Centro de la bola en x
    private int ballCenterX = 300;
    //Velocidad actual de la bola en x
    private int ballCurrentSpeedX = 3;
    //Centro de la bola en y
    private int ballCenterY = 30;
    //Velocidad de la bola en y
    private int ballCurrentSpeedY = 3;
    //Variable que hace que no nos salgamos y colisionemos bien con la pantalla
    private int stickPosY = (STICK_ALTURA-STICK_ANCHO) /2;
    //Velocidad actual del Stick
    private int stickCurrentSpeed = 0;
    //Frame per second
    private float segundos = 0.017f;
    //Objeto TimeLine, que le da "animacion" a todo lo que tenemos en pantalla
    private Timeline animationPong;
    //La velocidad actual del stick contrario
    private int stickCurrentEnemySpeed = 0;
    //Variable que hace que el stick contrario no se salga de pantalla
    private  int StickPosEnemyY = (STICK_ALTURA-STICK_ANCHO) /2;
    //El score 1
    private int score1 = 0;
    //El score 2
    private int score2 = 0;
    //La musica
    private Clip clip;
    //Segundos
    int segundosTim = 7;

    private void iniciarMusica(){
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("src/musica/Windmill Isle.wav"));
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
    }
    /**
     * Como bien dice el método, establece las cosas relacionadas con el pong, los stick la pelota la ventana el texto
     * las teclas, e incluso los colliders del stick y de la pelota
     */
    public void inicializarJuegoPong(){
        Pane root2 = new Pane();
        Scene scene2 = new Scene(root2,TAMXP, TAMYP, Color.BLACK);
        scene2.setFill(Color.BLACK);
        Stage pongStage = new Stage();
        pongStage.setTitle("Pong");
        pongStage.setScene(scene2);
        pongStage.show();

        Circle circleball = new Circle(10,ballCenterY,7, Color.BLACK);
        root2.getChildren().add(circleball);

        Rectangle rect = new Rectangle(560,stickPosY, STICK_ANCHO, STICK_ALTURA);
        rect.setFill(Color.BLACK);
        root2.getChildren().add(rect);

        Rectangle rect2 = new Rectangle(40,stickPosY, STICK_ANCHO, STICK_ALTURA);
        rect2.setFill(Color.BLACK);
        root2.getChildren().add(rect2);

        Text textTiempo = new Text("Score: ");
        textTiempo.setFont(Font.font(TEXT_SIZE));
        textTiempo.setFill(Color.BLACK);
        textTiempo.setX(5);
        textTiempo.setY(20);
        root2.getChildren().add(textTiempo);

        String falloString = String.valueOf("0");
        Text textTiempo2 = new Text(falloString);
        textTiempo2.setFont(Font.font(TEXT_SIZE));
        textTiempo2.setFill(Color.BLACK);
        textTiempo2.setX(50);
        textTiempo2.setY(20);
        root2.getChildren().add(textTiempo2);

        Text textTiempo3 = new Text("Score: ");
        textTiempo3.setFont(Font.font(TEXT_SIZE));
        textTiempo3.setFill(Color.BLACK);
        textTiempo3.setX(540);
        textTiempo3.setY(20);
        root2.getChildren().add(textTiempo3);

        String falloString2 = String.valueOf("0");
        Text textTiempo4 = new Text(falloString2);
        textTiempo4.setFont(Font.font(TEXT_SIZE));
        textTiempo4.setFill(Color.BLACK);
        textTiempo4.setX(585);
        textTiempo4.setY(20);
        root2.getChildren().add(textTiempo4);

        Text event = new Text("Evento actual: ");
        event.setFont(Font.font(TEXT_SIZE));
        event.setFill(Color.BLACK);
        event.setX(200);
        event.setY(20);
        root2.getChildren().add(event);

        Text eventactual = new Text("");
        eventactual.setFont(Font.font(TEXT_SIZE));
        eventactual.setFill(Color.BLACK);
        eventactual.setX(300);
        eventactual.setY(20);
        root2.getChildren().add(eventactual);

        TimerEventos temp = new TimerEventos();
        temp.Contar(segundosTim);

       iniciarMusica();

        animationPong = new Timeline(
                new KeyFrame(Duration.seconds(segundos), (ActionEvent ae)-> {
                    StickPosEnemyY += stickCurrentEnemySpeed;
                    rect2.setY(StickPosEnemyY);

                    stickPosY += stickCurrentSpeed;
                    rect.setY(stickPosY);

                    ballCenterX+= ballCurrentSpeedX;
                    circleball.setCenterX(ballCenterX);

                    circleball.setCenterY(ballCenterY);
                    ballCenterY += ballCurrentSpeedY;
                    if(ballCenterY >= TAMYP){
                        ballCurrentSpeedY = -3;
                    }
                    if(ballCenterY <= 0){
                        ballCurrentSpeedY = 3;
                    }
                    if (ballCenterX >= TAMXP){
                        ballCurrentSpeedX = -3;
                    }
                    else if(ballCenterX <= 0){
                        ballCurrentSpeedX = 3;
                    }

                    if (StickPosEnemyY<0){
                        StickPosEnemyY = 0;
                    } else{
                        if (StickPosEnemyY > TAMYP - STICK_ALTURA){
                            StickPosEnemyY = TAMYP - STICK_ALTURA;
                        }
                    }
                    if(stickPosY<0){
                        stickPosY = 0;
                    } else{
                        if(stickPosY > TAMYP - STICK_ALTURA){
                            stickPosY = TAMYP - STICK_ALTURA;
                        }
                    }

                    //Todo esto generá los eventos randoms para hacer todo mas divertido en el Pong
                    if (temp.getSegundos() == 0){
                        int numero =  (int)(Math.random()*42+1);
                        switch (numero){
                            case 1:
                                rect.setHeight(100);
                                eventactual.setText("MOD Pala");
                                break;
                            case 2:
                                rect2.setHeight(100);
                                eventactual.setText("MOD Pala");
                                break;
                            case 3:
                                rect.setHeight(50);
                                eventactual.setText("MOD Pala");
                                break;
                            case 4:
                                rect2.setHeight(50);
                                eventactual.setText("MOD Pala");
                                break;
                            case 5:
                                rect.setHeight(25);
                                eventactual.setText("MOD Pala");
                                break;
                            case 6:
                                rect2.setHeight(25);
                                eventactual.setText("MOD Pala");
                                break;
                            case 7:
                                rect.setWidth(14);
                                eventactual.setText("MOD Pala");
                                break;
                            case 8:
                                rect2.setWidth(14);
                                eventactual.setText("MOD Pala");
                                break;
                            case 9:
                                rect.setWidth(7);
                                eventactual.setText("MOD Pala");
                                break;
                            case 10:
                                rect2.setWidth(7);
                                eventactual.setText("MOD Pala");
                                break;
                            case 11:
                                rect.setWidth(3);
                                eventactual.setText("MOD Pala");
                                break;
                            case 12:
                                rect2.setWidth(3);
                                eventactual.setText("MOD Pala");
                                break;
                            case 13:
                                stickPosY = 100;
                                StickPosEnemyY = 100;
                                eventactual.setText("TP Pala");
                                break;
                            case 14:
                                stickPosY = 200;
                                StickPosEnemyY = 200;
                                eventactual.setText("TP Pala");
                                break;
                            case 15:
                                stickPosY = 300;
                                StickPosEnemyY = 300;
                                eventactual.setText("TP Pala");
                                break;
                            case 16:
                                ballCurrentSpeedX = 7;
                                ballCurrentSpeedY = 7;
                                eventactual.setText("MOD Bola");
                                break;
                            case 17:
                                ballCurrentSpeedX = 10;
                                ballCurrentSpeedY = 10;
                                eventactual.setText("MOD Bola");
                                break;
                            case 18:
                                ballCurrentSpeedX = 5;
                                ballCurrentSpeedY = 5;
                                eventactual.setText("MOD Bola");
                                break;
                            case 19:
                                ballCurrentSpeedX = -7;
                                ballCurrentSpeedY = -7;
                                eventactual.setText("MOD Bola");
                                break;
                            case 20:
                                ballCurrentSpeedX = -10;
                                ballCurrentSpeedY = -10;
                                eventactual.setText("MOD Bola");
                                break;
                            case 21:
                                ballCurrentSpeedX = -5;
                                ballCurrentSpeedY = -5;
                                eventactual.setText("MOD Bola");
                                break;
                            case 22:
                                ballCurrentSpeedX = 10;
                                eventactual.setText("MOD Bola");
                                break;
                            case 23:
                                ballCurrentSpeedX = 5;
                                eventactual.setText("MOD Bola");
                                break;
                            case 24:
                                ballCurrentSpeedX = 7;
                                eventactual.setText("MOD Bola");
                                break;
                            case 25:
                                ballCurrentSpeedX = -10;
                                eventactual.setText("MOD Bola");
                                break;
                            case 26:
                                ballCurrentSpeedX = -5;
                                eventactual.setText("MOD Bola");
                                break;
                            case 27:
                                ballCurrentSpeedX = -7;
                                eventactual.setText("MOD Bola");
                                break;
                            case 28:
                                ballCenterX = 300;
                                ballCenterY = 30;
                                eventactual.setText("MOD Bola");
                                break;
                            case 29:
                                circleball.setFill(Color.rgb(254,253,255));
                                eventactual.setText("COLOR");
                                break;
                            case 30:
                                rect.setFill(Color.rgb(254,253,255));
                                eventactual.setText("COLOR");
                                break;
                            case 31:
                                rect2.setFill(Color.rgb(254,253,255));
                                eventactual.setText("COLOR");
                                break;
                            case 32:
                                circleball.setFill(Color.BLACK);
                                eventactual.setText("COLOR");
                                break;
                            case 33:
                                rect.setFill(Color.BLACK);
                                eventactual.setText("COLOR");
                                break;
                            case 34:
                                rect2.setFill(Color.BLACK);
                                eventactual.setText("COLOR");
                                break;
                            case 35:
                                String auxiliar = textTiempo4.getText();
                                textTiempo4.setText(textTiempo2.getText());
                                textTiempo2.setText(auxiliar);
                                eventactual.setText("MOD Texto");
                                break;
                            case 36:
                                textTiempo4.setText("??");
                                textTiempo2.setText("??");
                                eventactual.setText("MOD Texto");
                                break;
                            case 37:
                                int numeroString =  (int)(Math.random()*10);
                                String rand = Integer.toString(numeroString);
                                int numeroString2 =  (int)(Math.random()*10);
                                String rando = Integer.toString(numeroString2);
                                textTiempo4.setText(rand);
                                textTiempo2.setText(rando);
                                eventactual.setText("MOD ???");
                                break;
                            case 38:
                                segundosTim = 6;
                                eventactual.setText("MOD Party");
                                break;
                            case 39:
                                segundosTim = 5;
                                eventactual.setText("MOD Party");
                                break;
                            case 40:
                                segundosTim = 3;
                                eventactual.setText("MOD Party");
                                break;
                            case 41:
                                rect.setHeight(10);
                                eventactual.setText("MOD Pala");
                                break;
                            case 42:
                                rect2.setHeight(10);
                                eventactual.setText("MOD Pala");
                                break;
                        }
                        temp.Contar(segundosTim);

                    }

                    Shape colision = Shape.intersect(circleball, rect);
                    Shape colision2 = Shape.intersect(circleball, rect2);
                    boolean colisionVacia = colision.getBoundsInLocal().isEmpty();
                    boolean colisionVacia2 = colision2.getBoundsInLocal().isEmpty();
                    if(colisionVacia2 == false){
                        ballCurrentSpeedX = 3;
                    }
                    if(colisionVacia == false){
                        ballCurrentSpeedX = -3;
                    }
                    if (ballCenterX >= 599){
                        ballCenterX = 300;
                        ballCurrentSpeedX = -3;
                        score1++;
                        textTiempo2.setText((String.valueOf(score1)));
                    }

                    if (ballCenterX <= 1){
                        ballCenterX = 300;
                        ballCurrentSpeedX = 3;
                        score2++;
                        textTiempo4.setText((String.valueOf(score2)));
                    }

                    if (score1>=10 || score2>=10){
                        if(score1>=10){
                            JOptionPane.showMessageDialog(null,"Gano el jugador 1");
                            animationPong.stop();

                            clip.stop();
                        }
                        else if (score2>=10){
                            JOptionPane.showMessageDialog(null,"Gano el jugador 2");
                            animationPong.stop();
                            clip.stop();
                        };
                        pongStage.close();
                    }

                    //Cuando se envia la solucitud de cerrado apaga la musica.
                    pongStage.setOnCloseRequest(evt -> {
                        // Para la musica
                        animationPong.stop();
                        clip.stop();
                    });

                    calcularVelocidad(conseguirStickCollision(circleball,rect));
                    calcularVelocidad2(conseguirStickCollision(circleball,rect2));
                })
        );

        scene2.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                switch(event.getCode()){
                    case UP:
                        stickCurrentSpeed = -6;
                        break;
                    case DOWN:
                        stickCurrentSpeed = 6;
                        break;
                    case W:
                        stickCurrentEnemySpeed = -6;
                        break;
                    case S:
                        stickCurrentEnemySpeed = 6;
                        break;
                }
            }
        });

        animationPong.setCycleCount(Timeline.INDEFINITE);
        animationPong.play();

        scene2.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent event){
                switch(event.getCode()){
                    case UP:
                        stickCurrentSpeed = 0;
                        break;
                    case DOWN:
                        stickCurrentSpeed = 0;
                        break;
                    case W:
                        stickCurrentEnemySpeed = 0;
                        break;
                    case S:
                        stickCurrentEnemySpeed = 0;
                        break;

                }
            }
        });
    }
    /**
     * Como bien dice el metodo, consigue la colision con el stick para que así el balon rebote
     * @param ball El objeto de la pelota
     * @param stick El objeto del rectangulo
     * @return Retorna un numero para saber la zona de colision y posteriormente darle un angulo
     */
    private int conseguirStickCollision(Circle ball, Rectangle stick){
        if(Shape.intersect(ball,stick).getBoundsInLocal().isEmpty()){
            return 0;
        }else{
            double offsetBallStick = ball.getCenterY()-stick.getY();
            if(offsetBallStick < stick.getHeight()*0.1){
                return 1;
            }else if (offsetBallStick < stick.getHeight() /2){
                return 2;
            }else if (offsetBallStick >= stick.getHeight() / 2 && offsetBallStick <stick.getHeight()*0.9){
                return 3;
            }else {
                return 4;
            }


        }
    }

    /**
     * Este metodo da angulo según la zona de colision en la paleta que tenga la pelota, el 1 y el 4 es que ha golpeado
     * en una de las esquinas por ende recibe mas velocidad en la y, el 2 y en el 3 ha golpeado mas en el centro por el que
     * la velocidad de x e y son las mismas
     * @param zonaColision seccion de la pala en la que colisiona la bola
     */
    private  void calcularVelocidad(int zonaColision){
        switch (zonaColision){
            case 0:
                break;
            case 1:
                ballCurrentSpeedX = -3;
                ballCurrentSpeedY = -6;
                break;
            case 2:
                ballCurrentSpeedX = -3;
                ballCurrentSpeedY = -3;
                break;
            case 3:
                ballCurrentSpeedX = -3;
                ballCurrentSpeedY = 3;
                break;
            case 4:
                ballCurrentSpeedX = -3;
                ballCurrentSpeedY = 6;
                break;
        }
    }

    /**
     * Este metodo da angulo según la zona de colision en la paleta que tenga la pelota, el 1 y el 4 es que ha golpeado
     * en una de las esquinas por ende recibe mas velocidad en la y, el 2 y en el 3 ha golpeado mas en el centro por el que
     * la velocidad de x e y son las mismas. Este se aplica a la paleta contraria, optimizable.
     * @param zonaColision seccion de la pala en la que colisiona la bola
     */
    private  void calcularVelocidad2(int zonaColision){
        switch (zonaColision){
            case 0:
                break;
            case 1:
                ballCurrentSpeedX = 3;
                ballCurrentSpeedY = 6;
                break;
            case 2:
                ballCurrentSpeedX = 3;
                ballCurrentSpeedY = 3;
                break;
            case 3:
                ballCurrentSpeedX = 3;
                ballCurrentSpeedY = -3;
                break;
            case 4:
                ballCurrentSpeedX = -3;
                ballCurrentSpeedY = -6;
                break;
        }
    }

}
