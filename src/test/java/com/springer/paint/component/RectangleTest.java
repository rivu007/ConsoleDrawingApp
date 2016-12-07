package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RectangleTest {

    private Canvas canvas;

    @After
    public void cleanUp() {
        canvas = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void rectangle_initRectangleWithoutParam_IllegalArgumentExceptionIsShown() throws Exception {
        canvas = new Canvas(20,4);
        int[] args = new int[]{};
        new Rectangle(canvas, args);
    }

    @Test(expected = NullPointerException.class)
    public void rectangle_initRectangleWithoutCanvas_NullPointerExceptionIsShown() throws Exception {
        int[] args = new int[]{1,2,1,4};
        new Rectangle(null, args);
    }

    @Test(expected = ValidationException.class)
    public void rectangle_initRectangleWithXCoordinateGreaterThanCanvasWidth_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        int[] args = new int[]{7,2,7,4};
        new Rectangle(canvas, args);
    }

    @Test(expected = ValidationException.class)
    public void rectangle_initRectangleWithYCoordinateGreaterThanCanvasWidth_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        int[] args = new int[]{1,7,1,9};
        new Rectangle(canvas, args);
    }

    @Test
    public void rectangle_initRectangleWithCanvasAndParams_success() throws Exception {
        canvas = new Canvas(20,4);
        int[] args = new int[]{1,2,3,4};
        Rectangle rectangle = new Rectangle(canvas, args);

        assertNotNull(rectangle);
        assertEquals(1, rectangle.x1, 0);
        assertEquals(2, rectangle.y1, 0);
        assertEquals(3, rectangle.x2, 0);
        assertEquals(4, rectangle.y2, 0);
    }
}
