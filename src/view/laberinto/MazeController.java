package view.laberinto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.Tiempo;

import javax.swing.*;

public class MazeController {
    static Stage primaryStage = new Stage();
    static Tiempo temp = new Tiempo();
    Timeline timeline;
    int a = 0;
    int b = 0;
    //Objeto core que mantiene la timeline unlimited.
    private Timeline animationball;
    //Frames por segundo
    private float segundos = 0.017f;

    /**
     * Crea la ventana donde se va a trabajar el laberinto.
     */
    public void iniciar(){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,1920, 1090);

        setupControls(scene);

        primaryStage.setTitle("Laberinto");
        primaryStage.setScene(scene);
        primaryStage.show();

        Maze mazo = new Maze(32,17,30);
        root.getChildren().add(mazo);

        // Player
        Player player = new Player(mazo.getStartPoint(), 30);
        root.getChildren().add(player);

        // Exit
        Exit exit = new Exit(mazo.getEndPoint(), 30);
        root.getChildren().add(exit);
        temp.Contar(200);

        timeline = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        if (temp.getSegundos() == 0){
                           timeline.stop();
                           primaryStage.close();
                            JFrame frame = temp.getJframe();
                            temp.Detener();
                            frame.setVisible(false);
                        }

                        player.update();
                        exit.update();

                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    /**
     * Todos los controles de movimiento, incluido en las variables normales, arriba abajo w s etc..
     * @param scene La escena en lka que se usa
     */
    void setupControls(Scene scene) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                case UP:
                    Player.verticalDirection = Player.verticalDirection == -1 ? Player.verticalDirection
                            : --Player.verticalDirection;

                    break;
                case S:
                case DOWN:
                    Player.verticalDirection = Player.verticalDirection == 1 ? Player.verticalDirection
                            : ++Player.verticalDirection;

                    break;
                case A:
                case LEFT:
                    Player.horizontalDirection = Player.horizontalDirection == -1 ? Player.horizontalDirection
                            : --Player.horizontalDirection;
                    break;
                case D:
                case RIGHT:
                    Player.horizontalDirection = Player.horizontalDirection == 1 ? Player.horizontalDirection
                            : ++Player.horizontalDirection;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W:
                case UP:
                    Player.verticalDirection = Player.verticalDirection == 1 ? Player.verticalDirection
                            : ++Player.verticalDirection;

                    break;
                case S:
                case DOWN:
                    Player.verticalDirection = Player.verticalDirection == -1 ? Player.verticalDirection
                            : --Player.verticalDirection;

                    break;
                case A:
                case LEFT:
                    Player.horizontalDirection = Player.horizontalDirection == 1 ? Player.horizontalDirection
                            : ++Player.horizontalDirection;
                    break;
                case D:
                case RIGHT:
                    Player.horizontalDirection = Player.horizontalDirection == -1 ? Player.horizontalDirection
                            : --Player.horizontalDirection;
                    break;
                case ESCAPE:
                    cerrarStage();
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * Se detiene el temporizador y se vuelve invisible el frame
     */
   public static void cerrarStage(){
        primaryStage.close();

       JFrame frame = temp.getJframe();
       temp.Detener();
       frame.setVisible(false);
   }

}
