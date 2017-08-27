package bitmapgrid.image;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageGridDocument {
    
    private float[] documentSize;
    private ArrayList<ArrayList<ScaledImage>> imageGrid;

    public ImageGridDocument() {};
    
    public void drawDocument(Graphics g) {
        
    }
    
    public int getNumRows() {
        return imageGrid.size();
    }
    
    public int getRowSize(int nRow) {
        return imageGrid.get(nRow).size();
    }
    
    private void insertImage(ScaledImage img, int row, int col) {
        imageGrid.get(row).set(col, img);
    }
    
    private void cleanupEmptyRows() {
        ArrayList<ArrayList<ScaledImage>> newGrid = new ArrayList<ArrayList<ScaledImage>>();
        for (ArrayList<ScaledImage> row : imageGrid) {
            if (!row.isEmpty()) {
                newGrid.add(row);
            }
        }
        imageGrid = newGrid;
    }
    
    private void computeOrigins() {
        // TODO: take out maximum dimensions computation into separate method
        float totalWidth = 0.0f;
        float totalHeight = 0.0f;
        ArrayList<Float> rowMaxY = new ArrayList<Float>();
        for (ArrayList<ScaledImage> row : imageGrid) {
            float rowMaxHeight = 0.0f;
            for (ScaledImage img : row) {
                float[] size = img.getSize();
                totalWidth += size[0];
                rowMaxHeight = Math.max(rowMaxHeight, size[1]);
            }
            rowMaxY.add(rowMaxHeight);
            totalHeight += rowMaxHeight;
            float hGap = (documentSize[0] - totalWidth) / (row.size() + 1);
            float currX = hGap;            
            for (ScaledImage img : row) {
                img.setOriginX(currX);
                currX += img.getSize()[0] + hGap;
            }
        }
        float vGap = (documentSize[1] - totalHeight) / (imageGrid.size() + 1);
        float currY = vGap;
        Iterator<Float> iterMaxY = rowMaxY.iterator();
        for (ArrayList<ScaledImage> row : imageGrid) {
            float currMaxY = iterMaxY.next();
            for (ScaledImage img : row) {
                img.setOriginY(currY + (currMaxY - img.getSize()[1]) * 0.5f);
            }
        }
    }
}
