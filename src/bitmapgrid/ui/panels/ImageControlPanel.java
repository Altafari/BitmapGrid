package bitmapgrid.ui.panels;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bitmapgrid.controls.InfoTextField;
import bitmapgrid.controls.ReactiveNumberField;
import bitmapgrid.observable.BinaryCombiner;
import bitmapgrid.observable.FunctionMapper;
import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Observable;
import bitmapgrid.observable.Signal;

public class ImageControlPanel extends VerticallyStackedPanel implements IConnectable {

    public final Observable<BufferedImage> image;
    public final IObservable<int[]> imageSize;
    public final IObservable<double[]> imageDimensions;
    public final IObservable<Double> pixelPerMm;

    private static final long serialVersionUID = 1L;

    private InfoTextField<int[]> imageSizeLabel;
    private InfoTextField<double[]> imageDimsLabel;
    private ReactiveNumberField dpiField;
    private InfoTextField<File> fileNameLabel;
    private JFileChooser fileChooser;
    private Observable<File> file;
    private JButton selectFileButton;

    public ImageControlPanel() {

        imageSizeLabel = new InfoTextField<int[]>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void notifyChanged(int[] newVal) {
                if (newVal != null) {
                    setText(String.format("%d x %d", newVal[0], newVal[1]));
                } else {
                    setText("N/A");
                }
            }
        };

        imageDimsLabel = new InfoTextField<double[]>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void notifyChanged(double[] newVal) {
                if (newVal != null) {
                    setText(String.format("%.1f x %.1f", newVal[0], newVal[1]));
                } else {
                    setText("N/A");
                }
            }
        };

        dpiField = new ReactiveNumberField();        
        pixelPerMm = new FunctionMapper<Double, Double>(dpiField.observable, x -> x / 25.4);

        fileNameLabel = new InfoTextField<File>() {
            private static final long serialVersionUID = 1L;

            public void notifyChanged(File newVal) {
                if (newVal != null) {
                    setText(newVal.getName());
                } else {
                    setText("");
                }
            }

            @Override
            protected int getAlignment() {
                return JTextField.LEFT;
            }
        };

        file = new Observable<File>();
        file.addObserver(fileNameLabel);
        fileChooser = new JFileChooser();
        selectFileButton = new JButton("Select");
        selectFileButton.addActionListener(a -> {
            if (fileChooser.showOpenDialog(ImageControlPanel.this) == JFileChooser.APPROVE_OPTION) {
                file.notifyChanged(fileChooser.getSelectedFile());
            }
        });

        image = new Observable<BufferedImage>();

        IObservable<BufferedImage> imageMapper = new FunctionMapper<File, BufferedImage>(file, f -> {
            if (f == null)
                return null;
            try {
                BufferedImage img = ImageIO.read(f);
                if (img == null) {
                    JOptionPane.showMessageDialog(null, "Unknown image format!");
                    return null;
                }
                return img;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Can't read image file!");
                return null;
            }
        });

        imageMapper.addObserver(image);

        imageSize = new FunctionMapper<BufferedImage, int[]>(image, i -> new int[] { i.getWidth(), i.getHeight() });
        imageSize.addObserver(imageSizeLabel);

        imageDimensions = new BinaryCombiner<int[], Double, double[]>(imageSize, pixelPerMm,
                (size, ppm) -> new double[] { size[0] / ppm, size[1] / ppm });

        imageDimensions.addObserver(imageDimsLabel);

        image.notifyChanged(null);
        dpiField.notifyChanged(900.0);

        addLabeledComponent(imageSizeLabel, "Image size");
        addLabeledComponent(imageDimsLabel, "Image dims");
        addLabeledComponent(dpiField, "Image DPI");
        addLabeledComponent(fileNameLabel, "File", new Dimension(160, getPreferredComponentSize().height));
        addLabeledComponent(selectFileButton, "", new Dimension(90, 20));
        add(Box.createVerticalGlue());
    }

    @Override
    protected Dimension getPreferredComponentSize() {
        return new Dimension(100, this.PREFERRED_SIZE.height);
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable(Signal.ImageDimension, imageDimensions);
        pub.publishObservable(Signal.SourceImage, image);
        pub.publishObservable(Signal.ImageDpi, dpiField.observable);
        pub.publishObservable(Signal.PixelPerMm, pixelPerMm);
    }

    @Override
    public void onSubscription(ISubscriptionVisitor sub) {}
}
