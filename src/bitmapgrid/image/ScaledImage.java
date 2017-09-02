package bitmapgrid.image;

public class ScaledImage {
    
    private float dpi;
    private float[] origin;
    private float[] size;
    
    public boolean isPointOnImage(float[] point) {
        for (int i = 0; i < 2; i++) {
            if (origin[i] > point[i]) {
                return false;
            }
            if (origin[i] + size[i] < point[i]) {
                return false;
            }
        }
        return true;
    }
    
    public float[] getSize() {
        return new float[] { 0.0f, 0.0f };
    };
    public float[] getOrigin() {
        return new float[] { 0.0f, 0.0f };
    };
    public void setOrigin(float[] origin) {
        
    };
}
