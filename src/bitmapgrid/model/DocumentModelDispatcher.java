package bitmapgrid.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Timer;

import bitmapgrid.observable.IConnectable;
import bitmapgrid.observable.IObservable;
import bitmapgrid.observable.IObserver;
import bitmapgrid.observable.IPublicationVisitor;
import bitmapgrid.observable.ISubscriptionVisitor;
import bitmapgrid.observable.Observable;
import bitmapgrid.observable.Signal;

public class DocumentModelDispatcher implements IConnectable {

    private enum DebounceState {
        READY, JUST_CHANGED, RUN_UPDATE
    }
    
    private final Observable<BufferedImage> documentImage;
    private final ArrayList<Signal> docParamsList;
    private final Map<Signal, Object> docParamsCache;
    private final Timer dbTimer;
    private final int DEBOUNCE_TIME_MS = 50;
    private DebounceState dbState;
    private int triggerCounter;
    
    public DocumentModelDispatcher() {
        documentImage = new Observable<BufferedImage>();
        docParamsList = initializeDocumentParametersList();
        docParamsCache = initializeDocumentParametersCache();
        dbState = DebounceState.READY;
        ActionListener timerEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(dbState) {
                case RUN_UPDATE:
                    onParametersUpdate();
                    dbState = DebounceState.READY;
                    break;
                case JUST_CHANGED:
                    dbState = DebounceState.RUN_UPDATE;
                    break;
                case READY:
                default:
                }
            }
        };
        dbTimer = new Timer(DEBOUNCE_TIME_MS, timerEvent);
        dbTimer.start();
    }
    
    @Override
    public void onPublication(IPublicationVisitor pub) {
        pub.publishObservable(Signal.DocumentImage, documentImage);
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public void onSubscription(ISubscriptionVisitor sub) {
        for (Signal s : docParamsList) {
            ((IObservable<Object>) sub.retrieveObservable(s)).addObserver(new IObserver<Object>() {
                @Override
                public void notifyChanged(Object newVal) {
                    docParamsCache.put(s, newVal);
                    dbState = DebounceState.JUST_CHANGED;
                }
            });
        }
    }
    
    private ArrayList<Signal> initializeDocumentParametersList() {
        ArrayList<Signal> res = new ArrayList<Signal>();
        res.add(Signal.PanelDimension);
        res.add(Signal.TilesNumber);
        res.add(Signal.SourceImage);
        res.add(Signal.ImageDpi);
        return res;
    }
    
    private Map<Signal, Object> initializeDocumentParametersCache() {
        Map<Signal, Object> res = new HashMap<Signal, Object>(docParamsList.size());
        for (Signal s : docParamsList) {
            res.put(s, null);
        }
        return res;
    }
    
    private void onParametersUpdate() {
        System.out.println("Update # " + triggerCounter++);
        double[] panelDimension = (double[]) docParamsCache.get("PanelDimension");
        if (panelDimension != null) {
            System.out.println("Panel dimensions: W = " + panelDimension[0] + ", H = " + panelDimension[1]);
        }
        int[] tilesNumber = (int[]) docParamsCache.get("TilesNumber");
        if (tilesNumber != null) {
            System.out.println("Columns: " + tilesNumber[0] + ", Rows: " + tilesNumber[1]);
        }
        Double imageDpi = (Double) docParamsCache.get("ImageDpi");
        if (imageDpi != null) {
            System.out.println("Image DPI: " + imageDpi);
        }
    }

}
