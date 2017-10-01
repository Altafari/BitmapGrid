package bitmapgrid.controls;

import javax.swing.SpinnerNumberModel;

public class ReactiveIntegerSpinner extends ReactiveSpinner<Integer> {

    private static final long serialVersionUID = 1L;

    public ReactiveIntegerSpinner() {
        super(new SpinnerNumberModel(1, 1, 5, 1)); // Stub here
    }

    @Override
    protected Integer extractValue(Object obj) {
        return ((Integer) obj);
    }

}
