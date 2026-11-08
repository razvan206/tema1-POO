package domain;

public enum DataType {
    DAM_PRICE,
    DAM_PRICE_PREDICTION,
    GRID_LOAD,
    GRID_LOAD_PREDICTION,
    IMBALANCE_PRICE,
    IMBALANCE_VOLUME

    private final String description;

    private DataType(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }
}
