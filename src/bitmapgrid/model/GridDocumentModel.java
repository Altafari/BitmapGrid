package bitmapgrid.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Map;

import bitmapgrid.observable.Signal;

public class GridDocumentModel implements IImageDocumentModel {
    
    @Override
    public BufferedImage getUpdatedDocument(Map<Signal, Object> parameters) {
        int[] numTiles = (int[]) parameters.get(Signal.TilesNumber);
        Double zoom = (Double) parameters.get(Signal.DocumentZoom);
        if (numTiles[0] < 1 || numTiles[1] < 1 || zoom == 0.0) {
            return null;
        }
        BufferedImage srcImage = (BufferedImage) parameters.get(Signal.SourceImage);
        int[] imageSize = new int[] { srcImage.getWidth(), srcImage.getHeight() };
        Double pixelPerMm = (Double) parameters.get(Signal.PixelPerMm);
        double[] panelDims = (double[]) parameters.get(Signal.PanelDimension);
        int[] panelSize = new int[] { (int) Math.ceil(panelDims[0] * pixelPerMm), (int) Math.ceil(panelDims[1] * pixelPerMm) };
        int[] docSize = new int[] { (int) Math.ceil(panelSize[0] * zoom), (int) Math.ceil(panelSize[1] * zoom) };
        BufferedImage dstImage = new BufferedImage(docSize[0], docSize[1], BufferedImage.TYPE_INT_RGB);
        Point[][] location = computeTilesLocation(numTiles, panelSize, imageSize);
        drawGrid(dstImage, location, imageSize, srcImage, zoom);
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
    
    private void drawGrid(BufferedImage dstImage, Point[][] coords, int[] imageSize, BufferedImage srcImage, double zoom) {
        Graphics2D g = dstImage.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, dstImage.getWidth(), dstImage.getHeight());
        g.setColor(Color.pink);
        for (Point[] pArr : coords) {
            for (Point p : pArr) {
                AffineTransform at = AffineTransform.getTranslateInstance(p.x * zoom, p.y * zoom);
                at.scale(zoom, zoom);
                g.drawImage(srcImage, at, null);
            }
        }
        g.dispose();
     }
}
