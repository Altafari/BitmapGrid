package bitmapgrid.ui;

import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import bitmapgrid.observable.IObservable;

public class GridControlPanel extends VerticallyStackedPanel {

    public IObservable<double[]> panelDimension;
    public IObservable<int[]> panelFragments;
    
    private IObservable<int[]> maxFragments;
    
    private static final long serialVersionUID = 1L;
    
    private JFormattedTextField panelWidth;
    private JFormattedTextField panelHeight;
    private JSpinner nColumns;
    private SpinnerModel nColumnsModel;
    private JSpinner nRows;
    private SpinnerModel nRowsModel;
    private JFormattedTextField imageBorderWidth;
    private JFormattedTextField groupBorderWidth;
    private JComboBox packingMode;
    
    public GridControlPanel() {
        panelWidth = new JFormattedTextField(NumberFormat.getInstance());

        panelHeight = new JFormattedTextField(NumberFormat.getInstance());
        
        nColumnsModel = new SpinnerNumberModel(1, 1, 5, 1);
        nColumns = new JSpinner(nColumnsModel);        
        nRowsModel = new SpinnerNumberModel(1, 1, 4, 1);        
        nRows = new JSpinner(nRowsModel);

        imageBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        imageBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);
        groupBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        groupBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);
        
        addLabeledComponent(panelWidth, "Panel width");
        addLabeledComponent(panelHeight, "Panel height");
        addLabeledComponent(nColumns, "Columns");
        addLabeledComponent(nRows, "Rows");
        addLabeledComponent(imageBorderWidth, "Image border");
        addLabeledComponent(groupBorderWidth, "Group border");
        add(Box.createVerticalGlue());
    }
}
