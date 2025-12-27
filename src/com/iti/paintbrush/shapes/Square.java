package com.iti.paintbrush.shapes;
import java.awt.*;
public class Square extends Shape {
    public Square(int x1, int y1, int x2, int y2, Color color) {
       super(x1, y1, x2, y2, color);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        int rawWidth = Math.abs(getX1() - getX2());
        int rawHeight = Math.abs(getY1() - getY2());

        int sideLength = Math.max(rawWidth, rawHeight);
        int x = getX1();
        int y = getY1();
        if (getX2() < getX1()) {
            x = getX1() - sideLength;
        }
        if (getY2() < getY1()) {
            y = getY1() - sideLength;
        }

        g.drawRect(x, y, sideLength, sideLength);
    }
}
