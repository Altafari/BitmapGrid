package bitmapgrid.ui;

import java.util.Map;

import javax.swing.Box;
import javax.swing.JFormattedTextField;

import bitmapgrid.controls.ReactiveIntegerSpinner;
import bitmapgrid.controls.ReactiveNumberField;
import bitmapgrid.observable.BinaryCombiner;
import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;

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

    private IObservable<double[]> imageSize;
    // private JComboBox packingMode;

    public GridControlPanel() {

        panelWidth = new ReactiveNumberField();
        panelHeight = new ReactiveNumberField();

        nColumns = new ReactiveIntegerSpinner();
        nRows = new ReactiveIntegerSpinner();

        imageBorderWidth = new ReactiveNumberField();
        groupBorderWidth = new ReactiveNumberField();

        // TODO: This is a stub. Needs to be wired up properly.
        imageSize = new BinaryCombiner<Double, Double, double[]>(imageBorderWidth.observable,
                groupBorderWidth.observable, (w, h) -> new double[] { w, h });

        IObservable<Integer> maxColumns = new BinaryCombiner<Double, double[], Integer>(panelWidth.observable,
                imageSize, (w, s) -> (int) Math.floor(w / s[0]));
        maxColumns.addObserver(nColumns.maxValue);
        IObservable<Integer> maxRows = new BinaryCombiner<Double, double[], Integer>(panelHeight.observable, imageSize,
                (h, s) -> (int) Math.floor(h / s[1]));
        maxRows.addObserver(nRows.maxValue);

        addLabeledComponent(panelWidth, "Panel width");
        addLabeledComponent(panelHeight, "Panel height");
        addLabeledComponent(nColumns, "Columns");
        addLabeledComponent(nRows, "Rows");
        addLabeledComponent(imageBorderWidth, "Image border");
        addLabeledComponent(groupBorderWidth, "Group border");
        add(Box.createVerticalGlue());
    }

    @Override
    public void addObservablesToMap(Map<String, Object> map) {
        map.put("PanelWidth", panelWidth.observable);
        
    }

    @Override
    public void wireUpObservables(Map<String, Object> map) {
        // TODO Auto-generated method stub
        
    }
}
