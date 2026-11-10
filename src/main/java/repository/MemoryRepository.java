package repository;

import domain.DataType;
import domain.GridLoadPoint;
import domain.MarketDocument;
import domain.MarketPoint;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MemoryRepository implements MarketRepository{
    private Map<DataType, Map<String, MarketDocument>> storage;
    // we use hashmap for the outter map because we don't need a relation
    // of order between elements
    // we just need to
    // store them in our database

    public MemoryRepository(){
        this.storage = new HashMap<>();

        for(DataType type : DataType.values()){
            this.storage.put(type, new TreeMap<>());
        }
    }

    @Override
    public void savePoint(DataType type, String date, MarketPoint point) {
        Map<String, MarketDocument> typeDocument = this.storage.get(type);
        MarketDocument dayDocument = typeDocument.computeIfAbsent(date, d -> {
            MarketDocument newDocument = new MarketDocument();
            newDocument.setDataType(type);
            newDocument.setDate(d);
            return newDocument;
        });
        // used lambda functions for computeIfAbsent

        dayDocument.addOrUpdatePoint(point);

    }

    @Override
    public Double getPreviousGridLoad(String date, int currentIndex){
        Map<String, MarketDocument> gridLoadDocument = this.storage.get(DataType.GRID_LOAD);
        if(gridLoadDocument == null) {
            return null;
        }
        MarketDocument dayDocument = gridLoadDocument.get(date);
        if(dayDocument == null) {
            return null;
        }
        TreeMap<Integer, MarketPoint> points = (TreeMap<Integer, MarketPoint>) dayDocument.getPointsMap();

        if(points == null || points.isEmpty()) {
            return null;
        }

        Map.Entry<Integer, MarketPoint> previousEntry = points.lowerEntry(currentIndex);

        if(previousEntry != null){
            GridLoadPoint previousPoint = (GridLoadPoint) previousEntry.getValue();
            return previousPoint.getQuantity();
        }

        return null;

    }

    @Override
    public MarketPoint getPoint(DataType type, String date, int intervalIndex){
        Map<String, MarketDocument> typeDocument = this.storage.get(type);
        if(typeDocument == null){
            return null;
        }
        MarketDocument document = typeDocument.get(date);
        if(document == null){
            return null;
        }
        Map<Integer, MarketPoint> pointMap = document.getPointsMap();
        if(pointMap == null){
            return null;
        }
        return pointMap.get(intervalIndex);
    }

    @Override
    public MarketPoint getLatestPoint(DataType type){
        Map<String, MarketDocument> typeDocument = this.storage.get(type);
        if(typeDocument == null || typeDocument.isEmpty()){
            return null;
        }
        TreeMap<String, MarketDocument> dayTree = (TreeMap<String, MarketDocument>) typeDocument;
        MarketDocument lastDocument = dayTree.lastEntry().getValue();

        Map<Integer, MarketPoint> dayPoints = lastDocument.getPointsMap();

        if(dayPoints == null || dayPoints.isEmpty()){
            return null;
        }

        TreeMap<Integer, MarketPoint> sortedPoints = (TreeMap<Integer, MarketPoint>) dayPoints;

        return sortedPoints.lastEntry().getValue();

    }

    @Override
    public MarketDocument getDocument(DataType type, String date){
        Map<String, MarketDocument> typeDocument = this.storage.get(type);
        if(typeDocument == null || typeDocument.isEmpty()){
            return null;
        }
        return typeDocument.get(date);
    }

}
