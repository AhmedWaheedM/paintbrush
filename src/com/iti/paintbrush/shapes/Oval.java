package com.iti.paintbrush.shapes;
import java.awt.*;
public class Oval extends Shape {
    public Oval(int x1, int y1, int x2, int y2, Color color) {
       super(x1, y1, x2, y2, color);

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        int x = Math.min(getX1(), getX2());
        int y = Math.min(getY1(), getY2());
        int width = Math.abs(getX1() - getX2());
        int height = Math.abs(getY1() - getY2());
        g.drawOval(x, y, width, height);
    }
}
