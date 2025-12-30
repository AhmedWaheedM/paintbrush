package com.iti.paintbrush.utils;

import com.iti.paintbrush.enums.DrawMode;
import com.iti.paintbrush.enums.ShapeMode;
import com.iti.paintbrush.shapes.*;
import java.awt.Color;

public class ShapeFactory {
    public static Shape createShape(ShapeMode mode, int x, int y, Color color, DrawMode drawMode) {
        return switch (mode) {
            case RECTANGLE -> new Rectangle(x, y, x, y, color, drawMode);
            case SQUARE    -> new Square(x, y, x, y, color, drawMode);
            case OVAL      -> new Oval(x, y, x, y, color, drawMode);
            case CIRCLE    -> new Circle(x, y, x, y, color, drawMode);
            case LINE      -> new Line(x, y, x, y, color, drawMode);
            case FREE_HAND -> new FreeHand(color, drawMode);
            case ERASER -> new Eraser();
            default        -> null; 
        };
    }
}