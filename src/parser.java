import java.io.File;
import java.io.PrintWriter;
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



        } catch(Exception e) {


        }
        File outputFile = new File("output.txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(outputFile);
        }catch(Exception e) {

        }


        for(road_segment r:roads) {
            for(MileMarker m:r.getMileMarkers()) {
                writer.println(m.toString());
            }
        }





    }
}
