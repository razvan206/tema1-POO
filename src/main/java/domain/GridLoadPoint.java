package domain;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class GridLoadPoint extends MarketPoint {
    Double quantity;
    Double reservesRequiredMW;

    public GridLoadPoint(String date, String interval, int intervalIndex, Double quantity, Double reservesRequiredMW) {
        super(date, interval, intervalIndex);
        this.quantity = quantity;
        this.reservesRequiredMW = reservesRequiredMW;
    }

    @Override
    public String validate(Double prevQuantity) {
        if (this.quantity == null)
            return "Quantity is null; interval skipped.";
        if (this.quantity < 0)
            return "Quantity " + quantity + " is below the minimum allowed value 0; interval skipped.";
        if (this.reservesRequiredMW == null)
            return "ReservesRequiredMW is null; interval skipped.";
        if (this.reservesRequiredMW <= 0)
            return "ReservesRequiredMW " + reservesRequiredMW + " is not positive; interval skipped.";
        if (prevQuantity != null && Math.abs(this.quantity - prevQuantity) > 10000.0)
            return "Quantity change exceeds 10000 MW compared with the previous interval; interval skipped.";
        return null;
    }

    @Override
    public void fillJsonPoint(ObjectNode node) {
        node.put("interval", this.interval);
        node.put("intervalIndex", this.intervalIndex);
        node.put("quantity", this.quantity);
    }
}