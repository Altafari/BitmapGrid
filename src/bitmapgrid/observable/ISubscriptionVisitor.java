package bitmapgrid.observable;

public interface ISubscriptionVisitor {
    public Object getObservable(Signal sig);
}
