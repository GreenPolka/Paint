
import com.sun.corba.se.spi.ior.iiop.AlternateIIOPAddressComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.ArrayList;

import static javafx.application.Application.launch;


/**
 * * <pre>
 *  * Program: Assginment 8
 *  * Author:  Ruchu Adhikary
 *  * Date:   August 9, 2019
 *  *
 *  * Purpose: to create a Canvas where shapes can be drawn.
 *  *
 *  * I, Ruchu Adhikary, student number 000798092, certify that all code submitted is my own work;
 *  * that I have not copied it from any other source.  I also certify that I have not allowed
 *  *  my work to be copied by others.
 *  * </pre>,
 */


public class CanvasDemo extends Application {


    // TODO: Instance Variables for View Components and Model

    final double SCREEN_WIDTH = 1000; // Set the Screen Width
    final double SCREEN_HEIGHT = 800; // Set the Screen Height
    final double CONTROL_HEIGHT = 60; // Set the Control section Height
    private String shapeType =""; // Variable for shape selection
    private double startX, startY, endX, endY; // starting X coordinate, starting Y coordinate, ending X coordinate, ending Y coordinate respectively
    private int lineWidth =0; // variable for the line weight
    GraphicsContext baseGC, topGC; // Graphic layers to draw while the mouse is pressed, and another to draw the shape
    Shapes newShape; // Variable to create a new shape object

    private Button btnCircle, btnRectangle, btnLine, btnClearScreen, btnReset, btnUndo; // buttons for the control screen
    private Label lbllineColour, lblfillColour, lblLineWidth; // labels for the control screen buttons
    private Color lineColor = Color.BLACK; //Color picker for the line color
    private Color fillColor = Color.GRAY; // Color picker for the fill color
    private ColorPicker outlineColorPicker, fillColorPicker;
    private TextField tfLineWidth; // text field to input line width

    // Create a new ArrayList for the the shapes that is being drawn in the canvas
    private ArrayList<Shapes> shapesDrawn = new ArrayList<>();


    // TODO: Private Event Handlers and Helper Methods

    /**
     * Button handling, if the user presses the Circle Button, this method gets called.
     * @param e set the shape type to Circle
     */
    public void setCircle(ActionEvent e){
        shapeType ="Circle";
        System.out.println("Circle selected, shapeType: "+ shapeType);
    }

    /**
     * Button handling, if the user presses the Rectangle Button, this method gets called.
     * @param e set the shape type to Rectangle
     */
    public void setRectangle(ActionEvent e){
        shapeType ="Rectangle";
        System.out.println("Rectangle selected, shapeType: "+ shapeType);
    }

    /**
     * Button handling, if the user presses the Line Button, this method gets called.
     * @param e set the shape type to Line
     */
    public void setLine(ActionEvent e){
        shapeType="Line";
        System.out.println("Line selected, shapeType: "+ shapeType);
    }
    /**
     * Button handling, if the user presses the Clear the Screen, this method gets called.
     * @param e Clears the screen
     */
    public void setClearScreen(ActionEvent e){

        baseGC.setFill(Color.WHITE); // Fills the base GC to white.
        baseGC.fillRect(0,CONTROL_HEIGHT,SCREEN_WIDTH,SCREEN_HEIGHT-CONTROL_HEIGHT); // Fill the Rectangle
        topGC.clearRect(0, CONTROL_HEIGHT,SCREEN_WIDTH,SCREEN_HEIGHT); // Clears the transperate GC
        shapesDrawn.clear(); // Clear the ArrayList

        System.out.println("Clear Screen selected");
    }
    /**
     * Button handling, if the user presses the Reset Parameters, this method gets called.
     * @param e resets the parameters i.e. fill color, line color, line width
     */

    public void setResetParameters(ActionEvent e) {
        // clear the Base GC
        baseGC.setFill(Color.WHITE);
        baseGC.fillRect(0,CONTROL_HEIGHT,SCREEN_WIDTH,SCREEN_HEIGHT-CONTROL_HEIGHT);
        // clear the Top GC
        topGC.setFill(Color.WHITE);
        topGC.clearRect(0,CONTROL_HEIGHT,SCREEN_WIDTH,SCREEN_HEIGHT-CONTROL_HEIGHT);


        /**This for loop goes through the ArrayList and sets the stroke color to whatever the picker has,
         * fill color to the fill color picker has, line width to what the text fill has and then it draws all the shape.
         */
        for(int i =0; i<shapesDrawn.size();i++){
            shapesDrawn.get(i).setStrokeColor(outlineColorPicker.getValue());
            shapesDrawn.get(i).setFillColor(fillColorPicker.getValue());
            shapesDrawn.get(i).setLineWidth(getLineWidth());
            shapesDrawn.get(i).draw(baseGC);
        }

        // This the exception for the Line width value, if it not an integer it will display a an Alter.
        try {
            int lineWidth = Integer.parseInt(tfLineWidth.getText());
        }
        catch (NumberFormatException ex)
        {
            new Alert(Alert.AlertType.ERROR,
                    "Could not convert " + tfLineWidth.getText() + " to an integer").showAndWait();
        }
        // This if statement will check if the line width is more than 1
        if(getLineWidth()<=1){
            new Alert(Alert.AlertType.ERROR,
                    "Please enter a postive integer:  " + tfLineWidth.getText()).showAndWait(); }


    }

    /**
     * Button handling, if the user presses the Undo, this method gets called.
     * @param e This removes the last shape drawn
     */

    public void setBtnUndo(ActionEvent e) {
// this try check how many elements are there in the array list and then clears the screen.

try{
            shapesDrawn.remove( shapesDrawn.size()-1);
            baseGC.setFill(Color.WHITE);
            baseGC.fillRect(0,CONTROL_HEIGHT,SCREEN_WIDTH,SCREEN_HEIGHT-CONTROL_HEIGHT);

// this for loop draws the shapes back in the canvas

            for(int i =0; i<shapesDrawn.size();i++){
                shapesDrawn.get(i).draw(baseGC);
            }
   }
// catch to show an alert that we are in our out of bound.
        catch (ArrayIndexOutOfBoundsException ex){
            new Alert(Alert.AlertType.ERROR,
                    "Cannot Undo, out of bound!").showAndWait();
        }
    }
    // mouse handling, when the mouse is clicked this method is activated.
    private void pressHandler(MouseEvent me) {
        startX=me.getX(); // this is the starting X coordinate of the mouse click
        startY=me.getY(); // this is the starting Y coordinate of the mouse click
        // this try will check for the value in text box to confirm if it is an integer or not, it isnt not, it will give an Error alter
        try{
            int lineWidth = Integer.parseInt(tfLineWidth.getText());
        }
        catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR,
                    "Please enter a postive numberic value for line width! /n" +
                            tfLineWidth.getText() + "is not a number value.").showAndWait();}
       // if statement to check if the line width is greater than 1 or not.
        if(getLineWidth()<1){
            new Alert(Alert.AlertType.ERROR,
                    "Please enter a postive integer:  " + tfLineWidth.getText()).showAndWait(); }

        System.out.println("Pressed at "+startX+" / "+startY+".");
    }

    // mouse handling, when the mouse is released this method is activated.
    private void releaseHandler(MouseEvent me) {
        endX=me.getX();
        endY=me.getY();

        // if statement to check if the mouse was released within the canvas or out.
        if ((endX>0 && endX<SCREEN_WIDTH && endY >0&&endY<SCREEN_HEIGHT)){

            // if the mouse was released in within the canvas the appropriate shape constructor is called and the shape is drawn.
            if (shapeType.equals("Circle")) {
                newShape = new Circle(startX, startY, endX, endY, outlineColorPicker.getValue(), getLineWidth(), fillColorPicker.getValue());
                newShape.draw(baseGC);
            } else if (shapeType.equals("Rectangle")) {
                newShape = new Rectangle(startX, startY, endX, endY, outlineColorPicker.getValue(), getLineWidth(), fillColorPicker.getValue());
                newShape.draw(baseGC);
            } else if (shapeType.equals("Line")) {
                newShape = new Line(startX, startY, endX, endY, outlineColorPicker.getValue(), getLineWidth(), fillColorPicker.getValue());
                newShape.draw(baseGC);
            } else { // this else will show an Error message no shape was selected.
                new Alert(Alert.AlertType.ERROR,
                        "Please select a shape to begin drawing!").showAndWait();
            }
        // once the shape is drawn, it is added to the arraylist
            shapesDrawn.add(newShape);
            //for (Shapes sh : shapesDrawn )
            // System.out.println(sh);}
        }
        else{ // this else will show an Error message if the mouse was released outside the canvas.
            new Alert(Alert.AlertType.ERROR, "You cannot draw outside the Canvas").showAndWait();
            topGC.clearRect(0, CONTROL_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
        }

        System.out.println("Released at "+endX+" / "+endY+".");
    }

    // mouse handling, when the mouse is dragged this method is activated.
    private void dragHandler(MouseEvent me) {
    topGC.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); // this clears the top layer before anything else
    endX=me.getX();
    endY=me.getY();

    // if statement to find which shape to draw, so that you can see the shape as the mouse is dragged in the canvas
        if (shapeType.equals("Circle")) {
            newShape = new Circle(startX, startY, endX, endY, outlineColorPicker.getValue(),getLineWidth(), fillColorPicker.getValue());
            newShape.draw(topGC);
        }
        else if (shapeType.equals("Rectangle")){
            newShape = new Rectangle(startX,startY,endX,endY, outlineColorPicker.getValue(),getLineWidth() ,fillColorPicker.getValue());
            newShape.draw(topGC);
        }
        else if (shapeType.equals("Line")){
            newShape = new Line(startX,startY,endX,endY, outlineColorPicker.getValue(),getLineWidth(),fillColorPicker.getValue() ) ;
            newShape.draw(topGC);
        }


        System.out.println("Drag at "+endX+" / "+endY+".");
    }
    // method to return the integer value from the text field called lineWidthl
    private int getLineWidth (){
        return Integer.parseInt(tfLineWidth.getText());
    }

    //  @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // set the size here
        stage.setTitle("Assignment 8 - Paint App"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        // 2. Create the GUI components
        Canvas baseC = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT); // creating a new base canvas
        Canvas topC = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT); // creating a top canvas, it will be a transparent one, we will not draw on it.

        // Control Center

        btnCircle = new Button("Elipse"); // Circle botton to display text Elipse
        btnRectangle = new Button("Rectangle"); //Rectangle button to display text Rectangle
        btnLine = new Button("Line"); // Line button to display text Line
        btnClearScreen = new Button("Clear Screen"); // Clear button to display text Clear
        btnReset = new Button("Reset Parameters");// Reset button to display text Reset Parameters
        btnUndo = new Button ("Undo"); //  Undo button to display text Undo
        lbllineColour = new Label ("Line Color: "); // Label for Line Color
        outlineColorPicker = new ColorPicker(Color.GRAY); // Outline Color picker
        lblfillColour = new Label ("Fill Color: "); // Label for Fill Color
        fillColorPicker = new ColorPicker(Color.GREEN); // Fill Color picker
        lblLineWidth = new Label ("Line Width"); // Label for Line Width
        tfLineWidth = new TextField("1"); // text field for line width


        // Circle Button formatting
        btnCircle.setPrefWidth(75);
        btnCircle.setPrefHeight(25);
        btnCircle.relocate(10, 0);

        // Rectangle Button formatting
        btnRectangle.setPrefWidth(100);
        btnRectangle.setPrefHeight(25);
        btnRectangle.relocate(95, 0);

        // Line Button formatting
        btnLine.setPrefWidth(75);
        btnLine.setPrefHeight(25);
        btnLine.relocate(205, 0);

        // Clear Screen Button formatting
        btnClearScreen.setPrefWidth(75);
        btnClearScreen.setPrefHeight(25);
        btnClearScreen.relocate(290, 0);

        // Reset Button formatting
        btnReset.setPrefHeight(100);
        btnReset.setPrefHeight(25);
        btnReset.relocate(375, 30);

        //Undo Button formatting
        btnUndo.setPrefWidth(100);
        btnUndo.setPrefHeight(25);
        btnUndo.relocate(800, 0);

        //Positioning Label for outline Color
       lbllineColour.relocate( 5, 35);

       //Outline Color picker formatting
        outlineColorPicker.setPrefWidth(100);
        outlineColorPicker.setPrefHeight(25);
       outlineColorPicker.relocate(80, 30);

       // Positioning Label for fill Color
       lblfillColour.relocate(190, 35);

       //Shape fill colour picker formatting
        fillColorPicker.setPrefWidth(100);
        fillColorPicker.setPrefHeight(25);
       fillColorPicker.relocate(250, 30);

       //Positioning Label for line width
        lblLineWidth.relocate(375, 5);

       //text box for line width
        tfLineWidth.setPrefWidth(50);
       tfLineWidth.relocate(475, 0);



        // 3. Add components to the root
        root.getChildren().addAll(baseC, topC, btnCircle, btnRectangle, btnLine, btnReset, btnClearScreen, btnUndo,
                lbllineColour, outlineColorPicker,lblfillColour, fillColorPicker, lblLineWidth, tfLineWidth);

        // 4. Configure  the components (colors, fonts, size, location)
        baseGC = baseC.getGraphicsContext2D();
        topGC = topC.getGraphicsContext2D();

        baseGC.setFill(Color.WHITE);
        baseGC.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // 5. Add Event Handlers and do final setup

        btnCircle.setOnAction(this:: setCircle); // Action handler for Circle button, this calls the setCircle method.
        btnRectangle.setOnAction(this:: setRectangle); // Action handler for Rectangle button, this calls the setRectangle method.
        btnLine.setOnAction(this:: setLine); // Action handler for Line button, this calls the setLine method.
        btnClearScreen.setOnAction(this::setClearScreen); // Action handler for Clear button, this calls the setClearSceen method.
        btnReset.setOnAction(this::setResetParameters);// Action handler for Reset button, this calls the setResetParameters method.
        btnUndo.setOnAction(this::setBtnUndo); // Action handler for Undo button, this calls the setBtnUndo method.

        topC.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler); // Mouse Handler, this calls the pressHandler method when mouse is pressed.
        topC.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler); // Mouse Handler, this calls the releaseHandler method when mouse is released.
        topC.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler); // Mouse Handler, this calls the dragHandler method when mouse is dragged.
        //Rectangle rect = new Rectangle(500,400,600,900, Color.RED, 50, Color.PINK);
        //rect.draw(topGC);

        // 6. Show the stage
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
