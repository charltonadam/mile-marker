/**
 * Created by charl on 1/15/2018.
 */
public class GeoPoint {

    double latitude;
    double longitude;

    public GeoPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String toString() {
        return latitude + " " + longitude;
    }



    public static double distance(GeoPoint p1, GeoPoint p2) {

        double theta = p1.longitude - p2.longitude;
        double dist = Math.sin(Math.toRadians(p1.latitude)) * Math.sin(Math.toRadians(p2.latitude)) + Math.cos(Math.toRadians(p1.latitude)) * Math.cos(Math.toRadians(p2.latitude)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        return dist;
    }


}
