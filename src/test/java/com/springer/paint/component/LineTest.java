package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LineTest {

    private Canvas canvas;

    @After
    public void cleanUp() {
        canvas = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void line_initLineWithoutParam_IllegalArgumentExceptionIsShown() throws Exception {
        canvas = new Canvas(20,4);
        int[] args = new int[]{};
        new Line(canvas, args);
    }

    @Test(expected = NullPointerException.class)
    public void line_initLineWithoutCanvas_NullPointerExceptionIsShown() throws Exception {
        int[] args = new int[]{1,2,1,4};
        new Line(null, args);
    }

    @Test(expected = ValidationException.class)
    public void line_initLineWithXCoordinateGreaterThanCanvasWidth_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        int[] args = new int[]{7,2,7,4};
        new Line(canvas, args);
    }

    @Test(expected = ValidationException.class)
    public void line_initLineWithYCoordinateGreaterThanCanvasWidth_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        int[] args = new int[]{1,7,1,9};
        new Line(canvas, args);
    }

    @Test
    public void line_initLineWithCanvasAndParams_success() throws Exception {
        canvas = new Canvas(15,12);
        int[] args = new int[]{1,7,1,9};
        Line line = new Line(canvas, args);

        assertNotNull(line);
        assertEquals(1, line.x1, 0);
        assertEquals(7, line.y1, 0);
        assertEquals(1, line.x2, 0);
        assertEquals(9, line.y2, 0);
    }
}
