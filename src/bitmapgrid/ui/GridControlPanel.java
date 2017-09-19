package bitmapgrid.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridControlPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JFormattedTextField nColumns;
    private JFormattedTextField nRows;
    private JFormattedTextField imageBorderWidth;
    private JFormattedTextField groupBorderWidth;
    private JComboBox packingMode;
    
    private final Dimension preferredFieldSize = new Dimension(60, 18);
    
    public GridControlPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        nColumns = new JFormattedTextField(NumberFormat.getIntegerInstance());
        nColumns.setPreferredSize(preferredFieldSize);
        nRows = new JFormattedTextField(NumberFormat.getIntegerInstance());
        nRows.setPreferredSize(preferredFieldSize);
        imageBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        imageBorderWidth.setPreferredSize(preferredFieldSize);
        groupBorderWidth = new JFormattedTextField(NumberFormat.getNumberInstance());
        groupBorderWidth.setPreferredSize(preferredFieldSize);
        JPanel nColumnsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nColumnsPanel.add(new JLabel("Num. columns"));
        nColumnsPanel.add(nColumns);
        JPanel nRowsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nRowsPanel.add(new JLabel("Num. rows"));
        nRowsPanel.add(nRows);
        JPanel imageBorderWidthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        imageBorderWidthPanel.add(new JLabel("Image border"));
        imageBorderWidthPanel.add(imageBorderWidth);
        JPanel groupBorderWidthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        groupBorderWidthPanel.add(new JLabel("Group border"));
        groupBorderWidthPanel.add(groupBorderWidth);
        this.add(nColumnsPanel);
        this.add(nRowsPanel);
        this.add(imageBorderWidthPanel);
        this.add(groupBorderWidthPanel);
    }

}
