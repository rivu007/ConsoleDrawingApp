package com.springer.paint.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class CanvasTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Canvas canvas;

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
        canvas = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void canvas_initCanvasWithoutParam_valitationErrorIsShown() throws Exception {
        canvas = new Canvas();
    }

    @Test
    public void canvas_initCanvasWithParams_shouldReturnCanvasObject() throws Exception {
        canvas = new Canvas(10,5);

        assertNotNull(canvas);
        assertEquals(10, canvas.getWIDTH());
        assertEquals(5, canvas.getHEIGHT());
    }


}
