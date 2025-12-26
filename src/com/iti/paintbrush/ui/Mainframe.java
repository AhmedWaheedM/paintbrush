package com.iti.paintbrush.ui;
import java.awt.*;
import javax.swing.*;
public class Mainframe extends JFrame {
    private JToggleButton btnRed, btnGreen, btnBlue;
    private JToggleButton btnOval, btnLine, btnRectangle;
    private JButton btnFreeHand, btnEraser;
    private JButton btnSave, btnLoad;
    private JButton btnUndo, btnRedo, btnClear;
    private JCheckBox chkDotted, chkFilled; 

    public Mainframe() {
        // Set up frame properties
        setTitle("Paint Brush - By ITI");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // toolbar panel
        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.LIGHT_GRAY);
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        // Initial Color buttons
        btnBlue = new JToggleButton("Blue");
        btnBlue.setBackground(Color.BLUE);
        btnBlue.setForeground(Color.WHITE);

        btnGreen = new JToggleButton("Green");
        btnGreen.setBackground(Color.GREEN);
        btnGreen.setForeground(Color.WHITE);

        btnRed = new JToggleButton("Red");
        btnRed.setBackground(Color.RED);
        btnRed.setForeground(Color.WHITE);

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(btnBlue);
        colorGroup.add(btnGreen);
        colorGroup.add(btnRed);

        // Initial Shape buttons
        btnOval = new JToggleButton("Oval");
        btnLine = new JToggleButton("Line");
        btnRectangle = new JToggleButton("Rectangle");

        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(btnOval);    
        shapeGroup.add(btnLine);
        shapeGroup.add(btnRectangle);

        // Initial Action buttons
        btnFreeHand = new JButton("Free Hand");
        btnEraser = new JButton("Eraser");
        btnClear = new JButton("Clear");
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");
        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");
        // Checkboxes
        chkDotted = new JCheckBox("Dotted");
        chkDotted.setOpaque(false);
        chkFilled = new JCheckBox("Filled");
        chkFilled.setOpaque(false);

        // Add to toolbar
        toolbar.add(new JLabel("Colors: "));
        toolbar.add(btnRed);
        toolbar.add(btnGreen);
        toolbar.add(btnBlue);

        toolbar.add(new JSeparator(SwingConstants.VERTICAL));

        toolbar.add(new JLabel("Shapes: "));
        toolbar.add(btnOval);
        toolbar.add(btnLine);
        toolbar.add(btnRectangle);

        toolbar.add(new JSeparator(SwingConstants.VERTICAL));

        toolbar.add(new JLabel("Actions: "));
        toolbar.add(btnFreeHand);
        toolbar.add(btnEraser);
        toolbar.add(btnClear);
        toolbar.add(btnUndo);
        toolbar.add(btnRedo);
        toolbar.add(chkDotted);
        toolbar.add(chkFilled);

        toolbar.add(new JSeparator(SwingConstants.VERTICAL));

        toolbar.add(new JLabel("File: "));
        toolbar.add(btnSave);
        toolbar.add(btnLoad);

        //Drawing area

        DrawingPanel drawingArea = new DrawingPanel();

        // Add panels
        this.add(toolbar, BorderLayout.NORTH);
        this.add(drawingArea, BorderLayout.CENTER);
        
    }
}
