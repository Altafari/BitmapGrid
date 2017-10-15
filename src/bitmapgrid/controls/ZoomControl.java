package bitmapgrid.controls;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
        
        ObservableCore<Double> obs = new ObservableCore<Double>() {
            @Override
            public Double getObservableValue() {
                return getZoom();
            }
        };
        
        observable = obs;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        btnIn = new ZoomButton("+");
        btnOut = new ZoomButton("-");
        slider = new JSlider(JSlider.VERTICAL, 0, 9, 4);
        btnIn.setAlignmentX(CENTER_ALIGNMENT);
        btnOut.setAlignmentX(CENTER_ALIGNMENT);
        slider.setAlignmentX(CENTER_ALIGNMENT);
        slider.setPreferredSize(sliderSize);
        slider.setMinimumSize(sliderSize);
        slider.setMaximumSize(sliderSize);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(9);
        slider.addChangeListener(e -> obs.notifyObservers(getZoom()));
        add(btnIn);
        add(Box.createVerticalStrut(INSET));
        add(slider);
        add(Box.createVerticalStrut(INSET));
        add(btnOut);
        setOpaque(false);
    }
    
    private Double getZoom() {
        return (slider.getValue() + 1.0) * 0.2;
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable(Signal.DocumentZoom, observable);
    }

    @Override
    public void onSubscription(ISubscriptionVisitor sub) {}

}
