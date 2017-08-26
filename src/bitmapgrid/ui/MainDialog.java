package bitmapgrid.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
        JPanel ctrlPanel = new JPanel();            ;
        //ctrlPanel.add(new DeviceBitView(SystemManager.getInstance().getConfigurator().bitState));
        pane.add(ctrlPanel, BorderLayout.LINE_START); 
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });       
    }
}
