package bitmapgrid.ui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import bitmapgrid.controls.InfoTextField;
import bitmapgrid.observable.Observable;

public class ImageControlPanel extends VerticallyStackedPanel {
    
    public final Observable<BufferedImage> image;
    public final Observable<Double> dpi;    

    private static final long serialVersionUID = 1L;
    private InfoTextField<int[]> imageSizeLabel;
    private InfoTextField<double[]> imageDimsLabel;
    private JFormattedTextField dpiField;
    private InfoTextField<File> fileNameLabel;
    private JButton selectFileButton;
    
    public ImageControlPanel() {

        image = new Observable<BufferedImage>();
        dpi = new Observable<Double>();

        imageSizeLabel = new InfoTextField<int[]>() {
            private static final long serialVersionUID = 1L;
            @Override
            public void notifyChanged(int[] newVal) {
                if (newVal != null) {
                    setText(String.format("%i x %i", newVal[0], newVal[1]));
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
                    setText(String.format("%f x %f", newVal[0], newVal[1]));
                } else {
                    setText("N/A");
                }
            }  
        };
        
        dpiField = new JFormattedTextField(NumberFormat.getNumberInstance());
        dpiField.setHorizontalAlignment(JFormattedTextField.RIGHT);
        dpiField.addActionListener(a -> dpi.notifyChanged((double)((JFormattedTextField) a.getSource()).getValue()));
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
        };
        
        selectFileButton = new JButton("Select");
        
        addLabeledComponent(imageSizeLabel, "Image size");
        addLabeledComponent(imageDimsLabel, "Image dims");
        addLabeledComponent(dpiField, "Image DPI");
        addLabeledComponent(fileNameLabel, "File", new Dimension(130, getPreferredComponentSize().height));
        addLabeledComponent(selectFileButton, "", new Dimension(90, 20));
    }
}
