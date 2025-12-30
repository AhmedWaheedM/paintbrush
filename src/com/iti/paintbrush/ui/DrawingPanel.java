package com.iti.paintbrush.ui;

import com.iti.paintbrush.enums.DrawMode;
import com.iti.paintbrush.enums.ShapeMode;
import com.iti.paintbrush.interfaces.Drawable;
import com.iti.paintbrush.shapes.*;
import com.iti.paintbrush.shapes.Shape;
import com.iti.paintbrush.utils.FileUtils;
import com.iti.paintbrush.utils.ShapeFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawingPanel extends JPanel {

    private ArrayList<Drawable> shapes;
    private ArrayList<Drawable> removedShapes;
    private ArrayList<Drawable> backupShapes;
    private Shape currentShape;
    private Color currentColor;
    private DrawMode currentDrawMode;
    private ShapeMode currentShapeMode; // Using Enum now!
    private BufferedImage backgroundImage;
    private BufferedImage backupImage;
    private int currentThick = 2;

    public DrawingPanel() {
        shapes = new ArrayList<>();
        removedShapes = new ArrayList<>();
        currentColor = Color.BLACK;
        currentShapeMode = ShapeMode.FREE_HAND; // Changed default to FREE_HAND
        currentDrawMode = DrawMode.SOLID; // default line type is solid
        setBackground(Color.WHITE);

        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    // --- State Setters ---
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void setCurrentShapeMode(ShapeMode mode) {
        this.currentShapeMode = mode;
    }

    public void setDrawMode( DrawMode drawmode){
        this.currentDrawMode = drawmode;
    }

    public void setCurrentThick(int thick){
        this.currentThick = thick;
    }

    // --- Action Methods ---

    // --- clears all (hard reset)
    public void reset() {
        backupShapes = new ArrayList<>(shapes);
        backupImage = backgroundImage;
        shapes.clear();
        removedShapes.clear();
        backgroundImage = null;
        repaint();
    }
        // --- clear shapes keeps background image
    public void clearAll() {
        backupShapes = new ArrayList<>(shapes);
        shapes.clear();
        removedShapes.clear();
        repaint();
    }

    public void undo() {
        if (!shapes.isEmpty()) {
            removedShapes.add(shapes.remove(shapes.size() - 1));
            repaint();
        }
        else if(backupShapes!=null && !backupShapes.isEmpty()){
            shapes.addAll(backupShapes);
            backgroundImage=backupImage;
            backupShapes=null;
            backupImage=null;
            repaint();
        }
    }

    public void redo() {
        if (!removedShapes.isEmpty()) {
            shapes.add(removedShapes.remove(removedShapes.size() - 1));
            repaint();
        }
    }


    // --- File Operations (Delegated to FileUtils) ---
    public void saveProject(File file) throws IOException {
        FileUtils.saveProject(file, shapes);
    }

    public void loadProject(File file) throws IOException, ClassNotFoundException {
        this.shapes = FileUtils.loadProject(file);
        this.removedShapes.clear();
        repaint();
    }

    public void exportImage(File file) throws IOException {
        FileUtils.exportImage(file, this);
    }

    public void importImage(File file) throws IOException {
        this.backgroundImage = ImageIO.read(file);
        repaint();
    }

    // --- Painting Logic ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. Draw Background Image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // 2. Draw History
        for (Drawable d : shapes) {
            d.draw(g);
        }

        // 3. Draw Preview
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    // --- Inner Mouse Handler ---
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (currentShapeMode == ShapeMode.FREE_HAND) {
                
                currentShape = new FreeHand(currentColor,currentThick, currentDrawMode);
                ((FreeHand) currentShape).addPoint(e.getX(), e.getY());
                shapes.add(currentShape); // Add to shapes immediately
            }
            else if (currentShapeMode == ShapeMode.ERASER) {
                
                currentShape = new Eraser(currentThick);
                ((Eraser) currentShape).addPoint(e.getX(), e.getY());
                shapes.add(currentShape); // Add to shapes immediately
            } 
            else {
                // Using the Factory Pattern for other shapes
                currentShape = ShapeFactory.createShape(currentShapeMode, e.getX(), e.getY(), currentColor, currentThick, currentDrawMode);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            
            if (currentShape != null) {
                if (currentShapeMode == ShapeMode.FREE_HAND) {
                    ((FreeHand) currentShape).addPoint(e.getX(), e.getY());
                } 
                else if (currentShapeMode == ShapeMode.ERASER) {
                    ((Eraser) currentShape).addPoint(e.getX(), e.getY());
                }
                else{
                    currentShape.setX2(e.getX());
                    currentShape.setY2(e.getY());
                }
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape != null) {
                if (currentShapeMode == ShapeMode.FREE_HAND) {
                    
                    ((FreeHand) currentShape).addPoint(e.getX(), e.getY());
                }
                else if (currentShapeMode == ShapeMode.ERASER) {
                    
                    ((Eraser) currentShape).addPoint(e.getX(), e.getY());
                } 
                else {
                    
                    currentShape.setX2(e.getX());
                    currentShape.setY2(e.getY());
                    shapes.add(currentShape);
                }
                currentShape = null;
                repaint();
            }
        }
    }
}