package com.iti.paintbrush.utils;

import com.iti.paintbrush.interfaces.Drawable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FileUtils {

    public static void saveProject(File file, ArrayList<Drawable> shapes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(shapes);
        }
    }

    public static ArrayList<Drawable> loadProject(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Drawable>) ois.readObject();
        }
    }

    public static void exportImage(File file, JPanel panel) throws IOException {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE); // Force white background
        g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        panel.print(g2d); 
        g2d.dispose();

        String filename = file.getName().toLowerCase();
        if (!filename.endsWith(".jpg") && !filename.endsWith(".jpeg") && !filename.endsWith(".png")) {
            file = new File(file.getParentFile(), filename + ".jpg");
        }
        ImageIO.write(image, "jpg", file);
    }
}