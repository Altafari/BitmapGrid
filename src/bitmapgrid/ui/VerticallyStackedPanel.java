package bitmapgrid.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class VerticallyStackedPanel extends JPanel {

    protected final Dimension PREFERRED_SIZE = new Dimension(60, 20);

    private static final long serialVersionUID = 1L;
    private final int LEFT_INSET = 5;

    public VerticallyStackedPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    protected void addLabeledComponent(Component comp, String label) {
        addLabeledComponent(comp, label, getPreferredComponentSize());
    }

    protected void addLabeledComponent(Component comp, String label, Dimension componentSize) {
        comp.setPreferredSize(componentSize);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createHorizontalStrut(LEFT_INSET));
        panel.add(new JLabel(label));
        JPanel compPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        compPanel.add(comp);
        panel.add(compPanel);
        add(panel);
    }

    protected Dimension getPreferredComponentSize() {
        return PREFERRED_SIZE;
    }
}