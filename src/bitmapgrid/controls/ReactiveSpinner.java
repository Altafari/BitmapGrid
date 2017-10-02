package bitmapgrid.controls;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveSpinner<T> extends JSpinner implements IObserver<T> {

    private static final long serialVersionUID = 1L;

    public final ObservableCore<T> observable = new ObservableCore<T>() {
        @Override
        public T getObservableValue() {
            return extractValue(getValue());
        }
    };

    public ReactiveSpinner(SpinnerModel model) {
        super(model);
        this.addChangeListener(e -> observable.notifyObservers(observable.getObservableValue()));
    }

    @Override
    public void notifyChanged(T newVal) {
        if (this.isValidValue(newVal)) {
            this.setValue(newVal);
        }
    }

    protected abstract T extractValue(Object obj);

    protected abstract boolean isValidValue(Object obj);
}
