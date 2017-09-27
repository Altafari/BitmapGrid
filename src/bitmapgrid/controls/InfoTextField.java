package bitmapgrid.controls;

import javax.swing.JTextField;

import bitmapgrid.observable.IObserver;

public abstract class InfoTextField<T> extends JTextField implements IObserver<T> {

    private static final long serialVersionUID = 1L;

    public InfoTextField() {
        setEditable(false);
        setHorizontalAlignment(RIGHT);
    }
    
    @Override
    public abstract void notifyChanged(T newVal);    
}
