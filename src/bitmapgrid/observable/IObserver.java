package bitmapgrid.observable;

public interface IObserver<T> {

    void notifyChanged(T newVal);    
}
