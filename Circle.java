import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class is a child class of Shapes.
 */
public class Circle extends Shapes {

    /**
     * This is the Circle constructor.
     * @param startX needs the starting X cordinate from the mouse pressed handler.
     * @param startY needs the starting Y cordinate from the mouse pressed handler.
     * @param endX needs the ending X cordinate from the mouse released handler.
     * @param endY needs the ending X cordinate from the mouse released  handler.
     * @param strokeColor need the stroke Color value from the strokeColor picker.
     * @param lineWidth need the integer value from the line width text field.
     * @param fillColor need the stroke Color value from the lineColor picker.
     */
    public Circle(double startX, double startY,double endX, double endY, Color strokeColor,  int lineWidth, Color fillColor){
    super(startX,startY, endX, endY, strokeColor,lineWidth, fillColor);

}

    /**
     * This is draw method to draw a Circle, it overrides the draw method from shape class.
     * @param gc
     */
    public void draw(GraphicsContext gc){

    gc.setStroke(getStrokeColor());
    gc.setFill(getFillColor());
    gc.setLineWidth(getLineWidth());


    //draws a circle, when the user presses the mouse and drags it to lower right side of the canvas, traces the user's mouse
    if(getEndX()>getStartX()&&getEndY()>getStartY()){
        gc.fillOval(getStartX(), getStartY(), getEndX()-getStartX(), getEndY()-getStartY());
        gc.strokeOval(getStartX(),getStartY(),getEndX()-getStartX(), getEndY()-getStartY());
        System.out.println("lower right");
    }
    //draws a circle when the user presses the mouse and drags it to upper right side of the canvas,  traces the user's mouse
    else if (getEndX()>getStartX()&& getStartY()>getEndY()){
        gc.fillOval(getStartX(), getEndY(), getEndX()-getStartX(), getStartY()-getEndY());
        gc.strokeOval(getStartX(), getEndY(), getEndX()-getStartX(), getStartY()-getEndY());
        System.out.println("upper right");
    }
    //draws a circle, when the user presses the mouse and drags it to lower left side of the canvas, traces the user's mouse
    else if (getStartX()>getEndX() && getStartY()<getEndY()){
        gc.fillOval(getEndX(), getStartY(), getStartX()-getEndX(), getEndY()-getStartY());
        gc.strokeOval(getEndX(), getStartY(), getStartX()-getEndX(), getEndY()-getStartY());
        System.out.println("lower left");
    }
    // draws a circle, when the user preses the mouse and drags it to upper left side of the canvas traces the user's mouse
    else if (getStartX()>getEndX() && getEndY()<getStartY()){
        gc.fillOval(getEndX(), getEndY(), getStartX()-getEndX(), getStartY()-getEndY());
        gc.strokeOval(getEndX(), getEndY(), getStartX()-getEndX(), getStartY()-getEndY());
        System.out.println("upper left");

    }

}
}