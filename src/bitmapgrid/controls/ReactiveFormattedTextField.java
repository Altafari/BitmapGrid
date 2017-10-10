package bitmapgrid.controls;

import java.text.Format;

import javax.swing.JFormattedTextField;

import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveFormattedTextField<T> extends JFormattedTextField implements IObserver<T> {

    private static final long serialVersionUID = 1L;

    public final ObservableCore<T> observable = new ObservableCore<T>() {
        @Override
        public T getObservableValue() {
            return extractValue(getValue());
        }
    };

    public ReactiveFormattedTextField(Format fmt) {
        super(fmt);
        setHorizontalAlignment(RIGHT);
        addPropertyChangeListener(a -> observable.notifyObservers(observable.getObservableValue()));
    }

    @Override
    public void notifyChanged(T newVal) {
        setValue(newVal);
    }

    protected abstract T extractValue(Object obj);
}
