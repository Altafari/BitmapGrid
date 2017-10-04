package bitmapgrid.ui;

import javax.swing.Box;

import bitmapgrid.controls.ReactiveIntegerSpinner;
import bitmapgrid.controls.ReactiveNumberField;
import bitmapgrid.observable.BinaryCombiner;
import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;

public class GridControlPanel extends VerticallyStackedPanel implements IConnectable {

    // public final IObservable<double[]> panelDimension;
    // public final IObservable<int[]> tilesNumber;

    private static final long serialVersionUID = 1L;

    private ReactiveNumberField panelWidth;
    private ReactiveNumberField panelHeight;
    private ReactiveIntegerSpinner nColumns;
    private ReactiveIntegerSpinner nRows;
    private ReactiveNumberField imageBorderWidth;
    private ReactiveNumberField groupBorderWidth;

    // private JComboBox packingMode;

    public GridControlPanel() {

        panelWidth = new ReactiveNumberField();
        panelHeight = new ReactiveNumberField();

        nColumns = new ReactiveIntegerSpinner();
        nRows = new ReactiveIntegerSpinner();

        imageBorderWidth = new ReactiveNumberField();
        groupBorderWidth = new ReactiveNumberField();

        addLabeledComponent(panelWidth, "Panel width");
        addLabeledComponent(panelHeight, "Panel height");
        addLabeledComponent(nColumns, "Columns");
        addLabeledComponent(nRows, "Rows");
        addLabeledComponent(imageBorderWidth, "Image border");
        addLabeledComponent(groupBorderWidth, "Group border");
        add(Box.createVerticalGlue());
    }

    @Override
    public void onPublication(IPublicationVisitor pub) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        IObservable<double[]> imageDimensions = (IObservable<double[]>) sub.retrieveObservable("imageDimensions");

        IObservable<Integer> maxColumns = new BinaryCombiner<Double, double[], Integer>(panelWidth.observable, imageDimensions,
                (w, s) -> (int) Math.floor(w / s[0]));
        maxColumns.addObserver(nColumns.maxValue);
        maxColumns.addObserver(nColumns);
        
        IObservable<Integer> maxRows = new BinaryCombiner<Double, double[], Integer>(panelHeight.observable, imageDimensions,
                (h, s) -> (int) Math.floor(h / s[1]));
        maxRows.addObserver(nRows.maxValue);
        maxRows.addObserver(nRows);
    }
}
