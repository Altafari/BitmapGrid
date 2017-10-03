package bitmapgrid.ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bitmapgrid.controls.InfoTextField;
import bitmapgrid.observable.BinaryCombiner;
import bitmapgrid.observable.FunctionMapper;
import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Observable;

public class ImageControlPanel extends VerticallyStackedPanel implements IConnectable {

    public final Observable<BufferedImage> image;
    public final IObservable<int[]> imageSize;
    public final IObservable<double[]> imageDimensions;
    public final Observable<Double> dpi;

    private static final long serialVersionUID = 1L;

    private InfoTextField<int[]> imageSizeLabel;
    private InfoTextField<double[]> imageDimsLabel;
    private JFormattedTextField dpiField;
    private InfoTextField<File> fileNameLabel;
    private JFileChooser fileChooser;
    private Observable<File> file;
    private JButton selectFileButton;

    public ImageControlPanel() {
        dpi = new Observable<Double>();

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

        dpiField = new JFormattedTextField(NumberFormat.getNumberInstance());
        dpiField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        dpiField.addActionListener(a -> dpi.notifyChanged(((Number) dpiField.getValue()).doubleValue()));
        dpi.addObserver(val -> dpiField.setValue(val));

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

        imageDimensions = new BinaryCombiner<int[], Double, double[]>(imageSize, dpi, (size, dpi) -> computeImageDimensions(dpi, size));
        imageDimensions.addObserver(imageDimsLabel);

        image.notifyChanged(null);
        dpi.notifyChanged(900.0);

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

    private double[] computeImageDimensions(Double dpi, int[] size) {
        return new double[] { (size[0] * 25.4) / dpi, (size[1] * 25.4) / dpi };
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable("imageDimensions", imageDimensions);
    }

    @Override
    public void onSubscription(ISubscriptionVisitor sub) { }
}
