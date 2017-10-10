package bitmapgrid.model;

import java.awt.image.BufferedImage;
import java.util.Map;

public class GridDocumentModel implements IImageDocumentModel {

    @Override
    public BufferedImage getUpdatedDocument(Map<String, Object> parameters) {
        
        BufferedImage inputImage = (BufferedImage) parameters.get("Image");
        Double pxPerMm = (Double) parameters.get("PixelsPerMm");
        int[] nTiles = (int[]) parameters.get("NumTiles");
        double[] docDims = (double[]) parameters.get("DocDims");
        int[] docSize = new int[] { (int) Math.ceil(docDims[0] * pxPerMm), (int) Math.ceil(docDims[1] * pxPerMm) };
                
        return null;
    }

}
