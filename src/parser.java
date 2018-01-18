import java.io.File;
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
                mifInput = mifScanner.nextLine();

                while (!mifInput.equals("PLINE MULTIPLE 1")) {
                    mifInput = mifScanner.nextLine();
                }


                midInput = midScanner.nextLine();


                String[] splits = midInput.split(",");

                road_segment r = new road_segment(numberOfSegments, midInput, Double.parseDouble(splits[5]), Double.parseDouble(splits[6]));


                int numberOfPoints = mifScanner.nextInt();

                for (int i = 0; i < numberOfPoints; i++) {
                    r.addLocation(new GeoPoint(mifScanner.nextDouble(), mifScanner.nextDouble()));
                }

                roads.add(r);
            }



        } catch(Exception e) {
            //this catch statement is not a problem, it simply means we have used up
            //all avaliable inputs.  Here, we simply end the program.

            System.out.println("Finish");

        }

    }
}
