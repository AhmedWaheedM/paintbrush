package com.iti.paintbrush.shapes;
import com.iti.paintbrush.enums.DrawMode;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeHand extends Shape {
    private List<int[]> points; // Arraylist of int arrays length 2 [x,y]
    
    public FreeHand(Color color, int thick, DrawMode drawMode) {
        super(0, 0, 0, 0, color, thick, drawMode);
        this.points = new ArrayList<>();
    }
    
    public void addPoint(int x, int y) {
        points.add(new int[]{x, y});
    }
    
    @Override
    public void draw(Graphics g) {
        if (points.isEmpty()) return;
        
        Graphics2D g2d = (Graphics2D) g;  // Cast to Graphics2D
        g2d.setColor(getColor());
        DrawMode drawMode = getDrawMode();

        // added varying thickh stter
        g2d.setStroke(new BasicStroke(getThick(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        // Draw each point as a small oval
        /* 
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            g.fillOval(x - thickness/2, y - thickness/2, thickness, thickness);
        } 
          */  
        

        // ersm lines ben l points badal ma trsm ovals kter gnb ba3d
        if (drawMode == DrawMode.DOTTED && points.size() >= 2){
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
            
            
            for (int i = 0; i < points.size() - 1; i++) {
                int[] point1 = points.get(i);
                int[] point2 = points.get(i + 1);
                int x1 = point1[0];
                int y1 = point1[1];
                int x2 = point2[0];
                int y2 = point2[1];
                g2d.drawLine(x1, y1, x2, y2);
            }
            // Reset stroke to default for other shapes
            g2d.setStroke(new BasicStroke());
        }
        else if (points.size() >= 2){ 
            for (int i = 0; i < points.size() - 1; i++) {
                int[] point1 = points.get(i);
                int[] point2 = points.get(i + 1);
                int x1 = point1[0];
                int y1 = point1[1];
                int x2 = point2[0];
                int y2 = point2[1];
                g2d.drawLine(x1, y1, x2, y2);
            }
        }   

    }
    
    public List<int[]> getPoints() {
        return points;
    }
}