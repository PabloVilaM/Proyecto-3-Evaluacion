package view.laberinto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze extends Group {

    private final Stack<Cell> stack;
    private final List<Cell> grid;
    private Cell currentCell;
    private double scale;
    private Point2D startPoint;
    private Point2D endPoint;

    public Maze(int rows, int cols) {
        this(rows, cols, 20);
    }

    /**
     * Funcion del algoritmo que crea el laberinto. El funcionamiento del algoritmo es "simple", el algoritmo
     * detecta si hay vecinos no visitados, en caso de haber va a el y borra la pared por la que pasa, así todo
     * el rato. Esto hace que no modifique nada por lo que no ha pasado dado que si una celda ya esta en su "stack"
     * de celdas visitadas hace que no modifique a menos que este encerrado, que entonces vuelve para atras hasta
     * que en el stack de celdas no visitadas haya una. Cuando se acaba el laberinto vacia el stack entero porque le
     * da que todas fueron visitadas
     * @param cols numero de columnas
     * @param rows numero de filas
     * @param scale escala
     */
    public Maze(int cols, int rows, double scale) {
        this.stack = new Stack<Cell>();
        this.grid = new ArrayList<Cell>();
        this.scale = scale;

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                grid.add(new Cell(x, y));
            }
        }

        // startPoint y endPoint
        Random rnd = new Random();

        boolean verticalRandom = rnd.nextBoolean();
        boolean horizontalRandom = rnd.nextBoolean();

        this.startPoint = new Point2D((horizontalRandom ? 0 : cols - 1) * scale + (scale / 2),
                (verticalRandom ? 0 : rows - 1) * scale + (scale / 2));
        this.endPoint = new Point2D((horizontalRandom ? cols - 1 : 0) * scale + (scale / 2),
                (verticalRandom ? rows - 1 : 0) * scale + (scale / 2));

        // Generate maze
        this.currentCell = grid.get(grid.indexOf(new Cell(0, 0)));

        this.currentCell.setVisited(true);
        this.stack.push(this.currentCell);

        while (!stack.isEmpty()) {
            this.currentCell = this.stack.pop();

            if (this.currentCell.hasUnvisitedNeighbours(grid)) {
                this.stack.push(currentCell);
                Cell nextCell = this.currentCell.getRandomNeighbour(grid);
                this.currentCell.removeWall(nextCell);
                nextCell.setVisited(true);
                this.stack.push(nextCell);
            }
        }

        this.draw();
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }

    /**
     * Funcion del algoritmo que se encarga de pintar los muros
     */
    private void draw() {
        double thickness = scale / 16;
        double length = scale + thickness;

        for (Cell cell : grid) {
            double posX = cell.getX() * scale;
            double posY = cell.getY() * scale;

            // Top wall
            if (cell.getWalls()[0]) {
                Rectangle wall = new Rectangle(posX, posY, length, thickness);
                wall.setFill(Color.BLACK);
                this.getChildren().add(wall);
            }
            // Right wall
            if (cell.getWalls()[1]) {
                Rectangle wall = new Rectangle(posX + scale, posY, thickness, length);
                wall.setFill(Color.BLACK);
                this.getChildren().add(wall);
            }
            // Bottom wall
            if (cell.getWalls()[2]) {
                Rectangle wall = new Rectangle(posX, posY + scale, length, thickness);
                wall.setFill(Color.BLACK);
                this.getChildren().add(wall);
            }
            // Left wall
            if (cell.getWalls()[3]) {
                Rectangle wall = new Rectangle(posX, posY, thickness, length);
                wall.setFill(Color.BLACK);
                this.getChildren().add(wall);
            }
        }
    }

}
