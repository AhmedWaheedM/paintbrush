package com.iti.paintbrush.shapes;
import com.iti.paintbrush.enums.DrawMode;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Eraser extends Shape {
    
    // uses color white statically
    // has thickness 10 statically
    private List<int[]> points; // Arraylist of int arrays length 2 [x,y]
    private int thickness = 10;
    
    public Eraser() {
        super(0, 0, 0, 0, Color.WHITE, DrawMode.SOLID);
        this.points = new ArrayList<>();
    }
    
    public void addPoint(int x, int y) {
        points.add(new int[]{x, y});
    }
    
    @Override
    public void draw(Graphics g) {
        if (points.isEmpty()) return;      
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        // DA 3SHAN YB2A VARYING THICKNESS
        g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));  
    
        
        // Draw each point as a small oval
         
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            g.fillOval(x - thickness/2, y - thickness/2, thickness, thickness);
        } 
            

        // ersm lines ben l points badal ma trsm ovals kter gnb ba3d
        if (points.size() >= 2) {
            
            for (int i = 0; i < points.size() - 1; i++) {
                int[] point1 = points.get(i);
                int[] point2 = points.get(i + 1);
                int x1 = point1[0];
                int y1 = point1[1];
                int x2 = point2[0];
                int y2 = point2[1];
                g2d.drawLine(x1, y1, x2, y2); 
            }
            g2d.setStroke(new BasicStroke()); 
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