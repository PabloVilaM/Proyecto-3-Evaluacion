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

import javax.swing.*;
import java.sql.SQLException;

public class Exit extends Rectangle {
    boolean finish = false;

    public Exit(Point2D endPoint, double scale) {
        super(endPoint.getX() - scale / 3, endPoint.getY() - scale / 3, scale / 1.5, scale / 1.5);
        super.setFill(Color.RED);
    }

    private boolean hasCollision() {
        boolean collision = false;
        for (Node node : super.getParent().getChildrenUnmodifiable()) {
            if (node instanceof Player && node.intersects(this.getLayoutBounds())) {
                collision = true;
            }
        }

        return collision;
    }

    public void update() {
        if (hasCollision() && !finish) {
            finish = true;

            MazeController.cerrarStage();
        }
    }
}
