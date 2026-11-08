package domain;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ImbalanceVolumesPoint extends MarketPoint {
    Double positiveImbalanceVolume;
    Double negativeImbalanceVolume;

    public ImbalanceVolumesPoint(String date, String interval, int intervalIndex, Double positiveImbalanceVolume, Double negativeImbalanceVolume) {
        super(date, interval, intervalIndex);
        this.positiveImbalanceVolume = positiveImbalanceVolume;
        this.negativeImbalanceVolume = negativeImbalanceVolume;
    }

    @Override
    public String validate(Double prevQuantity) {
        if (this.positiveImbalanceVolume == null)
            return "PositiveImbalanceVolume is null; interval skipped.";
        if (this.positiveImbalanceVolume < 0)
            return "PositiveImbalanceVolume " + positiveImbalanceVolume + " is below the minimum allowed value 0; interval skipped.";
        if (this.negativeImbalanceVolume == null)
            return "NegativeImbalanceVolume is null; interval skipped.";
        if (this.negativeImbalanceVolume < 0)
            return "NegativeImbalanceVolume " + negativeImbalanceVolume + " is below the minimum allowed value 0; interval skipped.";
        return null;
    }

    @Override
    public void fillJsonPoint(ObjectNode node) {
        node.put("interval", this.interval);
        node.put("intervalIndex", this.intervalIndex);
        node.put("positiveImbalanceVolume", this.positiveImbalanceVolume);
        node.put("negativeImbalanceVolume", this.negativeImbalanceVolume);
    }
}