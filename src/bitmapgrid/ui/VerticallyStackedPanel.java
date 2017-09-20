package bitmapgrid.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class VerticallyStackedPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Dimension preferredComponentSize = new Dimension(60, 18);
    
    public VerticallyStackedPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
    
    protected void addLabeledComponent(Component comp, String label) {
        comp.setPreferredSize(getComponentPreferredSize());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(new JLabel(label));
        panel.add(comp);
        this.add(panel);
    }
    
    protected Dimension getComponentPreferredSize(){
        return preferredComponentSize;
    }
}
