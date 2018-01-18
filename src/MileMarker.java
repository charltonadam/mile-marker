public class MileMarker {

    GeoPoint location;
    String attributes;
    int mileNumber;

    public MileMarker(GeoPoint p, String s, int m) {
        location = p;
        attributes = s;
        mileNumber = m;
    }

    public String toString() {
        return location.toString() + " Mile:" + mileNumber + " " + attributes;
    }


}
