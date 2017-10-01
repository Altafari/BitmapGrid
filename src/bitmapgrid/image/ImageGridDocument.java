package bitmapgrid.image;

import java.awt.Graphics;
import java.util.ArrayList;

public class ImageGridDocument {

    private float[] documentSize;
    private ArrayList<ArrayList<ScaledImage>> imageGrid;

    public ImageGridDocument() {
    };

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
        float vGap = (documentSize[1] - getGridHeight()) / (imageGrid.size() + 1);
        float currY = vGap;
        for (ArrayList<ScaledImage> row : imageGrid) {
            float hGap = (documentSize[0] - getRowWidth(row)) / (row.size() + 1);
            float rowHeight = getRowHeight(row);
            float currX = hGap;
            for (ScaledImage img : row) {
                img.setOrigin(new float[] { currX, currY + (rowHeight - img.getSize()[1]) * 0.5f });
                currX += img.getSize()[0] + hGap;
            }
            currY += vGap + rowHeight;
        }
    }

    private float getRowWidth(ArrayList<ScaledImage> row) {
        float res = 0.0f;
        for (ScaledImage img : row) {
            res += img.getSize()[0];
        }
        return res;
    }

    private float getRowHeight(ArrayList<ScaledImage> row) {
        float res = 0.0f;
        for (ScaledImage img : row) {
            res = Math.max(res, img.getSize()[1]);
        }
        return res;
    }

    private float getGridHeight() {
        float res = 0.0f;
        for (ArrayList<ScaledImage> row : imageGrid) {
            res += getRowHeight(row);
        }
        return res;
    }
}
