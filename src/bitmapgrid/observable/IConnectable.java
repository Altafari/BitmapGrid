package bitmapgrid.observable;

public interface IConnectable {

    void onPublication(HubConnector hub);
    void onSubscription(HubConnector hub);
}
