import java.util.LinkedList;
import java.util.List;

public class segmentBuilder {



    List<road_segment> roads;

    public segmentBuilder() {
        roads = new LinkedList<>();
    }

    public void add(road_segment r) {
        roads.add(r);
    }

    public void reduce() {
        if(roads.size() == 1) {
            //if there is only one roads, then we can assume that there is a mile marker associated with it
            return;
        } else if(roads.size() == 2) {
            //if this happens, something really weird is going on. probably, the two road segments
            //are so close together that it doesn't matter which one has the mile marker
            System.out.println("Something might have gone wrong, check with Adam");
        }

        //otherwise, there is some problems that we are going to have.  For the most part, there will only be 3.



        //do a traveling salesman style check every possible combination.  Unless there is like 20 road_segments, we are fine
        //we can assume that the first and last segments are correct

        double bestError = -1;
        int bestIteration = -1;



        for(int i = 0; i < Math.pow(2, roads.size() - 2) - 1; i++) {        //iterates through each combination
            String binary = Integer.toBinaryString(i);
            double currentError = 0;
            double currentRoadLength = roads.get(0).length;



            for(int k = 1; k < roads.size(); k++) {     //determines which roads to use in a combination

                if(binary.charAt(k - 1) == '1') {
                    //first, calculate the current error

                    double error = Math.abs(currentRoadLength - 1);
                    error *= 10;
                    currentError += Math.pow(error, 2);

                    //TODO: if implementation is too slow, add current error checking to cut calculation short



                    currentRoadLength = 0;

                } else {
                    //road is not included, do nothing?


                }
                //add the current length to the current distance
                currentRoadLength += roads.get(k).length;

            }

            //segment is calculated, compare it against the best so far
            if(bestIteration == -1) {
                //we have not found a best so far, update it
                bestError = currentError;
                bestIteration = i;

            } else if(currentError < bestError) {       //this is a better segment, use this
                bestError = currentError;
                bestIteration = i;
            }
            //if we are not better, throw away any work

        }

        //once we have the correct solution, replace the list with the updated list

        List<road_segment> updatedRoads = new LinkedList<>();
        updatedRoads.add(roads.get(0));

        String binary = Integer.toBinaryString(bestIteration);

        for(int i = 1; i < roads.size(); i++) {

            if(binary.charAt(i - 1) == '1') {
                updatedRoads.add(roads.get(i));
            }

        }
        updatedRoads.add(roads.get(roads.size() - 1));
        roads = updatedRoads;




    }




    public String toString() {
        //for this toString, we just need to print out each road_segment we have

        String ret = "";

        for(road_segment r:roads) {
            ret += r.toString();
        }

        return ret;



    }






}
