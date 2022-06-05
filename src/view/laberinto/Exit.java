package view.laberinto;

import connection.Conexion;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.sql.SQLException;

public class Exit extends Rectangle {
    boolean finish = false;

    /**
     * Dibuja un rectangulo con la escala correspondiente que le demos
     * @param endPoint El punto donde aparece, el "final"
     * @param scale La scala del cuadrado
     */
    public Exit(Point2D endPoint, double scale) {
        super(endPoint.getX() - scale / 3, endPoint.getY() - scale / 3, scale / 1.5, scale / 1.5);
        super.setFill(Color.RED);
    }

    /**
     * Comprueba si tiene colision, si tiene colision y es el olayer significa que existe por ende de vuelve un true
     * @return true si tiene colision, false si no
     */
    private boolean hasCollision() {
        boolean collision = false;
        for (Node node : super.getParent().getChildrenUnmodifiable()) {
            if (node instanceof Player && node.intersects(this.getLayoutBounds())) {
                collision = true;
            }
        }

        return collision;
    }

    /**
     * Si colisiona y no ha finalizado, lo finaliza y cierra la ventana
     */
    public void update() {
        if (hasCollision() && !finish) {
            finish = true;
            JOptionPane.showMessageDialog(null,"Ganaste");
            Clip clip = MazeController.getClip();
            clip.stop();
            MazeController.cerrarStage();
        }
    }
}
