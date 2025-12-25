package com.iti.paintbrush.ui;
import java.awt.*;
import javax.swing.*;
public class Mainframe extends JFrame {
    private JButton btnRed, btnGreen, btnBlue;
    private JButton btnOval, btnLine, btnRectangle;
    private JButton btnFreeHand, btnEraser, btnClear;
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
        btnBlue = new JButton("Blue");
        btnBlue.setBackground(Color.BLUE);
        btnBlue.setForeground(Color.WHITE);

        btnGreen = new JButton("Green");
        btnGreen.setBackground(Color.GREEN);
        btnGreen.setForeground(Color.WHITE);

        btnRed = new JButton("Red");
        btnRed.setBackground(Color.RED);
        btnRed.setForeground(Color.WHITE);

        // Initial Shape buttons
        btnOval = new JButton("Oval");
        btnLine = new JButton("Line");
        btnRectangle = new JButton("Rectangle");

        // Initial Action buttons
        btnFreeHand = new JButton("Free Hand");
        btnEraser = new JButton("Eraser");
        btnClear = new JButton("Clear");

        // Checkboxes
        chkDotted = new JCheckBox("Dotted");
        chkFilled = new JCheckBox("Filled");

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
        toolbar.add(chkDotted);
        toolbar.add(chkFilled);

        //Drawing area

        DrawingPanel drawingArea = new DrawingPanel();

        // Add panels
        this.add(toolbar, BorderLayout.NORTH);
        this.add(drawingArea, BorderLayout.CENTER);
        
    }
}
