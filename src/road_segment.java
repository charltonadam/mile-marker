import java.util.LinkedList;
import java.util.List;

/**
 * Created by charl on 1/15/2018.
 */
public class road_segment {

    int segment_number;

    List<GeoPoint> path;

    String attributes;

    double start;
    double end;

    public road_segment(int segment_number, String attributes, double start, double end) {
        this.segment_number = segment_number;
        this.attributes = attributes;
        this.start = start;
        this.end = end;

        path = new LinkedList<>();
    }


    public void addLocation(GeoPoint g) {
        path.add(g);
    }


    public int numberOfMileMarkers() {

        int i = (int) Math.ceil(start);
        int k = (int) end;

        return k - i + 1;
    }


    public List<MileMarker> getMileMarkers() {
        List<MileMarker> mileMarkers = new LinkedList<>();

        double currentPosition = start;
        double target = Math.ceil(start);
        int pathIndex = 1;
        int pathMaxIndex = path.size();

        while(pathIndex < pathMaxIndex && target <= end) {


            double distance = GeoPoint.distance(path.get(pathIndex - 1), path.get(pathIndex));
            if(currentPosition + distance >= target) {
                //we know that in between these two points there is a mile marker

                //first, get how far along in between the indexes the mile marker is
                double scale = target - currentPosition / distance;

                double latitude = (path.get(pathIndex).latitude - path.get(pathIndex - 1).latitude) / scale + path.get(pathIndex - 1).latitude;
                double longitude = (path.get(pathIndex).longitude - path.get(pathIndex - 1).longitude) / scale + path.get(pathIndex - 1).longitude;


                mileMarkers.add(new MileMarker(new GeoPoint(latitude, longitude), attributes, (int) target));
                target++;

            } else {
                //there is no mile marker, move up the index and try again
                pathIndex++;
                currentPosition += distance;
            }


            //we do not increment the pathIndex outside the else, as there might be multiple markers in between
            //a pair of indexes
        }



        return mileMarkers;
    }
}
