package bitmapgrid.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import bitmapgrid.controls.ZoomControl;
import bitmapgrid.model.DocumentModelDispatcher;
import bitmapgrid.observable.HubConnector;
import bitmapgrid.ui.panels.GridControlPanel;
import bitmapgrid.ui.panels.ImageControlPanel;
import bitmapgrid.ui.views.CustomLayeredPane;
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
        ScrollableDocumentView docView = new ScrollableDocumentView();
        JScrollPane scrollPane = new JScrollPane(docView);
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageControlPanel imPanel = new ImageControlPanel();
        GridControlPanel grPanel = new GridControlPanel();
        ZoomControl zoomCtrl = new ZoomControl();
        HubConnector hub = new HubConnector();
        hub.connectables.add(imPanel);
        hub.connectables.add(grPanel);
        hub.connectables.add(new DocumentModelDispatcher());
        hub.connectables.add(docView);
        hub.connectables.add(zoomCtrl);
        tabbedPane.addTab("Image", imPanel);
        tabbedPane.addTab("Arrangement", grPanel);
        pane.add(tabbedPane, BorderLayout.LINE_START);
        JLayeredPane clp = new CustomLayeredPane(scrollPane, zoomCtrl);
        pane.add(clp, BorderLayout.CENTER);
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
