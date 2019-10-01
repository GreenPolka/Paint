import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * This line class is a child class of Shapes.
 */

public class Line extends Shapes {

    /**
     * This is the Line constructor.
     * @param startX needs the starting X cordinate from the mouse pressed handler.
     * @param startY needs the starting Y cordinate from the mouse pressed handler.
     * @param endX needs the ending X cordinate from the mouse released handler.
     * @param endY needs the ending X cordinate from the mouse released  handler.
     * @param strokeColor need the stroke Color value from the strokeColor picker.
     * @param lineWidth need the integer value from the line width text field.
     * @param fillColor need the stroke Color value from the lineColor picker, but it doesnt use it because a line doesnt need a fill color.
     */

    public Line(double startX, double startY,double endX, double endY, Color strokeColor,  int lineWidth, Color fillColor){
        super(startX, startY, endX, endY, strokeColor, lineWidth, fillColor);


    }

    /**
     * This overrides the draw method from the Shape class.
     * @param gc
     */

    @Override
    public void draw(GraphicsContext gc){
       gc.setStroke(getStrokeColor());
        gc.setLineWidth(getLineWidth());
        gc.strokeLine(getStartX(), getStartY(), getEndX(), getEndY());

    }
}
