package bitmapgrid.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Observable;

public class DocumentModelDispatcher implements IConnectable {

    private final Observable<BufferedImage> documentImage;
    private final ArrayList<String> docParamsList;
    private final Map<String, Object> docParamsCache;
    
    public DocumentModelDispatcher() {
        documentImage = new Observable<BufferedImage>();
        docParamsList = initializeDocumentParametersList();
        docParamsCache = initializeDocumentParametersCache();
    }
    
    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable("DocumentImage", documentImage);
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        for (String s : docParamsList) {
            ((IObservable<Object>) sub.retrieveObservable(s)).addObserver(new IObserver<Object>() {
                @Override
                public void notifyChanged(Object newVal) {
                    docParamsCache.put(s, newVal);
                    onParameterChange();
                }
            });
        }
    }
    
    private ArrayList<String> initializeDocumentParametersList() {
        ArrayList<String> res = new ArrayList<String>();
        res.add("PanelDimension");
        res.add("TilesNumber");
        res.add("SourceImage");
        return res;
    }
    
    private Map<String, Object> initializeDocumentParametersCache() {
        Map<String, Object> res = new HashMap<String, Object>(docParamsList.size());
        for (String s : docParamsList) {
            res.put(s, null);
        }
        return res;
    }
    
    private void onParameterChange() {
        // TODO: debouncing here using swing timer
        for (String key : docParamsCache.keySet()) {
            Object val = docParamsCache.get(key);
            String repr = (val == null) ? "null" : val.toString();
            System.out.println(key + " : " + repr);
        }
    }

}
