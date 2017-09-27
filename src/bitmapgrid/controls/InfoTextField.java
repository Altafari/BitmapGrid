package bitmapgrid.controls;

import javax.swing.JTextField;

import bitmapgrid.observable.IObserver;

public abstract class InfoTextField<T> extends JTextField implements IObserver<T> {

    private static final long serialVersionUID = 1L;

    public InfoTextField() {
        setEditable(false);
        setHorizontalAlignment(getAlignment());
    }
    
    @Override
    public abstract void notifyChanged(T newVal);
    
    protected int getAlignment() {
        return RIGHT;
    }
}
