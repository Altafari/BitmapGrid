package bitmapgrid.observable;

public class Observable<T> extends ObservableCore<T> implements IObserver<T> {
    
    protected T value;
    
    public Observable() {
        super();
        value = null;
    }
    
    public Observable(T val) {
        super();
        value = val;
    }

    @Override
    public void notifyChanged(T val) {
        value = val;
        notifyObservers(value);            
    }

    @Override
    public T getValue() {
        return value;
    }    
}
