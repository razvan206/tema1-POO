import util.TimeConversionUtils;

public class MainTest {
    public static void main(String [] args){
        TimeConversionUtils util = new TimeConversionUtils();
        System.out.println(util.intervalToIndex("00:30 - 00:45"));
        System.out.println(util.indexToInterval(3));
        System.out.println(util.timestampToInterval("2025-10-15T00:00"));
    }
}
