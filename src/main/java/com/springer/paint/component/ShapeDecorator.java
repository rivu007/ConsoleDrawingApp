package com.springer.paint.component;

/**
 * @author Abhilash Ghosh
 * @since 1.1
 * @version 1.2
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        if(!(shape instanceof Canvas)){
            throw new NullPointerException("Canvas not found! Please draw canvas: C <width> <height>");
        }
        this.shape = shape;
    }

    public void draw() {
        shape.draw();
    }

    public Canvas getCanvas() {
        return (Canvas) shape;
    }

}
