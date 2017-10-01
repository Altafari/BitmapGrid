package bitmapgrid.controls;

import java.text.Format;

import javax.swing.JFormattedTextField;

import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveFormattedTextField<T> extends JFormattedTextField
        implements IObserver<T>, IObservable<T> {

    private static final long serialVersionUID = 1L;
    private final ObservableCore<T> observable = new ObservableCore<T>() {
        @Override
        public T getObservableValue() {
            return extractValue(getValue());
        }
    };

    public ReactiveFormattedTextField(Format fmt) {
        super(fmt);
        setHorizontalAlignment(RIGHT);
        addActionListener(a -> observable.notifyObservers(getObservableValue()));
    }

    @Override
    public boolean addObserver(IObserver<T> obs) {
        return observable.addObserver(obs);
    }

    @Override
    public boolean removeObserver(IObserver<T> obs) {
        return observable.removeObserver(obs);
    }

    @Override
    public T getObservableValue() {
        return observable.getObservableValue();
    }

    @Override
    public void notifyChanged(T newVal) {
        // Does it notify here?
        setValue(newVal);
    }

    protected abstract T extractValue(Object obj);
}
