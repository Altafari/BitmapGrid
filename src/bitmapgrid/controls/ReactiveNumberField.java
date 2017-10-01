package bitmapgrid.controls;

import java.text.NumberFormat;

public class ReactiveNumberField extends ReactiveFormattedTextField<Double> {

    private static final long serialVersionUID = 1L;

    public ReactiveNumberField() {
        super(NumberFormat.getInstance());
    }

    @Override
    protected Double extractValue(Object obj) {
        return ((Number) obj).doubleValue();
    }

}
