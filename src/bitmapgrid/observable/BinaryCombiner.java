package bitmapgrid.observable;

import java.util.function.BiFunction;

public class BinaryCombiner<U, V, R> extends ObservableCore<R> {

    private final IObservable<U> x;
    private final IObservable<V> y;
    private final BiFunction<U, V, R> func;

    public BinaryCombiner(IObservable<U> x, IObservable<V> y, BiFunction<U, V, R> func) {
        super();
        this.x = x;
        this.y = y;
        this.func = func;
        x.addObserver((s) -> {
            R res = null;
            if (s != null) {
                V yVal = y.getObservableValue();
                if (yVal != null) {
                    res = func.apply(s, yVal);
                }
            }
            notifyObservers(res);
        });

        y.addObserver((s) -> {
            R res = null;
            if (s != null) {
                U xVal = x.getObservableValue();
                if (xVal != null) {
                    res = func.apply(xVal, s);
                }
            }
            notifyObservers(res);
        });
    }

    @Override
    public R getObservableValue() {
        U xVal = x.getObservableValue();
        if (xVal == null){
            return null;
        }
        V yVal = y.getObservableValue();
        if (yVal == null){
            return null;
        }
        return func.apply(x.getObservableValue(), y.getObservableValue());
    }
}
