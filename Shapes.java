import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Parent class for all the shapes, it is abstract class as you cannot draw shapes, until the a shape type is decided.
 */
public abstract class Shapes{
    private double startX, startY, endX, endY;
    private Color strokeColor, fillColor;
    private int lineWidth;

    /**
     * This is the shape constructor.
     * @param startX needs the starting X cordinate from the mouse pressed handler.
     * @param startY needs the starting Y cordinate from the mouse pressed handler.
     * @param endX needs the ending X cordinate from the mouse released handler.
     * @param endY needs the ending X cordinate from the mouse released  handler.
     * @param strokeColor need the stroke Color value from the strokeColor picker.
     * @param lineWidth need the integer value from the line width text field.
     * @param fillColor need the stroke Color value from the lineColor picker.
     */

    public Shapes(double startX, double startY,double endX, double endY, Color strokeColor,  int lineWidth, Color fillColor){
        this.startX= startX;
        this.startY= startY;
        this.endX= endX;
        this.endY= endY;
        this.strokeColor = strokeColor;
        this.lineWidth =lineWidth;
        this.fillColor = fillColor;

    }
    //sets the StrokeColor
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    // sets the fillColor
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    //sets the line width
    public void setLineWidth(int lineWidth) { this.lineWidth = lineWidth;}

    //get the StrokeColor
    public Color getStrokeColor() {
        return strokeColor;
    }

    //gets the fillColor
    public Color getFillColor(){return fillColor;}

    //get the Line Width
    public int getLineWidth(){return lineWidth;}

    //get the starting X cordinate
    public double getStartX() {
        return startX;
    }

    //get the starting Y cordinate
    public double getStartY() {
        return startY;
    }

    //get the ending X cordinate
    public double getEndX() {
        return endX;
    }

    //get the ending Y cordinate
    public double getEndY() {
        return endY;
    }


    public abstract void draw (GraphicsContext gc);
}
