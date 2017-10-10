package bitmapgrid.observable;

public interface ISubscriptionVisitor {
    public Object retrieveObservable(Signal sig);
}
