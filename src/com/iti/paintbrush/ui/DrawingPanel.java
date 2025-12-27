package com.iti.paintbrush.ui;

import com.iti.paintbrush.enums.ShapeMode;
import com.iti.paintbrush.interfaces.Drawable;
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
    private ShapeMode currentMode; // Using Enum now!
    private BufferedImage backgroundImage;
    private BufferedImage backupImage;

    public DrawingPanel() {
        shapes = new ArrayList<>();
        removedShapes = new ArrayList<>();
        currentColor = Color.RED;
        currentMode = ShapeMode.CIRCLE; // Default
        setBackground(Color.WHITE);

        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    // --- State Setters ---
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void setCurrentMode(ShapeMode mode) {
        this.currentMode = mode;
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
            // Using the Factory Pattern!
            currentShape = ShapeFactory.createShape(currentMode, e.getX(), e.getY(), currentColor);
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (currentShape != null) {
                currentShape.setX2(e.getX());
                currentShape.setY2(e.getY());
                shapes.add(currentShape);
                currentShape = null;
                repaint();
            }
        }
    }
}