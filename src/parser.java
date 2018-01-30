import java.io.File;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by charl on 1/15/2018.
 */
public class parser {

    public static void main(String args[]) {

        String mid = "CO_70A.MID";
        String mif = "CO_70A.MIF";

        File midFile = new File(mid);
        File mifFile = new File(mif);

        Scanner midScanner;
        Scanner mifScanner;


        List<road_segment> roads = new LinkedList<>();
        int numberOfSegments = 0;

        try {
            midScanner = new Scanner(midFile);
            mifScanner = new Scanner(mifFile);

        } catch (Exception e) {
            System.out.println("Something went wrong with loading in the files.");
            return;
        }

        String mifInput = "";


        String midInput = "";


        try {

            while (true) {
                mifInput = mifScanner.next();

                while (!mifInput.equals("PLINE")) {
                    mifInput = mifScanner.next();
                }

                //we can now assume that we have the start of the segments in the .mif file
                mifScanner.next();
                int numberOfRoads = mifScanner.nextInt();


                midInput = midScanner.nextLine();


                String[] splits = midInput.split(",");

                road_segment r = new road_segment(numberOfSegments, midInput, Double.parseDouble(splits[5]), Double.parseDouble(splits[6]));


                for(int k = 0; k < numberOfRoads; k++) {
                    int numberOfPoints = mifScanner.nextInt();

                    for (int i = 0; i < numberOfPoints; i++) {
                        r.addLocation(new GeoPoint(mifScanner.nextDouble(), mifScanner.nextDouble()));
                    }

                }
                roads.add(r);

            }



        } catch(Exception ignored) {


        }
        File outputFile = new File("output.csv");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(outputFile);
        }catch(Exception ignored) {

        }












        //we have a lot of things to do now
        //first, we have to sort the list of road segments
        roads.sort(new road_segment_comparator());


        //now that we have sorted the list of roads,
        //break them into the segmentBuilder

        List<segmentBuilder> stitching = new LinkedList<>();

        //go through each iteration of the segments, add them to segments as needed
        stitching.add(new segmentBuilder());
        stitching.get(0).add(roads.get(0));
        double previousDistance = roads.get(0).length;

        for(road_segment r:roads) {

            if(previousDistance < .98) {
                //add it to the previous segment

                stitching.get(stitching.size() - 1).add(r);

            } else {
                //add it to a new segment

                segmentBuilder seg = new segmentBuilder();
                seg.add(r);
                stitching.add(seg);
            }
            previousDistance = r.length;
        }
        //go through each segmentBuilder, reduce it

        for(segmentBuilder seg: stitching) {

            seg.reduce();
            writer.println(seg.toString());

        }






        //option 2, don't use reduce() method

        List<road_segment> option2 = new LinkedList<>();

        



    }



    public static class road_segment_comparator implements Comparator {

        public int compare(Object o1, Object o2) {
            road_segment r1 = (road_segment) o1;
            road_segment r2 = (road_segment) o2;

            int retVal = (int) ( r1.start * 10 - r2.start * 10 );

            return retVal;
        }

    }


}

