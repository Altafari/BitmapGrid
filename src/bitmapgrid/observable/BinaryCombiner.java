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
            V yVal = y.getObservableValue();
            R res = (s != null && yVal != null) ? func.apply(s, yVal) : null;
            notifyObservers(res);
        });

        y.addObserver((s) -> {
            U xVal = x.getObservableValue();
            R res = (s != null && xVal != null) ? func.apply(xVal, s) : null;
            notifyObservers(res);
        });
    }

    @Override
    public R getObservableValue() {
        U xVal = x.getObservableValue();
        V yVal = y.getObservableValue();
        if (xVal != null && yVal != null) {
            return func.apply(x.getObservableValue(), y.getObservableValue());
        }
        return null;
    }
}
