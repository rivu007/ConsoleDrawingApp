package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;

/**
 * The Rectangle component draws rectangle starting from upper left corner of(x1,y1)tolower right corner (x2,y2)
 * using {@see CellContent.CROSS}.
 *
 * Validation Criteria:
 *          - As a prerequisites before Line component, canvas has to be defined by the user.
 *          - It expects (x1,y1) and (x2,y2) co-ordinates to draw the rectangle.
 *
 * @author Abhilash Ghosh.
 * @since 1.1
 * @version 1.2
 */
public class Rectangle extends ShapeDecorator {

    int x1, x2, y1, y2;

    public Rectangle(Shape canvas, int... args){
        super(canvas);

        //validation rule for Line:
        if(args.length != 4) {
            throw new IllegalArgumentException("Invalid args supplied! Rectangle command accept 4 args: x1, y1, x2, y2");
        }

        int x1 = args[0];
        int y1 = args[1];
        int x2 = args[2];
        int y2 = args[3];

        if(x1 < 0 || x2 < 0 || x1 > getCanvas().getWIDTH() || x2 > getCanvas().getWIDTH()){
            throw new ValidationException("Validation Exception: x1: "+ x1 + ", x2: "+ x2 + " co-ordinates can't be non negative or greater than canvas width: " + getCanvas().getWIDTH());
        }

        if(y1 < 0 || y2 < 0 || y1 > getCanvas().getHEIGHT() || y2 > getCanvas().getHEIGHT()){
            throw new ValidationException("Validation Exception: y1: "+ y1 + ", y2: "+ y2 + " co-ordinates can't be non negative or greater than canvas height: " + getCanvas().getHEIGHT());
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(){

        for(int row=y1; row<= y2; row++) {
            for(int col = x1; col<= x2; col++) {
                if(row==y1 || row==y2) //condition for painting boundary elements
                    getCanvas().getCells()[row][col] = CellContent.CROSS.symbol;
                else if(col==x1 ||  col==x2)
                    getCanvas().getCells()[row][col] = CellContent.CROSS.symbol;
                else
                    getCanvas().getCells()[row][col]= CellContent.EMPTY.symbol;
            }
        }
    }
}
