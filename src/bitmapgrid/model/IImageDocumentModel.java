package bitmapgrid.model;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface IImageDocumentModel {
    BufferedImage getUpdatedDocument(Map<String, Object> parameters);
}
