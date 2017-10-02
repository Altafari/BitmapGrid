package bitmapgrid.controls;

import javax.swing.SpinnerNumberModel;

import bitmapgrid.observable.IObserver;

public class ReactiveIntegerSpinner extends ReactiveSpinner<Integer> {

    private static final long serialVersionUID = 1L;

    public final IObserver<Integer> maxValue = new IObserver<Integer>() {

        @Override
        public void notifyChanged(Integer newMaxVal) {
            SpinnerNumberModel currModel = (SpinnerNumberModel) getModel();
            int currVal = (Integer) currModel.getValue();
            int minVal = (Integer) currModel.getMinimum();

            if (newMaxVal == null || newMaxVal < minVal) {
                return;
            }

            SpinnerNumberModel newModel = new SpinnerNumberModel(Math.min(Math.max(currVal, minVal), newMaxVal), minVal,
                    newMaxVal.intValue(), 1);

            setModel(newModel);
        }
    };

    public ReactiveIntegerSpinner() {
        this(1, 1, 1);
    }

    public ReactiveIntegerSpinner(int value, int minVal, int maxVal) {
        super(new SpinnerNumberModel(value, minVal, maxVal, 1));
    }

    @Override
    protected Integer extractValue(Object obj) {
        return ((Integer) obj);
    }

    @Override
    protected boolean isValidValue(Object obj) {
        Integer newVal = (Integer) obj;
        SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        return newVal != null && newVal >= (Integer) model.getMinimum() && newVal <= (Integer) model.getMaximum();
    }

}
