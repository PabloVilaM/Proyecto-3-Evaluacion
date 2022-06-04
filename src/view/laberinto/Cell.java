package view.laberinto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    private int x;
    private int y;
    private boolean[] walls;
    private boolean visited;

    public Cell(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        // top 0, right 1, bottom 2, left 3
        this.walls = new boolean[] { true, true, true, true };
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setWalls(boolean[] walls) {
        this.walls = walls;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void removeWall(Cell next) {
        int y = this.y - next.y;

        if (y == 1) {
            walls[0] = false;
            next.walls[2] = false;
        } else if (y == -1) {
            walls[2] = false;
            next.walls[0] = false;
        }

        int x = this.x - next.x;

        if (x == 1) {
            walls[3] = false;
            next.walls[1] = false;
        } else if (x == -1) {
            walls[1] = false;
            next.walls[3] = false;
        }
    }

    public boolean hasUnvisitedNeighbours(List<Cell> grid) {
        return getUnvisitedNeighbours(grid).size() != 0;
    }

    public List<Cell> getUnvisitedNeighbours(List<Cell> grid) {
        List<Cell> neighbours = new ArrayList<Cell>(4);

        Cell up = checkCellInBounds(grid, new Cell(x, y - 1));
        Cell right = checkCellInBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkCellInBounds(grid, new Cell(x, y + 1));
        Cell left = checkCellInBounds(grid, new Cell(x - 1, y));

        if (up != null)
            if (!up.visited)
                neighbours.add(up);
        if (right != null)
            if (!right.visited)
                neighbours.add(right);
        if (bottom != null)
            if (!bottom.visited)
                neighbours.add(bottom);
        if (left != null)
            if (!left.visited)
                neighbours.add(left);

        return neighbours;
    }

    public Cell getRandomNeighbour(List<Cell> grid) {
        List<Cell> neighbours = getUnvisitedNeighbours(grid);

        if (neighbours.size() > 0) {
            return neighbours.get(new Random().nextInt(neighbours.size()));
        } else {
            return null;
        }
    }

    private static Cell checkCellInBounds(List<Cell> grid, Cell cell) {
        if (grid.contains(cell)) {
            return grid.get(grid.indexOf(cell));
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return x == other.x && y == other.y;
    }
}
