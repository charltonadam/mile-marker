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


    public int hasMileMarker() {

        int i = (int) Math.ceil(start);

        int numberOfMileMarkers = 0;

        while(i <= end) {
            numberOfMileMarkers++;
            i++;
        }

        return numberOfMileMarkers;
    }



}
