package com.iti.paintbrush.ui;

import com.iti.paintbrush.interfaces.Drawable;
import com.iti.paintbrush.shapes.Rectangle;
import com.iti.paintbrush.shapes.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

    private ArrayList<Drawable> shapes; // List to store drawn shapes made with Drawable interface for adding JPEGs or any none shape later
    private ArrayList<Drawable> removedShapes; // For redo functionality
    private Shape currentShape; // Shape being currently drawn
    private Color currentColor; 
    private String currentMode; // e.g., "Rectangle", "Oval", etc. aka user selected shape via toolbar

    public DrawingPanel() {
        shapes = new ArrayList<>();
        removedShapes = new ArrayList<>();
        currentColor = Color.RED; //! static for now
        currentMode = "Rectangle"; //! static for now
        setBackground(Color.WHITE);

        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Drawable d : shapes) {
            d.draw(g);
        }

        if (currentShape != null) {
            currentShape.draw(g); 
        }
    }

    private class MouseHandler extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (currentMode.equals("Rectangle")) {
                currentShape = new Rectangle(e.getX(), e.getY(), e.getX(), e.getY(), currentColor);
            }
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                //Testing 
                //System.out.println("Anchor: " + currentShape.getX1() + " | Current: " + e.getX());
                repaint();
            }
        }
        //saving the shape on mouse release
        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX()); // Finalize the shape's second point
                currentShape.setY2(e.getY()); 
                shapes.add(currentShape);   // Adding the completed shape to the list
                currentShape = null;    //sending message to stop drawing
                repaint();
            }
        }
    } 
} 