package com.iti.paintbrush.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FreeHand extends Shape {
    // has thickness 5 statically
    private List<int[]> points; // Arraylist of int arrays length 2 [x,y]
    private int thickness = 3;
    
    public FreeHand(Color color) {
        super(0, 0, 0, 0, color);
        this.points = new ArrayList<>();
    }
    
    public void addPoint(int x, int y) {
        points.add(new int[]{x, y});
    }
    
    @Override
    public void draw(Graphics g) {
        if (points.isEmpty()) return;
        
        g.setColor(getColor());
        
        // Draw each point as a small oval
        /* 
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            g.fillOval(x - thickness/2, y - thickness/2, thickness, thickness);
        } 
          */  
        
        // ersm lines ben l points badal ma trsm ovals kter gnb ba3d
        if (points.size() >= 2) {
            
            for (int i = 0; i < points.size() - 1; i++) {
                int[] point1 = points.get(i);
                int[] point2 = points.get(i + 1);
                int x1 = point1[0];
                int y1 = point1[1];
                int x2 = point2[0];
                int y2 = point2[1];
                g.drawLine(x1, y1, x2, y2);
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