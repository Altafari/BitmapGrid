package bitmapgrid.ui.panels;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class VerticallyStackedPanel extends JPanel {
    
    private class RackPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(1, getPreferredSize().height);
        }
        
        public Dimension getMaximumSize() {
            return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
        }
    }

    protected final Dimension PREFERRED_SIZE = new Dimension(60, 20);

    private static final long serialVersionUID = 1L;
    private final int HORIZONTAL_INSET = 5;
    private final int VERTICAL_INSET = 10;

    public VerticallyStackedPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    protected void addLabeledComponent(Component comp, String label) {
        addLabeledComponent(comp, label, getPreferredComponentSize());
    }

    protected void addLabeledComponent(Component comp, String label, Dimension componentSize) {
        add(Box.createVerticalStrut(VERTICAL_INSET));
        comp.setMaximumSize(componentSize);
        comp.setMinimumSize(componentSize);
        comp.setPreferredSize(componentSize);
        JPanel panel = new RackPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(Box.createHorizontalStrut(HORIZONTAL_INSET));
        panel.add(new JLabel(label));
        panel.add(Box.createHorizontalStrut(HORIZONTAL_INSET));
        panel.add(Box.createHorizontalGlue());
        panel.add(comp);
        panel.add(Box.createHorizontalStrut(HORIZONTAL_INSET));
        Dimension size = panel.getPreferredSize();
        panel.setMinimumSize(size);
        panel.setMaximumSize(size);
        add(panel);
    }

    protected Dimension getPreferredComponentSize() {
        return PREFERRED_SIZE;
    }
}