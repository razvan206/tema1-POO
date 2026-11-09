package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MarketDocument {
    private DataType dataType;
    private String biddingZoneDomain;
    private String periodStart;
    private String periodEnd;
    private String resolution;
    private String priceMeasureUnit;
    private String quantityMeasureUnit;
    private String currencyUnit;
    private Map<Integer, MarketPoint> points = new TreeMap<>();
    // we use TreeMap to have the intervals sorted out and to have O(1) acces time to elements

    public void addOrUpdatePoint(MarketPoint point){
        if(point != null && point.getIntervalIndex() >=1 && point.getIntervalIndex() <= 96)
            this.points.put(point.getIntervalIndex(), point);
    }
    // put the points in map

    public MarketPoint getPoint(int intervalIndex){
        return this.points.get(intervalIndex);
    }

    public void setPointsFromList(List<MarketPoint> pointsList){
        if(pointsList != null)
            for(MarketPoint p : pointsList)
                if(p != null)
                    this.points.put(p.getIntervalIndex(),p);
    }
    // populate the map from JSON

    public List<MarketPoint> getPointsAsList(){
        return new ArrayList<>(this.points.values());
    }

    public MarketDocument(DataType dataType, String biddingZoneDomain, String periodStart, String periodEnd, String resolution, String priceMeasureUnit, String quantityMeasureUnit, String currencyUnit, Map<Integer, MarketPoint> points) {
        this.dataType = dataType;
        this.biddingZoneDomain = biddingZoneDomain;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.resolution = resolution;
        this.priceMeasureUnit = priceMeasureUnit;
        this.quantityMeasureUnit = quantityMeasureUnit;
        this.currencyUnit = currencyUnit;
        this.points = points;
    }

    public String getBiddingZoneDomain() {
        return biddingZoneDomain;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public String getResolution() {
        return resolution;
    }

    public String getPriceMeasureUnit() {
        return priceMeasureUnit;
    }

    public String getQuantityMeasureUnit() {
        return quantityMeasureUnit;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public DataType getDataType() {
        return dataType;
    }

    public Map<Integer, MarketPoint> getPointsMap() {
        return points;
    }
    public void setPoints(Map<Integer, MarketPoint> points) {
        this.points = points;
    }
}

