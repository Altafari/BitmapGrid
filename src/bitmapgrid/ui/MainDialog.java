package bitmapgrid.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import bitmapgrid.controls.ZoomControl;
import bitmapgrid.model.DocumentModelDispatcher;
import bitmapgrid.observable.HubConnector;
import bitmapgrid.ui.panels.GridControlPanel;
import bitmapgrid.ui.panels.ImageControlPanel;
import bitmapgrid.ui.views.ScrollableDocumentView;

public class MainDialog {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BitmapGrid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }

    private static void addComponentsToPane(Container pane) {
        ScrollableDocumentView docView = new ScrollableDocumentView(new ImageIcon());
        JScrollPane scrollPane = new JScrollPane(docView);
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageControlPanel imPanel = new ImageControlPanel();
        GridControlPanel grPanel = new GridControlPanel();
        HubConnector hub = new HubConnector();
        hub.connectables.add(imPanel);
        hub.connectables.add(grPanel);
        hub.connectables.add(new DocumentModelDispatcher());
        hub.connectables.add(docView);
        tabbedPane.addTab("Image", imPanel);
        tabbedPane.addTab("Arrangement", grPanel);
        pane.add(tabbedPane, BorderLayout.LINE_START);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(new ZoomControl(), BorderLayout.LINE_END);
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
