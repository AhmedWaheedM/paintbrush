package com.iti.paintbrush.main;
import com.iti.paintbrush.ui.Mainframe;
import javax.swing.*;
public class PaintBrushApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mainframe().setVisible(true));
    }
}