package bitmapgrid.observable;

public interface IPublicationVisitor {
    void publishObservable(Signal sig, Object obs);
}
