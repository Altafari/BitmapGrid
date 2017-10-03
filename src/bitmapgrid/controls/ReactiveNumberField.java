package bitmapgrid.controls;

import java.text.NumberFormat;

public class ReactiveNumberField extends ReactiveFormattedTextField<Double> {

    private static final long serialVersionUID = 1L;

    public ReactiveNumberField() {
        super(NumberFormat.getInstance());
    }

    @Override
    protected Double extractValue(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((Number) obj).doubleValue();
    }

}
