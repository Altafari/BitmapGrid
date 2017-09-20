package bitmapgrid.ui;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ImageControlPanel extends VerticallyStackedPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JLabel imageSize;
    private JLabel imageDims;
    private JFormattedTextField dpi;
    private JTextField fileName;
    private JButton selectFile;
    
    public ImageControlPanel() {
        
    }
}
