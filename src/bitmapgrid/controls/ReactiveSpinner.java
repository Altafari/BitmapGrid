package bitmapgrid.controls;

import javax.swing.JSpinner;

import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.ObservableCore;

public abstract class ReactiveSpinner<T> extends JSpinner implements IObserver<T>, IObservable<T> {

    private static final long serialVersionUID = 1L;
    private ObservableCore<T> observable = new ObservableCore<T>() {
        @Override public T getValue() {
            return null;
        }        
    };
    
    private IObserver<T> maxValueObserver = new IObserver<T>() {
        @Override
        public void notifyChanged(T newVal) {
            // TODO Auto-generated method stub
            
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void notifyChanged(T newVal) {
        // TODO Auto-generated method stub
        
    }
    
    public IObserver<T> getMaxValueObserver() {
        return maxValueObserver;
    }

}
