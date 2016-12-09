# PaintApp - Have fun drawing in console

## Overview about the App:
A simple console version of drawing app. The app runs in an interactive mode and the user can
draw by issuing simple commands. Current workflow are follows:

1. Create a new canvas. _Remember without canvas created, drawing other component is not allowed_. User will get an error message about this:
```
    $ java -jar /Users/abhilash.ghosh/Desktop/PaintApp/working.build/PaintApp-1.2.jar
    Enter command:L 20 4 20 5
    Sorry! can't parse the entered value. Try again: Canvas not found! Please draw canvas: C <width> <height>
    Supported Command List:
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    To draw canvas press:     C <width> <height>
    To draw rectangle press:  R <x1> <y1> <x2> <y2>
    To draw Line press :      L <x1> <y1> <x2> <y2>
    To fill canvas press :    B <x> <y> <c>
    To quit press:            Q
    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    Enter command:
```

2. Start drawing on the canvas by issuing various commands (See supported command list section for more details). Some validation rules to be taken into consideration:
    1. Each command expects some command line **arguments** to be supplied. If not entered correctly user will get an IllegalArgument message with proper description:
    ``` 
       Enter command:L 3 15
       Sorry! can't parse the entered value. Try again: Invalid argument supplied! Line command accept 4 args: x1, y1, x2, y2
       Supported Command List:
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       To draw canvas press:     C <width> <height>
       To draw rectangle press:  R <x1> <y1> <x2> <y2>
       To draw Line press :      L <x1> <y1> <x2> <y2>
       To fill canvas press :    B <x> <y> <c>
       To quit press:            Q
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       Enter command:
    ```   
    2. The component's(`Rectangle`/`Line`/`Fill`) co-ordinates should not exceed canvas `width` and `height`. Else user gets a ValidationException message with proper description:
    ```
        Enter command:L 3 15 3 22
        Sorry! can't parse the entered value. Try again: Validation Exception: y1:15, y2:22 co-ordinates can't be non negative or greater than canvas height: 6
        Supported Command List:
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        To draw canvas press:     C <width> <height>
        To draw rectangle press:  R <x1> <y1> <x2> <y2>
        To draw Line press :      L <x1> <y1> <x2> <y2>
        To fill canvas press :    B <x> <y> <c>
        To quit press:            Q
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        Enter command:
    ```         
3. Once done quit the app by pressing `Q` and enjoy your drawing.

## Supported Command list:
| Command  | Description |
|-------------|-------------|
| `C w h` | Creates a canvas of width `w` and height `h`. |
| `L x1 y1 x2 y2` | Creates a new line from `(x1,y1)` to `(x2,y2)` using `x` characters. Current version supports only vertical and horizontal line. |
| `R x1 y1 x2 y2` | Creates a new rectangle, whose upper left corner is `(x1,y1)` and lower right corner is `(x2,y2)` using `x` characters |
| `B x y c` | Fills the entire area connected to `(x,y)` with color `c`. The behaviour is analogous to *bucket fill* in paint application. |
| `Q` | To quit the app. |

## Technology stack & libs used in the project:
1. Java 8
2. Maven - build automation tool and dependency management
3. JUnit - for test cases
4. System Rules - A collection of JUnit rules for testing code that uses java.lang.System

## Architecture Design:
The app is designed in component driven structure which is easy to extend. The `Canvas` class implements `Shape` interface and the components extends the abstract class `ShapeDecorator`
I have used the **Decorator Design Pattern** to design this app. [Click here to know more] (https://en.wikipedia.org/wiki/Decorator_pattern)

#### UML diagram:
 <img src="https://i.imgsafe.org/b294fa4077.png" width="350">

## Getting it running:
1. Extract the zip file `PaintApp-v1.2.zip`
2. `cd PaintApp`
3. Command to run the working build: `java -jar working.build/PaintApp-1.2.jar`
4. Enter command as specified in the above command list table. That's it.

or to package this app:

1. Run `mvn clean install` at the root of the repository, where the pom.xml file is located.
2. Look up in the `target` directory with the jar file `PaintApp-1.2.jar`  

## Screenshot:
<img src="https://i.imgsafe.org/b30afac451.png" width="500">

## How to run the test case:
The app is covered with 28 test cases.
1. Run `mvn test` at the root of the repository, where the pom.xml file is located.
2. Checkout the console log for the detailed report.

## Current version:
v1.2

## Contribution guidelines

* Writing tests
* Code review
* Other guidelines

## Who do I talk to?

* Abhilash Ghosh

