package view.laberinto;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {
    public static int horizontalDirection = 0;
    public static int verticalDirection = 0;

    private double speed;

    public Player(Point2D startPoint, double scale) {
        super(startPoint.getX(), startPoint.getY(), scale/4, Color.valueOf("#512da8"));
        setStroke(Color.WHITE);
        setStrokeWidth(scale/12);

        this.speed = scale * 0.1;
    }

    private boolean hasCollision()
    {
        boolean collision = false;
        for (Node node : super.getParent().getChildrenUnmodifiable()) {
            if (node instanceof Maze)
            {
                for (Node wall : ((Maze)node).getChildren()) {
                    //System.out.println(node);
                    if (node.hashCode() != super.hashCode() && wall.intersects(super.getLayoutBounds()))
                    {
                        collision = true;
                    }
                }
            }
        }

        return collision;
    }

    public void update() {
        double horizontalMove = speed * horizontalDirection;
        double verticalMove = speed * verticalDirection;

        super.setCenterX(super.getCenterX() + horizontalMove);

        if(hasCollision()) {
            super.setCenterX(super.getCenterX() - horizontalMove);
        }

        super.setCenterY(super.getCenterY() + verticalMove);

        if(hasCollision()) {
            super.setCenterY(super.getCenterY() - verticalMove);
        }
    }

    public double getSpeed() {
        return speed;
    }
}
