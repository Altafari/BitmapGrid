package bitmapgrid.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Map;

import bitmapgrid.observable.Signal;

public class GridDocumentModel implements IImageDocumentModel {
    
    @Override
    public BufferedImage getUpdatedDocument(Map<Signal, Object> parameters) {
        
        BufferedImage srcImage = (BufferedImage) parameters.get(Signal.SourceImage);
        int[] imageSize = new int[] { srcImage.getWidth(), srcImage.getHeight() };
        Double pixelPerMm = (Double) parameters.get(Signal.PixelPerMm);
        int[] numTiles = (int[]) parameters.get(Signal.TilesNumber);
        double[] panelDims = (double[]) parameters.get(Signal.PanelDimension);
        int[] panelSize = new int[] { (int) Math.ceil(panelDims[0] * pixelPerMm), (int) Math.ceil(panelDims[1] * pixelPerMm) };
        BufferedImage dstImage = new BufferedImage(panelSize[0], panelSize[1], BufferedImage.TYPE_INT_RGB);
        Point[][] location = computeTilesLocation(numTiles, panelSize, imageSize);
        drawGrid(dstImage, location, imageSize, srcImage);
        return dstImage;
    }
    
    private Point[][] computeTilesLocation(int[] numTiles, int[] panelSize, int[] imageSize) {
        int rows = numTiles[0], cols = numTiles[1];
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
    
    private void drawGrid(BufferedImage dstImage, Point[][] coords, int[] imageSize, BufferedImage srcImage) {
        Graphics2D g = dstImage.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, dstImage.getWidth(), dstImage.getHeight());
        g.setColor(Color.pink);
        for (Point[] pArr : coords) {
            for (Point p : pArr) {
                g.drawImage(srcImage, p.x, p.y, null);
            }
        }
        g.dispose();
     }
}
