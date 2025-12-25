package com.iti.paintbrush.shapes;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());

        int x = Math.min(getX1(), getX2());
        int y = Math.min(getY1(), getY2());
        int width = Math.abs(getX2() - getX1());
        int height = Math.abs(getY2() - getY1());
        if (isFilled()) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}
