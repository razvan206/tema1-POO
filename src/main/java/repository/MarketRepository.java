package repository;

import domain.DataType;
import domain.MarketDocument;
import domain.MarketPoint;

import javax.xml.crypto.Data;
import java.util.List;

public interface MarketRepository {
    public void savePoint(DataType type, String date, MarketPoint point);

    // save or override a point in repo
    // we use savePoint for import and patch because they do basically the same thing

    public Double getPreviousGridLoad(String date, int currentIndex);

    // used for validation in GRID document

    public MarketPoint getPoint(DataType type, String date, int intervalIndex);

    public MarketPoint getLatestPoint(DataType type);

    // for GET("LATEST") from DataType type document

    public MarketDocument getDocument(DataType type, String date);

    // in case we need a interval of data from a specific Document we load the entire MarketDocument

}
