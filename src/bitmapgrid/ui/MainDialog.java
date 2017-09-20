package bitmapgrid.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MainDialog {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BitmapGrid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    private static void addComponentsToPane(Container pane) {
        JPanel mainPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(60, 70));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Image", new ImageControlPanel());
        tabbedPane.addTab("Arrangement", new GridControlPanel());
        mainPanel.add(tabbedPane, BorderLayout.LINE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        pane.add(mainPanel);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });       
    }
}
