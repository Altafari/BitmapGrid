package bitmapgrid.model;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Map;

import bitmapgrid.observable.Signal;

public class GridDocumentModel implements IImageDocumentModel {
    
    private BufferedImage image; 
    
    @Override
    public BufferedImage getUpdatedDocument(Map<Signal, Object> parameters) {
        
        BufferedImage srcImage = (BufferedImage) parameters.get(Signal.SourceImage);
        int[] imageSize = new int[] { srcImage.getWidth(), srcImage.getHeight() };
        Double pixelPerMm = (Double) parameters.get(Signal.PixelPerMm);
        int[] numTiles = (int[]) parameters.get(Signal.TilesNumber);
        double[] panelDims = (double[]) parameters.get(Signal.PanelDimension);
        int[] panelSize = new int[] { (int) Math.ceil(panelDims[0] * pixelPerMm), (int) Math.ceil(panelDims[1] * pixelPerMm) };
        createNewImageIfResized(panelSize);
        Point[][] location = computeTilesLocation(numTiles, panelSize, imageSize);
        drawOutline(location, imageSize);
        return image;
    }
    
    private void createNewImageIfResized(int[] dims) {
        if (image == null || image.getWidth() != dims[0] || image.getHeight() != dims[1]) {
            image = new BufferedImage(dims[0], dims[1], BufferedImage.TYPE_INT_RGB);
        }
    }
    
    private Point[][] computeTilesLocation(int[] numTiles, int[] panelSize, int[] imageSize) {
        int cols = numTiles[0], rows = numTiles[1];
        double xStep = (double) panelSize[0] / cols;
        double yStep = (double) panelSize[1] / rows;
        double xOffset = (xStep - imageSize[0]) / 2.0;
        double yOffset = (yStep - imageSize[1]) / 2.0;        
        Point[][] res =  new Point[cols][];
        for (int j = 0; j < cols; j++) {
            Point[] col = new Point[rows];
            for (int i = 0; i < rows; i++) {
                col[i] = new Point((int) Math.round(xStep * j + xOffset), (int) Math.round(yStep * i + yOffset));
            }
            res[j] = col;
        }
        return res;
    }
    
    private void drawOutline(Point[][] coords, int[] imageSize) {
        
    }
    

}
