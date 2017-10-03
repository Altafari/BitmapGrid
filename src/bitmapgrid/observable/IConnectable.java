package bitmapgrid.observable;

public interface IConnectable {

    void onPublication(IPublicationVisitor pub);
    void onSubscription(ISubscriptionVisitor sub);
}
