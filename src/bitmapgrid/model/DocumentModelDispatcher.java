package bitmapgrid.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
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

    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        for (String s : docParamsList) {
            IObservable<?> obs = ((IObservable<?>) sub.retrieveObservable(s));
            // TODO
        }
    }
    
    private ArrayList<String> initializeDocumentParametersList() {
        return new ArrayList<String>();
    }
    
    private Map<String, Object> initializeDocumentParametersCache() {
        Map<String, Object> res = new HashMap<String, Object>(docParamsList.size());
        for (String s : docParamsList) {
            res.put(s, null);
        }
        return res;
    }
    
    private void onParameterChange() {
        
    }

}
