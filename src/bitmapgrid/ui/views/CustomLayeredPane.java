package bitmapgrid.ui.views;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JLayeredPane;

public class CustomLayeredPane extends JLayeredPane {
    
    private class DualLayerLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {}

        @Override
        public void removeLayoutComponent(Component comp) {}

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return mainCont.getPreferredSize();
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return mainCont.getMinimumSize();
        }

        @Override
        public void layoutContainer(Container parent) {
            Dimension pDims = parent.getSize();
            int nComp = parent.getComponentCount();
            for (int i = 0; i < nComp; i ++) {
                Component c = parent.getComponent(i);
                if (mainCont == c) {
                    c.setBounds(0, 0, pDims.width, pDims.height);
                } else {
                    Dimension d = c.getPreferredSize();
                    c.setBounds(pDims.width - (d.width + H_GAP), V_GAP, d.width, d.height);
                }
            }
        }
    }
    
    private static final long serialVersionUID = 1L;
    
    private static final int H_GAP = 40;
    private static final int V_GAP = 30;

    private final Container mainCont;
    private final Container onTopCont;
    
    public CustomLayeredPane(Container mainContainer, Container onTopContainer) {
        mainCont = mainContainer;
        onTopCont = onTopContainer;
        add(mainCont, new Integer(0));
        add(onTopCont, new Integer(1));
        setLayout(new DualLayerLayout());
    }
}
