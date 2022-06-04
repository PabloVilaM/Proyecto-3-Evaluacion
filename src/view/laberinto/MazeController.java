package view.laberinto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MazeController {

    //Objeto core que mantiene la timeline unlimited.
    private Timeline animationball;
    //Frames por segundo
    private float segundos = 0.017f;
    public void iniciar(){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,1920, 1020);

        setupControls(scene);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Laberinto");
        primaryStage.setScene(scene);
        primaryStage.show();

        Maze mazo = new Maze(64,34,30);
        root.getChildren().add(mazo);

        // Player
        Player player = new Player(mazo.getStartPoint(), 30);
        root.getChildren().add(player);

        // Exit
        Exit exit = new Exit(mazo.getEndPoint(), 30);
        root.getChildren().add(exit);


        Timeline timeline = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        player.update();
                        exit.update();
                    }
                }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

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
                default:
                    break;
            }
        });
    }
}
