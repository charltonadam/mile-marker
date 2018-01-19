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
        return latitude + "," + longitude;
    }



    public static double distance(GeoPoint p1, GeoPoint p2) {

        double theta = p1.longitude - p2.longitude;
        double dist = Math.sin(Math.toRadians(p1.latitude)) * Math.sin(Math.toRadians(p2.latitude)) + Math.cos(Math.toRadians(p1.latitude)) * Math.cos(Math.toRadians(p2.latitude)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        return dist;
    }



    public static double distance2(GeoPoint p1, GeoPoint p2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(p2.latitude - p1.latitude);
        double lonDistance = Math.toRadians(p2.longitude - p1.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(p1.latitude)) * Math.cos(Math.toRadians(p2.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * .621371; // convert to miles

        return distance;
    }


}
