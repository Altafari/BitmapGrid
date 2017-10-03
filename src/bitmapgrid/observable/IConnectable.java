package bitmapgrid.observable;

import java.util.Map;

public interface IConnectable {
    
    void addObservablesToMap(Map<String, Object> map);

    void wireUpObservables(Map<String, Object> map);
}
