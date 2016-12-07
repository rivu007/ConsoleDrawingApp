package com.springer.paint;

import com.springer.paint.component.*;
import com.springer.paint.util.Cli;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Main Class - Entry point of the drawing app
 * Validation criteria: {@link Canvas} should be initialised before any component.
 * Supported components:
 *  @see Canvas
 *  @see Line
 *  @see Rectangle
 *  @see Fill
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.2
 */
public class StartDrawing {
    public static void main(String args[]) {
        List<Shape> shapes = new LinkedList<>();

        while(true) {
            Shape shape = Cli.parse();
            if(shape != null) {
                shapes.add(shape);
                Collections.reverse(shapes);
                shapes.forEach(Shape::draw);
            }
        }
    }
}
