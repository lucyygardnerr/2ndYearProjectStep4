// LUCY GARDNER GMB18183
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class Step4Test {

    @Test
    public void testSetName() {
        System.out.println("\nTesting Name Methods:");
        //Also tests getName method
        Group group = new Group();
        group.setName("Lucy");
        System.out.println("Name should be Lucy: " + group.getName());
        group.setName("Gardner");
        System.out.println("Name should be Gardner: " + group.getName());
    }

    @Test
    public void testSetEmail() {
        System.out.println("\nTesting Email Methods:");
        // Also tests validateEmail method
        Group group = new Group();
        System.out.println("Correct case: ");
        group.setEmail("lucy@email.com");
        System.out.println("Incorrect case: ");
        System.out.println(("** name is null as it has ut been assigned yet **"));
        group.setEmail("lucy-email.com");
    }

    @Test
    public void testSetGroupSize() {
        System.out.println("\nTesting Group Size Methods:");
        //Also tests getGroupSize method
        Group group = new Group();
        group.setGroupSize(2);
        System.out.println("Group size 2: " + group.getGroupSize());
        System.out.println("Group size 1001: " + group.getGroupSize());
        group.setGroupSize(1001);
    }

    @Test
    public void testSetHeight() {
        System.out.println("\nTesting Height Methods:");
        //Also tests getHeight method
        Group group = new Group();
        System.out.println("Within rage of 1 - 2.5: ");
        group.setHeight(1.5);
        System.out.println("Group's height: " + group.getHeight());
        System.out.println("Outwith range (2.7): ");
        group.setHeight(2.7);
    }

    @Test
    public void testSetWheelchair() {
        System.out.println("\nTesting Wheelchair Methods:");
        //Also tests isWheelchair method
        Group group = new Group();
        group.setWheelchair("Y");
        System.out.println("Wheelchair set to Y: " + group.isWheelchair());
        group.setWheelchair("N");
        System.out.println("Wheelchair set to N: " + group.isWheelchair());
    }

    @Test
    public void testSetTypes(){
        System.out.println("\nTesting Ride Type Methods:");
        // Also tests getTypes & clearRideTypes methods
        Group group = new Group();
        System.out.println("Ride Types should contain Kids & Water:");
        group.addRideType("Kids");
        group.addRideType("Water");
        System.out.println(group.getRideTypes());
        System.out.println("Clearing ride types: ");
        group.clearRideTypes();
        System.out.println(group.getRideTypes());
    }

    @Test
    public void testReadFromFile() throws IOException {
        System.out.println("\nTesting File Handler reading rides from file");
        // Also tests getRides & getRideNames method
        FileHandler fileHandler = new FileHandler();
        System.out.println("Call to get rides before reading from file: ");
        System.out.println(fileHandler.getRides());
        System.out.println("Reads from file...");
        fileHandler.getRidesFromFile();
        System.out.println("Call to get rides: ");
        System.out.println(fileHandler.getRides());
        System.out.println("Call to get ride names method: ");
        System.out.println(fileHandler.getRideNames());
    }

    @Test
    public void testGraph() throws IOException {
        System.out.println("\nTesting Graph is created and traversed correctly");
        GraphApp graphApp = new GraphApp();
        int [][] graph = graphApp.setUpGraph();
        System.out.println("Call to set up graph method...");
        System.out.println("Print result of set up graph method: ");
        System.out.println(Arrays.deepToString(graph));
        System.out.println("\nCall to Dijkstra's method...");
        System.out.println("Print result of Dijkstra's method:");
        graphApp.dijkstra(graph);
    }


}
