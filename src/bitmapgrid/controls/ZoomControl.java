package bitmapgrid.controls;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ZoomControl extends JPanel {

    private class ZoomButton extends JButton {

        private static final long serialVersionUID = 1L;
        private final Dimension dims = new Dimension(40, 40);

        public ZoomButton(String s) {
            super(s);
            setPreferredSize(dims);
            setMinimumSize(dims);
            setMaximumSize(dims);
        }
    }
    
    private static final long serialVersionUID = 1L;
    private static final int INSET = 5;
    private final JButton btnIn, btnOut;
    private final JSlider slider;
    private final Dimension sliderSize = new Dimension(30, 160);
    
    public ZoomControl() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        btnIn = new ZoomButton("+");
        btnOut = new ZoomButton("-");
        slider = new JSlider(JSlider.VERTICAL, 0, 9, 5);
        btnIn.setAlignmentX(CENTER_ALIGNMENT);
        btnOut.setAlignmentX(CENTER_ALIGNMENT);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setPreferredSize(sliderSize);
        slider.setMinimumSize(sliderSize);
        slider.setMaximumSize(sliderSize);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(9);
        add(btnIn);
        add(Box.createVerticalStrut(INSET));
        add(slider);
        add(Box.createVerticalStrut(INSET));
        add(btnOut);
        setOpaque(false);
    }

}
