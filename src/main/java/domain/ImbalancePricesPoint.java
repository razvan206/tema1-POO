package domain;

import com.fasterxml.jackson.databind.node.ObjectNode;

import static java.lang.Math.abs;

public class ImbalancePricesPoint extends MarketPoint {
    Double positiveImbalancePrice;
    Double negativeImbalancePrice;

    public ImbalancePricesPoint(String date, String interval, int intervalIndex, Double positiveImbalancePrice, Double negativeImbalancePrice) {
        super(date, interval, intervalIndex);
        this.positiveImbalancePrice = positiveImbalancePrice;
        this.negativeImbalancePrice = negativeImbalancePrice;
    }

    @Override
    public String validate(Double prevQuantity) {
        if (this.positiveImbalancePrice == null)
            return "PositiveImbalancePrice is null; interval skipped.";
        if(abs(this.positiveImbalancePrice) > 15000)
            return "PositiveImbalancePrice " + this.positiveImbalancePrice + " exceeds the maximum allowed value 15000 EUR/MWh; interval skipped.";
        if(this.negativeImbalancePrice == null)
            return "NegativeImbalancePrice is null; interval skipped.";
        if(abs(this.negativeImbalancePrice) > 15000)
            return "NegativeImbalancePrice " + this.negativeImbalancePrice + " exceeds the maximum allowed value 15000 EUR/MWh; interval skipped.";
        return null;
    }

    @Override
    public void fillJsonPoint(ObjectNode node) {
        node.put("interval", this.interval);
        node.put("intervalIndex", this.intervalIndex);
        node.put("positiveImbalancePrice", this.positiveImbalancePrice);
        node.put("negativeImbalancePrice", this.negativeImbalancePrice);
    }
}