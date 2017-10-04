package bitmapgrid.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import bitmapgrid.observable.HubConnector;

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
        ImageControlPanel imPanel = new ImageControlPanel();
        GridControlPanel grPanel = new GridControlPanel();
        HubConnector hub = new HubConnector();
        hub.connectables.add(imPanel);
        hub.connectables.add(grPanel);
        tabbedPane.addTab("Image", imPanel);
        tabbedPane.addTab("Arrangement", grPanel);
        mainPanel.add(tabbedPane, BorderLayout.LINE_START);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        pane.add(mainPanel);
        hub.wireUp();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
