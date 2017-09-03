package bitmapgrid.ui;

public class ViewPort {
    
    private int[] portSize;
    private float[] portPosition;
    private float portDpi;
    
    private float[] documentSize;
    
    public void move(int[] offset) {
        // TODO: wrong way
      
    }
    
    public void zoom(int[] point, float scale) {
        
    }
    
    public float getPortDpi() {
        return portDpi;
    }
    
    public int[] getCenter() {
        return new int[] {portSize[0] / 2, portSize[1] / 2};
    }
    
    public void reset(float[] documentSize) {
        
    }
    
    public int[] getDocumentOverhang() {
        int[] res = new int[2];
        for (int i = 0; i < 2 ; i++) {
            res[i] = (int)Math.max(0, Math.ceil(documentSize[i] - portSize[i] / portDpi)); 
        }
        return res;
    }
    
    public int[] getPortOffset() {
        int[] res = new int[2];
        for (int i = 0; i < 2; i++) {
            res[i] = (int)Math.round(portPosition[i] / portDpi);
        }
        return res;
    }
    
    public void setPortOffset(int[] offset) {
        // Clip? throw an exception for testing purposes
    }
    
    public int[] clipPortOffset(int[] offset) {
        int[] res = new int[2];
        int[] overhang = getDocumentOverhang();
        for (int i = 0; i < 2; i++) {
            res[i] = clipAbs(offset[i], overhang[i] / 2);
        }
        return res;
    }
    
    private int clipAbs(int val, int max) {
        if (val > 0) {
            return Math.min(val, max);
        }
        else {
            return Math.max(val, -max);
        }
    }
}
