package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;

/**
 * The Canvas class models the canvas element in the paint app.
 * It represents a well defined area on which components can be printed. It has to be the first component to be initialised.
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.2
 */
public class Canvas implements Shape {

    int ROW, COL;
    String[][] cells;

    /**
     * Constructor to initialize the canvas
     * @param args
     * @exception ValidationException
     */
    public Canvas(int... args) {
        //validation rule for Canvas:
        if(args.length != 2) {
            throw new IllegalArgumentException("Invalid argument supplied! Canvas command accept 2 args: C <width> <height>");
        }

        ROW = args[1];
        COL = args[0];
        cells = new String[ROW+2][COL+2];

        for(int row=0; row< ROW+2; row++) {
            for(int col = 0; col< COL+2; col++) {
                if(row==0 || row == ROW+1) //condition for painting boundary elements
                    cells[row][col] = CellContent.HORIZONTAL.symbol;
                else if( col==0 ||  col == COL+1)
                    cells[row][col] = CellContent.VERTICAL.symbol;
                else
                    cells[row][col]= CellContent.EMPTY.symbol;
            }
        }
    }

    /**
     * Print the present state of the canvas with all the component(s) assigned to it.
     */
    public void draw() {
        for (int row = 0; row < ROW+2; row++) {
            for (int col = 0; col < COL+2; col++) {
                System.out.print(cells[row][col]);
            }
            System.out.println();
        }
    }

    public String[][] getCells() {
        return cells;
    }

    public int getWIDTH() {
        return COL;
    }

    public int getHEIGHT() {
        return ROW;
    }
}
