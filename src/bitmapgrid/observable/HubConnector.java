package bitmapgrid.observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HubConnector implements IPublicationVisitor, ISubscriptionVisitor {

    public final ArrayList<IConnectable> connectables;
    public final Map<String, Object> nodes;

    public HubConnector() {
        connectables = new ArrayList<IConnectable>();
        nodes = new HashMap<String, Object>();
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
    public void publishObservable(String name, Object obs) {
        if (nodes.containsKey(name)) {
            throw new IllegalArgumentException("Name " + name + " is already published");
        }
        nodes.put(name, obs);
    }

    @Override
    public Object retrieveObservable(String name) {
        if (!nodes.containsKey(name)) {
            throw new IllegalArgumentException("Name " + name + "is not published");
        }
        return nodes.get(name);
    }
}
