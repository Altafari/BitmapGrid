package bitmapgrid.observable;

public class ChangeTrigger<T> extends ObservableCore<T> {

    private final IObservable<T> obs;
    private T state;
    
    public ChangeTrigger(IObservable<T> observable) {
        obs = observable;
        state = obs.getObservableValue();
        obs.addObserver(s -> {
            if (s != state) {
                state = s;
                this.notifyObservers(s);
            }
        });
    }
    
    @Override
    public T getObservableValue() {
        return obs.getObservableValue();
    }

}
