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

public class PongInd {

    //El ancho del stick
    private final int STICK_ANCHO = 7;
    //El alto del stick
    private final int STICK_ALTURA = 50;
    //Ancho de la pantalla
    private final int TAMXP = 600;
    //Altura de la pantalla
    private final int TAMYP = 400;
    //Centro de la bola (en x)
    private int ballCenterX = 10;
    //Velocidad actual de la bola x
    private int ballCurrentSpeedX = 3;
    //Centro de la bola en y
    private int ballCenterY = 30;
    //Velocidad actual de la bola en y
    private int ballCurrentSpeedY = 3;
    // Variable que nos permite no salirnos de pantalla con el stick
    private int stickPosY = (STICK_ALTURA-STICK_ANCHO) /2;
    //Velocidad del palo
    private int stickCurrentSpeed = 0;
    //N de paradas que haces que se pasa a un string posteriormente y se convierte en texto
    private  int fallos = 0;
    //Los "fps" de la animacion
    private float segundos = 0.017f;
    //Objeto core que mantiene la timeline unlimited.
    private Timeline animationPong;
    //Tamaño del texto de paradas
    private static final double TEXT_SIZE = 15;

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

        Text textTiempo = new Text("Paradas: ");
        textTiempo.setFont(Font.font(TEXT_SIZE));
        textTiempo.setFill(Color.BLACK);
        textTiempo.setX(5);
        textTiempo.setY(20);
        root2.getChildren().add(textTiempo);

        String falloString = String.valueOf("0");
        Text textTiempo2 = new Text(falloString);
        textTiempo2.setFont(Font.font(TEXT_SIZE));
        textTiempo2.setFill(Color.BLACK);
        textTiempo2.setX(65);
        textTiempo2.setY(20);
        root2.getChildren().add(textTiempo2);

        animationPong = new Timeline(
                new KeyFrame(Duration.seconds(segundos), (ActionEvent ae)-> {
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

                    if(stickPosY<0){
                        stickPosY = 0;
                    } else{
                        if(stickPosY > TAMYP - STICK_ALTURA){
                            stickPosY = TAMYP - STICK_ALTURA;
                        }
                    }



                    Shape colision = Shape.intersect(circleball, rect);

                    boolean colisionVacia = colision.getBoundsInLocal().isEmpty();

                    if (colisionVacia == false && ballCurrentSpeedX > 0){
                        ballCurrentSpeedX = -3;
                        fallos++;
                        textTiempo2.setText((String.valueOf(fallos)));
                    }


                    calcularVelocidad(conseguirStickCollision(circleball,rect));
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

}
