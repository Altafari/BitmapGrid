package bitmapgrid.model;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import bitmapgrid.observable.BinaryCombiner;
import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Observable;
import bitmapgrid.observable.Signal;

public class ZoomableImageView implements IConnectable {

    private final Observable<BufferedImage> zoomedDocument;
    
    public ZoomableImageView() {
        zoomedDocument = new Observable<BufferedImage>();
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable(Signal.ZoomedDocument, zoomedDocument);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        IObservable<Double> docZoom = (IObservable<Double>) sub.getObservable(Signal.DocumentZoom);
        IObservable<BufferedImage> docImage = (IObservable<BufferedImage>) sub.getObservable(Signal.DocumentImage);
        BinaryCombiner<BufferedImage, Double, BufferedImage> zoomCombiner =
                new BinaryCombiner<BufferedImage, Double, BufferedImage>(docImage, docZoom, (i, z) -> scaleImage(i, z));
        zoomCombiner.addObserver(zoomedDocument);
    }

    private BufferedImage scaleImage(BufferedImage img, Double zoom) {
        int width = (int) Math.ceil(img.getWidth() * zoom);
        int height = (int) Math.ceil(img.getHeight() * zoom);
        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = res.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(zoom, zoom);
        g.drawImage(img, at, null);
        g.dispose();
        return res;
    }
}
