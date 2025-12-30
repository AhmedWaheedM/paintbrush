package com.iti.paintbrush.shapes;
import com.iti.paintbrush.enums.DrawMode;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeHand extends Shape {
    // has thickness 5 statically
    private List<int[]> points; // Arraylist of int arrays length 2 [x,y]
    private int thickness = 3;
    
    public FreeHand(Color color, DrawMode drawMode) {
        super(0, 0, 0, 0, color, drawMode);
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
                1f,                // line width
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
    
    public int getThickness() {
        return thickness;
    }
    
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
    
    public List<int[]> getPoints() {
        return points;
    }
}