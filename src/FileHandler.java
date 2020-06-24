// LUCY GARDNER GMB18183
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/* This class reads in the information about every ride in the park from a file
   and assigns the data to a list of rides.
*/
class FileHandler {

    List<Ride> rides = new ArrayList<>();

    int ridesListSize(){
        return rides.size();
    }

    List<Ride> getRides(){
        return rides;
    }

    // returns a list of just the ride names for ease of use in other methods i.e printing the rides in the park
    List<String> getRideNames(){
        List<String> rideNames = new ArrayList<>();
        for (Ride ride: rides) {
            rideNames.add(ride.getName().toUpperCase());
        }
        return rideNames;
    }

    void getRidesFromFile() throws IOException {
        //check if file exists first
        String fileName = "Rides.txt";
        if(!Files.exists(Paths.get(fileName))) {
            System.out.println("File does not exist");
        }

        BufferedReader br = new BufferedReader(new FileReader("Rides.txt"));

        /* breaks down each line in the file using ','s to separate each factor
           i.e ride name, group size etc
           creates a ew ride ofr each line and assigns the ride data to the new ride
        */
        for (String line = br.readLine(); line != null; line = br.readLine()){
            String parts[] = line.split(", ");
            Ride ride = new Ride();
            rides.add(ride);
            ride.name = parts[0];
            checkHeight(ride, parts[1]);
            ride.wheelchair = parts[2];
            checkGroupSize(ride, parts[3]);
            ride.theme = parts[4];
            ride.waitingTime = Integer.parseInt(parts[5]);
            ride.types.addAll(Arrays.asList(parts).subList(6, parts.length));
        }
    }

    private void checkHeight(Ride ride, String height){
        /* This method firstly check if there is a height restriction in place for the ride
           - if not the method goes no further
           - if there is a height restriction the method then goes on to check:
             -if it is a range between 2 numbers
             - if it is got to be larger than a number
             -if it got to be smaller than a number
           - for each of the above cases it then goes on to appropriately set the height of the ride
         */
        if(height.equals("N")){
            return;
        }
        if( height.contains(">") && height.contains("<")){
            String[] ranges = height.split("<");
            String[] part1 = ranges[0].split(">");
            double lowRange = parseDouble(part1[1]);
            String[] part2 = ranges[1].split("<");
            double highRange = parseDouble(part2[0]);
            ride.heightRange = new Range(lowRange, highRange);
            return;
        }
        if( height.contains(">")){
            String[] ranges = height.split(">");
            ride.heightRange = new Range(parseDouble(ranges[1]), 2.5);
        }
        if( height.contains("<")){
            String[] ranges = height.split("<");
            ride.heightRange = new Range(0.7, parseDouble(ranges[1]));
        }
    }

    private void checkGroupSize(Ride ride, String groupSize){
        /* This method firstly check if there is a group size restriction in place for the ride
           - if not the method goes no further
           - if there is a group size restriction the method then goes on to check if it is a range between 2 numbers
               - if so it then goes on to appropriately set the group size of the ride as a range
           - if it is just 1 number it assigns it to the group size of the ride
         */
        if(groupSize.equals("N")){
            return;
        }
        if(groupSize.contains("-")){
            String[] ranges = groupSize.split("-");
            ride.groupRange = new Range(parseInt(ranges[0]), parseInt(ranges[1]));
        }
        else{
            ride.groupSize = parseInt(groupSize);
        }
    }
}
