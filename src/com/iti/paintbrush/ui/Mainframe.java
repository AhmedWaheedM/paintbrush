package com.iti.paintbrush.ui;

import com.iti.paintbrush.enums.DrawMode;
import com.iti.paintbrush.enums.ShapeMode;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Mainframe extends JFrame {

    private DrawingPanel drawingPanel;
    private JFileChooser fileChooser;

    public Mainframe() {
        // 1. Frame Setup
        setTitle("Paint Brush - By ITI");
        setSize(1350, 600);
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
        JPanel toolbarContainer = new JPanel();
        toolbarContainer.setLayout(new BoxLayout(toolbarContainer, BoxLayout.Y_AXIS));
        toolbarContainer.setBackground(Color.LIGHT_GRAY);

        // 1st row
        // -- Actions --
        JPanel actionsRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsRow.setBackground(Color.LIGHT_GRAY);
        
        actionsRow.add(new JLabel("Actions:"));
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");
        JButton btnClear = new JButton("Clear");
        JButton btnReset = new JButton("Reset");


        btnClear.addActionListener(e -> drawingPanel.clearAll()); 
        btnReset.addActionListener(e -> drawingPanel.reset());
        btnUndo.addActionListener(e -> drawingPanel.undo());
        btnRedo.addActionListener(e -> drawingPanel.redo());


        actionsRow.add(btnUndo);
        actionsRow.add(btnRedo);
        actionsRow.add(btnClear);
        actionsRow.add(btnReset);


        // 2nd row - tools
        JPanel toolsRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolsRow.setBackground(Color.LIGHT_GRAY);

        // -- Colors --
        toolsRow.add(new JLabel("Colors: "));
        ButtonGroup colorGroup = new ButtonGroup();
        JToggleButton blackBtn = createColorButton("", Color.BLACK, colorGroup);
        toolsRow.add(blackBtn);
        blackBtn.setSelected(true);  // Default selection

        blackBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 4)); 
        toolsRow.add(createColorButton("", Color.GRAY, colorGroup));
        toolsRow.add(createColorButton("", Color.WHITE, colorGroup));
        toolsRow.add(createColorButton("", Color.RED, colorGroup));
        Color green = new Color(0,200,00);
        toolsRow.add(createColorButton("", green, colorGroup));
        toolsRow.add(createColorButton("", Color.YELLOW, colorGroup));
        toolsRow.add(createColorButton("", Color.BLUE, colorGroup));
        toolsRow.add(createColorButton("", Color.PINK, colorGroup));
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));


        // -- Shapes --
        toolsRow.add(new JLabel("Shapes:"));
        ButtonGroup shapeGroup = new ButtonGroup();
        JToggleButton pencilBtn = createShapeButton("\u270E", "Pencil", ShapeMode.FREE_HAND, shapeGroup);
        toolsRow.add(pencilBtn);
        pencilBtn.setSelected(true); //default start

        // using unicode characters to insert shapes
        toolsRow.add(createShapeButton("\u2572", "Line", ShapeMode.LINE, shapeGroup));
        toolsRow.add(createShapeButton("\u25AD", "Rectangle", ShapeMode.RECTANGLE, shapeGroup));
        toolsRow.add(createShapeButton("\u25A1", "Square", ShapeMode.SQUARE, shapeGroup));
        toolsRow.add(createShapeButton("\u2B2D", "Oval", ShapeMode.OVAL, shapeGroup));
        toolsRow.add(createShapeButton("\u25CB", "Circle", ShapeMode.CIRCLE, shapeGroup));
        toolsRow.add(createShapeButton("\u2327", "Eraser", ShapeMode.ERASER, shapeGroup));
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));

        // -- Draw Modes -- (for line types )
        toolsRow.add(new JLabel("Mode:"));
        
        // Mutually exclusive checkboxes for Solid, Dotted, and Filled
        JCheckBox solidCheckbox = new JCheckBox("Solid");
        JCheckBox dottedCheckbox = new JCheckBox("Dotted");
        JCheckBox filledCheckbox = new JCheckBox("Filled");
        
        solidCheckbox.setSelected(true); // default
        
        solidCheckbox.addItemListener(e -> {
            if (solidCheckbox.isSelected()) {
                dottedCheckbox.setSelected(false);
                filledCheckbox.setSelected(false);
                drawingPanel.setDrawMode(DrawMode.SOLID);
            }
        });
        
        dottedCheckbox.addItemListener(e -> {
            if (dottedCheckbox.isSelected()) {
                solidCheckbox.setSelected(false);
                filledCheckbox.setSelected(false);
                drawingPanel.setDrawMode(DrawMode.DOTTED);
            }
        });
        
        filledCheckbox.addItemListener(e -> {
            if (filledCheckbox.isSelected()) {
                solidCheckbox.setSelected(false);
                dottedCheckbox.setSelected(false);
                drawingPanel.setDrawMode(DrawMode.FILLED);
            }
        });
        
        toolsRow.add(solidCheckbox);
        toolsRow.add(dottedCheckbox);
        toolsRow.add(filledCheckbox);
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));

        // -- Thickness Slider --
        toolsRow.add(new JLabel("Thickness:"));
        JSlider thicknessSlider = new JSlider(1, 11, 2);
        thicknessSlider.setPreferredSize(new Dimension(100, 30));
        thicknessSlider.setBackground(Color.LIGHT_GRAY);
        thicknessSlider.addChangeListener(e -> drawingPanel.setCurrentThick(thicknessSlider.getValue()));
        toolsRow.add(thicknessSlider);
        toolsRow.add(new JSeparator(SwingConstants.VERTICAL));


        // 7ot both rows fl container
        toolbarContainer.add(actionsRow);
        toolbarContainer.add(toolsRow);
        

        return toolbarContainer;
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
    
    private JToggleButton createColorButton(String name, Color color, ButtonGroup group) {
    JToggleButton btn = new JToggleButton(name);
    btn.setBackground(color);
    Dimension size = new Dimension(30, 30);
    btn.setPreferredSize(size);
    btn.setForeground(Color.WHITE);

    // changes in style that will make the button have an outline on selection
        // changing defult style
    btn.setContentAreaFilled(false);
    btn.setOpaque(true);
    btn.setFocusPainted(false);
        // default border
    btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        // talama 3mlna reset lel style lazm ne3ml el exclusive selection style bnfsna

    // changing button border depending on action
    btn.addActionListener(e -> {
        drawingPanel.setCurrentColor(color);
        
        /* we will use something called enumeration --shabah el while-- that reads the number of objects created of this type
        and iterates through it ..  */    
        java.util.Enumeration<AbstractButton> btns = group.getElements();
            // zy cursor by-Loop through all buttons in the group  
        while (btns.hasMoreElements()) {
        JToggleButton bt = (JToggleButton) btns.nextElement();
            if (bt.isSelected()) {
                bt.setBorder(BorderFactory.createLineBorder(Color.CYAN, 4));
            } else {
                bt.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
         }
    
        });
    group.add(btn); 
    return btn;
    }
    
    

    // --- Helper: Create a Shape Button ---
    private JToggleButton createShapeButton(String unicode, String tooltip, ShapeMode mode, ButtonGroup group) {
        JToggleButton btn = new JToggleButton(unicode);
        btn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20)); // Unicode font
        btn.setToolTipText(tooltip);
        btn.addActionListener(e -> drawingPanel.setCurrentShapeMode(mode));
        group.add(btn);
        return btn;
    }

    // --- Helper: create a line type button
    // --- Helper: Create a Draw Mode Button ---
    private JToggleButton createDrawModeButton(String name, DrawMode mode, ButtonGroup group) {
    JToggleButton btn = new JToggleButton(name);
    btn.addActionListener(e -> drawingPanel.setDrawMode(mode));
    group.add(btn);
    return btn;
    }
}