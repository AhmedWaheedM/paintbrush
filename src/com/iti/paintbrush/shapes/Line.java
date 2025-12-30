package com.iti.paintbrush.shapes;
import com.iti.paintbrush.enums.DrawMode;
import java.awt.*;
public class Line extends Shape {
    public Line(int x1, int y1, int x2, int y2, Color color, int thick, DrawMode drawMode) {
       super(x1, y1, x2, y2, color, thick, drawMode);
    }   
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D
        g2d.setColor(getColor());
        DrawMode drawMode = getDrawMode();

        if (drawMode == DrawMode.DOTTED){
            float[] dashPattern = {5f, 5f};  // 5 pixels on, 5 pixels off
            BasicStroke dashedStroke = new BasicStroke(
                getThick(),                // line width
                BasicStroke.CAP_BUTT,    // end cap style
                BasicStroke.JOIN_MITER,  // join style
                10f,                     // miter limit
                dashPattern,             // dash pattern
                0f                       // dash phase
            );
            g2d.setStroke(dashedStroke);
            g2d.drawLine(getX1(), getY1(), getX2(), getY2());
            
            // Reset stroke to default for other shapes
            g2d.setStroke(new BasicStroke());
        }
        else{
            // added varying thickh stter
            g2d.setStroke(new BasicStroke(getThick(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(getX1(), getY1(), getX2(), getY2());
        }




    }
}
