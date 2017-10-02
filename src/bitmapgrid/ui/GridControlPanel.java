package bitmapgrid.ui;

import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JFormattedTextField;

import bitmapgrid.controls.ReactiveIntegerSpinner;
import bitmapgrid.observable.IObservable;

public class GridControlPanel extends VerticallyStackedPanel {

    public IObservable<double[]> panelDimension;
    public IObservable<int[]> panelFragments;

    // private IObservable<int[]> maxFragments;

    private static final long serialVersionUID = 1L;

    private JFormattedTextField panelWidth;
    private JFormattedTextField panelHeight;
    private ReactiveIntegerSpinner nColumns;
    private ReactiveIntegerSpinner nRows;
    private JFormattedTextField imageBorderWidth;
    private JFormattedTextField groupBorderWidth;
    // private JComboBox packingMode;

    public GridControlPanel() {
        panelWidth = new JFormattedTextField(NumberFormat.getInstance());

        panelHeight = new JFormattedTextField(NumberFormat.getInstance());

        nColumns = new ReactiveIntegerSpinner(1, 1, 5);
        nRows = new ReactiveIntegerSpinner();
        nColumns.observable.addObserver(nRows.maxValue);

        ReactiveIntegerSpinner mSp = new ReactiveIntegerSpinner(1, 1, 5);
        mSp.observable.addObserver(nRows);

        imageBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        imageBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);
        groupBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        groupBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);

        addLabeledComponent(panelWidth, "Panel width");
        addLabeledComponent(panelHeight, "Panel height");

        addLabeledComponent(mSp, "Master");

        addLabeledComponent(nColumns, "Columns");
        addLabeledComponent(nRows, "Rows");
        addLabeledComponent(imageBorderWidth, "Image border");
        addLabeledComponent(groupBorderWidth, "Group border");
        add(Box.createVerticalGlue());
    }
}
