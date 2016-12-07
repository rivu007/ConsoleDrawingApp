package com.springer.paint.util;

import com.springer.paint.component.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class CliTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        Cli.setCanvas(null);
    }

    @Test()
    public void parse_canvasCommandTriggeredWithWrongParam_IncorrectUserInputMessageIsShown() throws Exception{
        systemInMock.provideLines("C width 4");
        Cli.parse();
        String expectedMessage = "Enter command:Only non negative numbers are allowed in args: For input string: \"width\"";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test()
    public void parse_lineCommandTriggeredWithoutCanvasCreated_showCanvasNotFoundException() throws Exception {
        systemInMock.provideLines("L 1 2 6 2");
        Cli.parse();
        String expectedMessage = "Canvas not found! Please draw canvas: C <width> <height>";

        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test
    public void parse_canvasCommandTriggered_shouldReturnCanvasObject() throws Exception {
        systemInMock.provideLines("C 20 4");
        Shape shape = Cli.parse();

        assertEquals(Canvas.class, shape.getClass());
    }

    @Test()
    public void parse_lineCommandTriggeredWithoutCoordinateParams_showsIllegalArgsExceptionMessage() throws Exception {
        systemInMock.provideLines("L 1 2");
        Cli.setCanvas(new Canvas(20, 4));
        Cli.parse();
        String expectedMessage = "Invalid argument supplied! Line command accept 4 args: x1, y1, x2, y2";

        assertTrue(outContent.toString().contains(expectedMessage));

    }

    @Test()
    public void parse_lineWidthGreaterThanCanvasWidth_showsValidationException() throws Exception {
        systemInMock.provideLines("L 21 25 21 30");
        Cli.setCanvas(new Canvas(10,4));
        Cli.parse();
        String expectedMessage = "Validation Exception: x1:21, x2:21 co-ordinates can't be non negative or greater than canvas width: 10";

        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test
    public void parse_lineCommandTriggered_shouldReturnLineObject() throws Exception {
        systemInMock.provideLines("L 1 2 1 4");
        Cli.setCanvas(new Canvas(40, 4));
        Shape shape = Cli.parse();

        assertEquals(Line.class, shape.getClass());
    }

    @Test()
    public void parse_rectangleeCommandTriggeredWithoutCoordinateParams_showsIllegalArgsExceptionMessage() throws Exception {
        systemInMock.provideLines("R 20");
        Cli.setCanvas(new Canvas(40, 4));
        Cli.parse();
        String expectedMessage = "Invalid args supplied! Rectangle command accept 4 args: x1, y1, x2, y2";

        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test()
    public void parse_rectangleWidthGreaterThanCanvasWidth_ValidationExceptionMessageIsShown() throws Exception {
        systemInMock.provideLines("R 50 60 70 80");
        Cli.setCanvas(new Canvas(4,4));
        Cli.parse();
        String expectedMessage = "Enter command:Sorry! can't parse the entered value. Try again: Validation Exception: x1: 50, x2: 70 co-ordinates can't be non negative or greater than canvas width: 4";

        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test
    public void parse_rectangleCommandTriggered_shouldReturnRectangleObject() throws Exception {
        systemInMock.provideLines("R 2 3 5 4");
        Cli.setCanvas(new Canvas(20, 4));
        Shape shape = Cli.parse();

        assertEquals(Rectangle.class, shape.getClass());
    }

    @Test
    public void parse_FillCommandTriggered_shouldReturnFillObject() throws Exception {
        systemInMock.provideLines("B 2 3 c");
        Cli.setCanvas(new Canvas(20, 4));
        Shape shape = Cli.parse();

        assertEquals(Fill.class, shape.getClass());
    }

    @Test()
    public void parse_fillWidthGreaterThanCanvasWidth_ValidationExceptionMessageIsShown() throws Exception {
        systemInMock.provideLines("B 50 60 c");
        Cli.setCanvas(new Canvas(4,4));
        Cli.parse();
        String expectedMessage = "Validation Exception: x:50 co-ordinate can't be non negative or greater than canvas width: 4";

        assertTrue(outContent.toString().contains(expectedMessage));
    }
}
