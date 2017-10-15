package bitmapgrid.ui.views;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Scrollable;

import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Signal;

public class ScrollableDocumentView extends JLabel implements Scrollable, IConnectable {

    private static final long serialVersionUID = 1L;
    private final Dimension defaultPreferredDimension = new Dimension(800, 600);
    private ImageIcon imageIcon;

    public ScrollableDocumentView() {
        super((ImageIcon) null);
        setAutoscrolls(true);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
                scrollRectToVisible(r);
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {}

        });
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (imageIcon == null) {
            return defaultPreferredDimension;
        } else {
            return super.getPreferredSize();
        }
    }
    
    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return defaultPreferredDimension;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
        return 10;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
        return 1;
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {}

    @SuppressWarnings("unchecked")
    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        IObservable<BufferedImage> obs = (IObservable<BufferedImage>) sub.getObservable(Signal.DocumentImage);
        obs.addObserver(new IObserver<BufferedImage>() {
            @Override
            public void notifyChanged(BufferedImage newVal) {
                if (newVal == null) {
                    imageIcon = null;
                } else {
                    imageIcon = new ImageIcon(newVal);
                }
                setIcon(imageIcon);
                revalidate();
                repaint();
                update(getGraphics());
            }
        });
    }
}
