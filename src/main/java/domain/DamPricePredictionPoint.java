package domain;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DamPricePredictionPoint extends MarketPoint{
    Double predictedPriceEurMhw;
    Double spikeProbability;

    public DamPricePredictionPoint(String date, String interval, int intervalIndex, Double predictedPriceEurMhw, Double spikeProbability){
        super(date, interval, intervalIndex);
        this.predictedPriceEurMhw = predictedPriceEurMhw;
        this.spikeProbability = spikeProbability;
    }

    @Override
    public String validate(Double prevQuantity) {
        if(this.predictedPriceEurMhw == null)
            return "PredictedPriceEurMwh is null; interval skipped.";
        if(this.predictedPriceEurMhw < -500 || this.predictedPriceEurMhw > 4000)
            return "PredictedPriceEurMwh " + predictedPriceEurMhw + " is outside the market range [-500,4000] EUR/Mwh; interval skipped.";
        if(this.spikeProbability == null)
            return "SpikeProbability is null; interval skipped.";
        if(this.spikeProbability < 0.0 || this.spikeProbability > 1.0)
            return "SpikeProbability " + this.spikeProbability + " is outside valid range [0.0,1.0]; interval skipped.";
        return null;
    }

    @Override
    public void fillJsonPoint(ObjectNode node) {
        node.put("interval",this.interval);
        node.put("intervalIndex",this.intervalIndex);
        node.put("predictedPriceEurMhw",this.predictedPriceEurMhw);
        node.put("spikeProbability",this.spikeProbability);
    }
}
