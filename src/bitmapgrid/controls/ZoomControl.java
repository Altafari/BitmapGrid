package bitmapgrid.controls;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.ObservableCore;
import bitmapgrid.observable.Signal;

public class ZoomControl extends JPanel implements IConnectable {

    public final IObservable<Double> observable;
    
    private class ZoomButton extends JButton {

        private static final long serialVersionUID = 1L;
        private final Dimension dims = new Dimension(36, 36);

        public ZoomButton(String imgFileName) {
            super();
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(imgFileName));
                setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setIcon(new ImageIcon(img));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setPreferredSize(dims);
            setMinimumSize(dims);
            setMaximumSize(dims);
        }
    }
    
    private static final long serialVersionUID = 1L;
    private final JButton btnIn, btnOut;
    private final JSlider slider;
    private final Dimension sliderSize = new Dimension(30, 120);
    private final int zoomSteps = 10;
    private final int initialState = 4;
    private final double zoomRatio = 1.4;
    private final double minZoom = 0.05;
    private final Map<Integer, Double> zoomTable;
    
    public ZoomControl() {
        
        ObservableCore<Double> obs = new ObservableCore<Double>() {
            @Override
            public Double getObservableValue() {
                return getZoom();
            }
        };
        
        observable = obs;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        btnIn = new ZoomButton("resources/btzoomin.png");
        btnOut = new ZoomButton("resources/btzoomout.png");
        slider = new JSlider(JSlider.VERTICAL, 0, zoomSteps - 1, initialState);
        btnIn.setAlignmentX(CENTER_ALIGNMENT);
        btnOut.setAlignmentX(CENTER_ALIGNMENT);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setPreferredSize(sliderSize);
        slider.setMinimumSize(sliderSize);
        slider.setMaximumSize(sliderSize);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(zoomSteps - 1);
        slider.putClientProperty("JSlider.isFilled", false);
        slider.addChangeListener(e -> obs.notifyObservers(getZoom()));
        btnIn.addActionListener(e -> slider.setValue(Math.min(slider.getMaximum(), slider.getValue() + 1)));
        btnOut.addActionListener(e -> slider.setValue(Math.max(slider.getMinimum(), slider.getValue() - 1)));
        add(btnIn);
        add(slider);
        add(btnOut);
        setOpaque(false);
        zoomTable = new HashMap<Integer, Double>();
        double zoomVal = minZoom;
        for(int i = 0; i < zoomSteps; i++) {
            zoomTable.put(i, zoomVal);
            zoomVal *= zoomRatio;
        }
        
    }
    
    private Double getZoom() {
        return zoomTable.get(slider.getValue());
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable(Signal.DocumentZoom, observable);
    }

    @Override
    public void onSubscription(ISubscriptionVisitor sub) {}

}
