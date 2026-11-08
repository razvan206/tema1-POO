package domain;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class MarketPoint {
    protected String date;
    protected int intervalIndex;
    protected String interval;

    public String getDate() {
        return date;
    }

    public int getIntervalIndex(){
        return intervalIndex;
    }

    public String getInterval() {
        return interval;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public void setIntervalIndex(int intervalIndex) {
        this.intervalIndex = intervalIndex;
    }

    public MarketPoint(String date, String interval, int intervalIndex){
        this.date = date;
        this.interval = interval;
        this.intervalIndex = intervalIndex;
    }

    public abstract String validate(Double prevQuantity);
    public abstract void fillJsonPoint(ObjectNode node);
}
