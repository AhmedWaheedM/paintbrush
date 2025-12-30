package com.iti.paintbrush.shapes;
import com.iti.paintbrush.enums.DrawMode;
import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, Color color, DrawMode drawMode) {
        super(x1, y1, x2, y2, color, drawMode);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D
        g2d.setColor(getColor());
        DrawMode drawMode = getDrawMode();

        int x = Math.min(getX1(), getX2());
        int y = Math.min(getY1(), getY2());
        int width = Math.abs(getX2() - getX1());
        int height = Math.abs(getY2() - getY1());


        if (drawMode == DrawMode.SOLID){
            g2d.drawRect(x, y, width, height);
        }
        else if (drawMode == DrawMode.FILLED){
            g2d.fillRect(x, y, width, height);
        }
        else{
            float[] dashPattern = {5f, 5f};  // 5 pixels on, 5 pixels off
            BasicStroke dashedStroke = new BasicStroke(
                1f,                // line width
                BasicStroke.CAP_BUTT,    // end cap style
                BasicStroke.JOIN_MITER,  // join style
                10f,                     // miter limit
                dashPattern,             // dash pattern
                0f                       // dash phase
            );
            g2d.setStroke(dashedStroke);
            g2d.drawRect(x, y, width, height);
            
            // Reset stroke to default for other shapes
            g2d.setStroke(new BasicStroke());
        }


    }
}
