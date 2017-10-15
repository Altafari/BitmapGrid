package bitmapgrid.observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HubConnector implements IPublicationVisitor, ISubscriptionVisitor {

    public final ArrayList<IConnectable> connectables;
    public final Map<Signal, Object> nodes;

    public HubConnector() {
        connectables = new ArrayList<IConnectable>();
        nodes = new HashMap<Signal, Object>();
    }

    public void wireUp() {
        for (IConnectable c : connectables) {
            c.onPublication(this);
        }
        for (IConnectable c : connectables) {
            c.onSubscription(this);
        }
    }

    @Override
    public void publishObservable(Signal sig, Object obs) {
        if (nodes.containsKey(sig)) {
            throw new IllegalArgumentException("Name " + sig + " is already published");
        }
        nodes.put(sig, obs);
    }

    @Override
    public Object getObservable(Signal sig) {
        if (!nodes.containsKey(sig)) {
            throw new IllegalArgumentException("Name " + sig + "is not published");
        }
        return nodes.get(sig);
    }
}
