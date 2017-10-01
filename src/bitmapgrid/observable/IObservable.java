package bitmapgrid.observable;

public interface IObservable<T> {

    boolean addObserver(IObserver<T> obs);

    boolean removeObserver(IObserver<T> obs);

    T getObservableValue();

}