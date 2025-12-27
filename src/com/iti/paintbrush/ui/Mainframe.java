package com.iti.paintbrush.ui;

import com.iti.paintbrush.enums.ShapeMode;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Mainframe extends JFrame {

    private DrawingPanel drawingPanel;
    private JFileChooser fileChooser;

    public Mainframe() {
        // 1. Frame Setup
        setTitle("Paint Brush - By ITI");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 2. Initialize Components
        drawingPanel = new DrawingPanel();
        fileChooser = new JFileChooser();

        // 3. Build UI using Helper Methods
        JPanel toolbar = createToolbar();
        JMenuBar menuBar = createMenuBar();

        // 4. Add to Frame
        this.setJMenuBar(menuBar);
        this.add(toolbar, BorderLayout.NORTH);
        this.add(drawingPanel, BorderLayout.CENTER);
    }

    // --- Helper: Build the Toolbar ---
    private JPanel createToolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.setBackground(Color.LIGHT_GRAY);

        // -- Colors --
        toolbar.add(new JLabel("Colors: "));
        toolbar.add(createColorButton("Red", Color.RED));
        toolbar.add(createColorButton("Green", Color.GREEN));
        toolbar.add(createColorButton("Blue", Color.BLUE));
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));

        // -- Shapes --
        toolbar.add(new JLabel("Shapes: "));
        ButtonGroup shapeGroup = new ButtonGroup(); // Ensures only one shape is active
        toolbar.add(createShapeButton("Rect", ShapeMode.RECTANGLE, shapeGroup));
        toolbar.add(createShapeButton("Oval", ShapeMode.OVAL, shapeGroup));
        toolbar.add(createShapeButton("Line", ShapeMode.LINE, shapeGroup));
        toolbar.add(createShapeButton("Square", ShapeMode.SQUARE, shapeGroup));
        toolbar.add(createShapeButton("Circle", ShapeMode.CIRCLE, shapeGroup));
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));

        // -- Actions --
        JButton btnClear = new JButton("Clear");
        JButton btnReset = new JButton("Reset");
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");

        btnClear.addActionListener(e -> drawingPanel.clearAll()); // Assuming you renamed ClearAll -> clearAll
        btnReset.addActionListener(e -> drawingPanel.reset());
        btnUndo.addActionListener(e -> drawingPanel.undo());
        btnRedo.addActionListener(e -> drawingPanel.redo());

        toolbar.add(btnUndo);
        toolbar.add(btnRedo);
        toolbar.add(btnClear);
        toolbar.add(btnReset);

        return toolbar;
    }

    // --- Helper: Build the Menu Bar ---
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Project");
        JMenuItem loadItem = new JMenuItem("Load Project");
        JMenuItem exportItem = new JMenuItem("Export to JPG");
        JMenuItem importItem = new JMenuItem("Import Background");

        // Save Project Action
        saveItem.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.saveProject(fileChooser.getSelectedFile());
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });

        // Load Project Action
        loadItem.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.loadProject(fileChooser.getSelectedFile());
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });

        // Export Image Action
        exportItem.addActionListener(e -> {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.exportImage(fileChooser.getSelectedFile());
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });

        // Import Image Action
        importItem.addActionListener(e -> {
            fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.importImage(fileChooser.getSelectedFile());
                } catch (Exception ex) { 
                    JOptionPane.showMessageDialog(this, "Error loading image"); 
                }
            }
            fileChooser.resetChoosableFileFilters(); // Reset filter after use
        });

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exportItem);
        fileMenu.add(importItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    // --- Helper: Create a Color Button ---
    private JToggleButton createColorButton(String name, Color color) {
        JToggleButton btn = new JToggleButton(name);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> drawingPanel.setCurrentColor(color));
        return btn;
    }

    // --- Helper: Create a Shape Button ---
    private JToggleButton createShapeButton(String name, ShapeMode mode, ButtonGroup group) {
        JToggleButton btn = new JToggleButton(name);
        btn.addActionListener(e -> drawingPanel.setCurrentMode(mode));
        group.add(btn); // Add to group so they toggle exclusively
        return btn;
    }
}