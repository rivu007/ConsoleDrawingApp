package com.springer.paint.util;

import com.springer.paint.component.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * A simple utility class for command line interface
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.2
 */
public class Cli {

    private static Canvas canvas;

    /**
     * Parses the user input, validates it and init the component
     * <p>Validation Criteria:</p>
     *  - C (width) (height) : one uppercase character 'C' followed by 2 digits
     *  - R (x1) (y1) (x2) (y2) : one uppercase character 'R' followed by 4 digits
     *  - L (x1) (y1) (x2) (y2) : one uppercase character 'L' followed by 4 digits
     *  - B (x) (y) (symbol) : one uppercase character 'B' followed by 2 digits and one character
     *  - Q : to quit the app
     *
     * @return Shape
     */
    public static Shape parse() {
        Shape shape = null;
        System.out.print("Enter command:");
        try {
            Scanner scanner = new Scanner(System.in);
            String commandString = scanner.nextLine();
            String[] request = commandString.split("\\s+");  //the array of strings computed by space

            switch (Command.findCommandByAcronyms(request[0])){
                case CANVAS:
                    int[] canvasCoordinates = convertStringArrayToIntArray(Arrays.copyOfRange(request,1,request.length));
                    canvas = new Canvas(canvasCoordinates);
                    shape = canvas;
                    break;
                case LINE:
                    int[] lineCoordinates = convertStringArrayToIntArray(Arrays.copyOfRange(request,1,request.length));
                    shape = new Line(canvas, lineCoordinates);
                    break;
                case RECTANGLE:
                    int[] rectCoordinates = convertStringArrayToIntArray(Arrays.copyOfRange(request,1,request.length));
                    shape = new Rectangle(canvas, rectCoordinates);
                    break;
                case FILL:
                    shape = new Fill(canvas, Arrays.copyOfRange(request,1,request.length));
                    break;
                case EXIT:
                    System.out.print("Thank you for using the Drawing App! See you again.");
                    System.exit(0);
                default:
                    System.out.println("Command not supported: " + request[0]);
                    help();
            }
            return shape;
        } catch (NumberFormatException nfe){
            System.out.println("Only non negative numbers are allowed in args: " + nfe.getMessage());
            help();
        } catch (Exception e) {
            System.out.println("Sorry! can't parse the entered value. Try again: " + e.getMessage());
            help();
        }
        return shape;
    }

    public static int[] convertStringArrayToIntArray(String[] arr) {
        return Stream.of(arr).mapToInt(Integer::parseInt).toArray();
    }

    /**
     *  User manual for command list currently supported by the app.
     */
    private static void help() {
        System.out.println("Supported Command List:");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("To draw canvas press:     C <width> <height>");
        System.out.println("To draw rectangle press:  R <x1> <y1> <x2> <y2>");
        System.out.println("To draw Line press :      L <x1> <y1> <x2> <y2>");
        System.out.println("To fill canvas press :    B <x> <y> <c>");
        System.out.println("To quit press:            Q");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println();
    }

    public static void setCanvas(Canvas canvas) {
        Cli.canvas = canvas;
    }
}
