package bitmapgrid.controls;

import javax.swing.JFormattedTextField;

import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveFormattedTextField<T> extends JFormattedTextField implements IObserver<T>, IObservable<T> {

    private static final long serialVersionUID = 1L;
    private ObservableCore<T> observable = new ObservableCore<T>() {
        @Override public T getValue() {
            return null;
        }        
    };

    @Override
    public boolean addObserver(IObserver<T> obs) {
        return observable.addObserver(obs);
    }

    @Override
    public boolean removeObserver(IObserver<T> obs) {
        return observable.removeObserver(obs);
    }

    @Override
    public T getValue() {
        return observable.getValue();
    }

    @Override
    public void notifyChanged(T newVal) {
        // TODO Auto-generated method stub        
    }

}
