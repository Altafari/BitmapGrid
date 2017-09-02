package bitmapgrid.ui;

public class ViewPort {
    
    private int[] portSize;     // Viewport size in screen pixels
    private float[] origin;     // Image [0, 0] position on projection
    private float pixelSize;    // Pixel `  size in document units
    
    public void move(int[] offset) {
        for (int i = 0; i < 2; i++) {
            origin[i] += offset[i] * pixelSize;
        }
    }
    
    public void zoom(int[] point, float scale) {
        
    }
    
    public int[] getCenter() {
        return new int[] {portSize[0] / 2, portSize[1] / 2};
    }
    
    public void reset(float[] documentSize, float margin) {
        
    }
}
