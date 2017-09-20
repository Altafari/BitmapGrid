package bitmapgrid.ui;

import java.text.NumberFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class GridControlPanel extends VerticallyStackedPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JSpinner nColumns;
    private SpinnerModel nColumnsModel;
    private JSpinner nRows;
    private SpinnerModel nRowsModel;
    private JFormattedTextField imageBorderWidth;
    private JFormattedTextField groupBorderWidth;
    private JComboBox packingMode;
    
    public GridControlPanel() {
        nColumnsModel = new SpinnerNumberModel(1, 1, 5, 1);
        nColumns = new JSpinner(nColumnsModel);
        
        nRowsModel = new SpinnerNumberModel(1, 1, 4, 1);        
        nRows = new JSpinner(nRowsModel);
        imageBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        imageBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);
        groupBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        groupBorderWidth.setHorizontalAlignment(JFormattedTextField.TRAILING);
        
        addLabeledComponent(nColumns, "Num. cols");
        addLabeledComponent(nRows, "Num. rows");
        addLabeledComponent(imageBorderWidth, "Image border");
        addLabeledComponent(groupBorderWidth, "Group border");
    }
}
