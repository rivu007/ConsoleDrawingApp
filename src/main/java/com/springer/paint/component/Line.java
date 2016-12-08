package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;

/**
 * The Line component draws horizontal and vertical line from(x1,y1)to(x2,y2) using {@see CellContent.CROSS}.
 * Validation Criteria:
 *          - As a prerequisites before Line component, canvas has to be defined by the user.
 *          - It expects (x1,y1) and (x2,y2) co-ordinates to draw the line.
 *
 * @author Abhilash Ghosh.
 * @since 1.1
 * @version 1.2
 */
public class Line extends ShapeDecorator {

    int x1, x2, y1, y2;

    public Line(Shape canvas, int... args) {
        super(canvas);

        //validation rule for Line:
        if(args.length != 4) {
            throw new IllegalArgumentException("Invalid argument supplied! Line command accept 4 args: x1, y1, x2, y2");
        }

        int x1 = args[0];
        int y1 = args[1];
        int x2 = args[2];
        int y2 = args[3];

        if(x1 < 0 || x2 < 0 || x1 > getCanvas().getWIDTH() || x2 > getCanvas().getWIDTH()){
            throw new ValidationException("Validation Exception: x1:"+ x1 + ", x2:"+ x2 + " co-ordinates can't be non negative or greater than canvas width: " + getCanvas().getWIDTH());
        }

        if(y1 < 0 || y2 < 0 || y1 > getCanvas().getHEIGHT() || y2 > getCanvas().getHEIGHT()){
            throw new ValidationException("Validation Exception: y1:"+ y1 + ", y2:"+ y2 + " co-ordinates can't be non negative or greater than canvas height: " + getCanvas().getHEIGHT());
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Assumption: Only Vertical or Horizontal line is supported to draw.
     * Length of a line = dist((x, y), (a, b)) = √(x1 - x2)² + (y1 - y2)²
     * if x1==x2 || y1==y2 then length can be calculated the distance of |y1-y2| and |x1-x2| resp.
     */
    public void draw() {
        //condition for vertical line
        if (x1==x2) {
            for(int row = y1; row <= y2; row++) {
                getCanvas().getCells()[row][x1] = CellContent.CROSS.symbol;
            }
        } else if (y1==y2) {
            for(int col = x1; col <= x2; col++) {
                getCanvas().getCells()[x1+1][col] = CellContent.CROSS.symbol;
            }
        } else {
            throw new ValidationException("Validation Exception: Only Horizontal/Vertical lines are supported: ("+x1+","+y1+") and ("+x2+","+y2+")");
        }
    }
}
