package com.springer.paint.component;

import com.springer.paint.exception.ValidationException;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FillTest {

    private Canvas canvas;

    @After
    public void cleanUp() {
        canvas = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void fill_initFillWithoutParam_IllegalArgumentExceptionIsShown() throws Exception {
        canvas = new Canvas(20,4);
        String[] args = new String[]{};
        new Fill(canvas, args);
    }

    @Test(expected = NullPointerException.class)
    public void fill_initFillWithoutCanvas_NullPointerExceptionIsShown() throws Exception {
        String[] args = new String[]{"6","2","g"};
        new Fill(null, args);
    }

    @Test(expected = ValidationException.class)
    public void fill_initFillWithXCoordinateGreaterThanCanvasWidth_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        String[] args = new String[]{"6","2","g"};
        new Fill(canvas, args);
    }

    @Test(expected = ValidationException.class)
    public void fill_initFillWithYCoordinateGreaterThanCanvasHeight_ValidationExceptionIsShown() throws Exception {
        canvas = new Canvas(4,5);
        String[] args = new String[]{"2","9","g"};
        new Fill(canvas, args);
    }

    @Test
    public void fill_initFillWithCanvasAndParams_success() throws Exception {
        canvas = new Canvas(15,12);
        String[] args = new String[]{"1","7","c"};
        Fill fill = new Fill(canvas, args);

        assertNotNull(fill);
        assertEquals(1, fill.x);
        assertEquals(7, fill.y);
        assertEquals(args[2], fill.color);

    }
}
