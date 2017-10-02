package bitmapgrid.controls;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveSpinner<T> extends JSpinner implements IObserver<T>, IObservable<T> {

    private static final long serialVersionUID = 1L;

    private final ObservableCore<T> observable = new ObservableCore<T>() {
        @Override
        public T getObservableValue() {
            return extractValue(getValue());
        }
    };

    private final IObserver<SpinnerModel> modelObserver = new IObserver<SpinnerModel>() {
        @Override
        public void notifyChanged(SpinnerModel newVal) {
            setModel(newVal);
        }
    };

    public ReactiveSpinner(SpinnerModel model) {
        super(model);
        this.addChangeListener(e -> observable.notifyObservers(observable.getObservableValue()));
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
        // ???
        this.setValue(newVal);
    }

    public IObserver<SpinnerModel> getModelObserver() {
        return modelObserver;
    }

    protected abstract T extractValue(Object obj);
}
