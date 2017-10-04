package bitmapgrid.observable;

import java.util.function.Function;

public class FunctionMapper<T, R> extends ObservableCore<R> {

    private final IObservable<T> x;
    private final Function<T, R> func;

    public FunctionMapper(IObservable<T> x, Function<T, R> func) {
        this.x = x;
        this.func = func;
        x.addObserver((s) -> notifyObservers((s == null)? null : func.apply(s)));
    }

    @Override
    public R getObservableValue() {
        T xVal = x.getObservableValue();
        if (xVal == null){
            return null;
        }
        return func.apply(xVal);
    }

}
