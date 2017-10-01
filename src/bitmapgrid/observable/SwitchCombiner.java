package bitmapgrid.observable;

public class SwitchCombiner<T> extends ObservableCore<T> {

    private final IObservable<Boolean> selector;
    private final IObservable<T> x;
    private final IObservable<T> y;

    public SwitchCombiner(IObservable<Boolean> selector, IObservable<T> x, IObservable<T> y) {
        this.selector = selector;
        this.x = x;
        this.y = y;
        selector.addObserver((s) -> notifyObservers(s ? x.getObservableValue() : y.getObservableValue()));
        x.addObserver((s) -> {
            if (selector.getObservableValue())
                notifyObservers(s);
        });
        y.addObserver((s) -> {
            if (!selector.getObservableValue())
                notifyObservers(s);
        });
    }

    @Override
    public T getObservableValue() {
        return selector.getObservableValue() ? x.getObservableValue() : y.getObservableValue();
    }
}
