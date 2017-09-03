package bitmapgrid.ui;

import javax.swing.JScrollBar;

public class ScrollController {
    
    private JScrollBar hScrollBar;
    private JScrollBar vScrollBar;
    private ViewPort viewPort;
    
    public ScrollController() {
        // TODO: Pass here instances of scrollbars and ViewPort
    }
    
    public void onViewPortUpdate(ViewPort wp) {
        int[] overhang = wp.getDocumentOverhang();
        // Limit number of ticks?
        int[] portOffset = wp.getPortOffset();
        int[] scrollPos = new int[2];
        int[] scrollTicks = new int[2];
        for (int i = 0; i < 2; i++) {
            scrollTicks[i] = overhang[i] + 1;
            scrollPos[i] = Math.min(scrollTicks[i], Math.max(0, overhang[i] / 2 + portOffset[i]));
        }
        // Update scrollbars accordingly to scrolPos and scrollTicks; 
    }
    
    public void onScrollUpdate(ViewPort wp) {
        // TODO: wp should be stored as member
        // TODO: check and test formulas
        int[] scrollPos = new int[2];
        int[] scrollTicks = new int[2];
        int[] portOffset = new int[2];
        for (int i = 0; i < 2 ;i++) {
            portOffset[i] = scrollPos[i] - (scrollTicks[i] - 1) / 2;
        }
        wp.setPortOffset(portOffset);
    }
    // TODO: Is it possible to set swing's scrollbar thumb width accordingly? 
}
