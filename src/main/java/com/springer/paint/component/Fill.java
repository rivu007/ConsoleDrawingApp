package com.springer.paint.component;


import com.springer.paint.exception.ValidationException;

/**
 * The fill component should fill the entire area connected to (x,y) with supplied color.
 * Validation Criteria:
 *          - It expects x and y co-ordinates and replacement color as args
 *
 * @author Abhilash Ghosh
 * @since 1.1
 * @version 1.2
 */
public class Fill extends ShapeDecorator {

    int x,y;
    String color;

    /**
     * Constructor to initialize the Fill component
     * @param args
     * @exception ValidationException
     */
    public Fill(Shape shape, String args[]) {
        super(shape);

        if(args.length != 3) {
            throw new IllegalArgumentException("Invalid args supplied! Fill command accept 3 args: x y o");
        }

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        String color = args[2];

        if(x < 1 ||  x > getCanvas().getWIDTH()){
            throw new ValidationException("Validation Exception: x:"+ x + " co-ordinates should be greater than 1 and less than canvas width: " + getCanvas().getWIDTH());
        }

        if(y < 1 ||  y > getCanvas().getHEIGHT()){
            throw new ValidationException("Validation Exception: y:"+ y + " co-ordinates should be greater than 1 and less than canvas height: " + getCanvas().getHEIGHT());
        }

        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw() {
        floodFill(getCanvas().cells, x, y, CellContent.EMPTY.symbol, color);
    }

    /**
     * Implementation of Flood fill algorithm. The flood-fill algorithm takes four parameters: x coordinate, x coordinate, a target color, and a replacement color.
     * The algorithm looks for all nodes in the array that are connected to the start node by a path of the target color and changes them to the replacement color.
     * @see <a href="https://en.wikipedia.org/wiki/Flood_fill">Flood Fill Algorithm</a>
     *
     * @param cells cells of the canvas
     * @param x starting x co-ordinate
     * @param y starting y co-ordinate
     * @param oldColor the color to be replaced
     * @param newColor the replacement color
     */
    private void floodFill(String cells[][], int x, int y, String oldColor, String newColor) {

        if (x < 0 || x >= getCanvas().COL+1 || y < 0 || y >= getCanvas().ROW+1)
            return;
        if (!oldColor.equals(cells[y][x]))
            return;

        // Replace the color at (x, y)
        cells[y][x] = newColor;
        // Recur for north, east, south and west
        floodFill(cells, x+1, y, oldColor, newColor);
        floodFill(cells, x-1, y, oldColor, newColor);
        floodFill(cells, x, y+1, oldColor, newColor);
        floodFill(cells, x, y-1, oldColor, newColor);
    }
}
