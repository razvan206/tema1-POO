package domain;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DamPricePoint extends MarketPoint {
    Double priceAmount;

    public DamPricePoint(String date, String interval, int intervalIndex, Double priceAmount){
        super(date,interval,intervalIndex);
        this.priceAmount = priceAmount;
    }

    @Override
    public String validate(Double prevQuantity) {
        if(priceAmount == null)
            return "PriceAmount is null; interval skipped.";
        if(priceAmount < -500 || priceAmount > 4000)
            return "PriceAmount " + priceAmount + " is outside the market range [-500, 4000] EUR/MWh; interval skipped.";
        return null;
    }

    @Override
    public void fillJsonPoint(ObjectNode node) {
        node.put("interval", this.interval);
        node.put("intervalIndex", this.intervalIndex);
        node.put("priceAmount", this.priceAmount);
    }

}
