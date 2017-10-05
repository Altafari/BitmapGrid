package bitmapgrid.image;

import java.awt.image.BufferedImage;

import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;

public class SimpleImageGrid {
    
    public SimpleImageGrid(
            IObserver<BufferedImage> outputImage,
            IObservable<BufferedImage> inputImage,
            IObservable<double[]> outputSize,
            IObservable<double[]> inputSize,
            IObservable<int[]> numTiles,
            IObservable<Double> dpi) {
    }
}
