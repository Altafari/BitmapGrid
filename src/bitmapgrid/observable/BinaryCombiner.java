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
        x.addObserver((s) -> notifyObservers(func.apply(s, y.getObservableValue())));
        y.addObserver((s) -> notifyObservers(func.apply(x.getObservableValue(), s)));
    }

    @Override
    public R getObservableValue() {
        return func.apply(x.getObservableValue(), y.getObservableValue());
    }
}
