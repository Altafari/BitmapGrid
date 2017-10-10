package bitmapgrid.model;

import java.awt.image.BufferedImage;
import java.util.Map;

import bitmapgrid.observable.Signal;

public interface IImageDocumentModel {
    BufferedImage getUpdatedDocument(Map<Signal, Object> parameters);
}
