package com.iti.paintbrush.shapes;
import java.awt.*;
public class Circle extends Shape {
    public Circle(int x1, int y1, int x2, int y2, Color color) {
       super(x1, y1, x2, y2, color);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        int rawDiameter = Math.abs(getX1() - getX2());
        int rawHeight = Math.abs(getY1() - getY2());

        int diameter = Math.max(rawDiameter, rawHeight);
        int x = getX1();
        int y = getY1();
        if (getX2() < getX1()) {
            x = getX1() - diameter;
        }
        if (getY2() < getY1()) {
            y = getY1() - diameter;
        }

        g.drawOval(x, y, diameter, diameter);
    }
}
