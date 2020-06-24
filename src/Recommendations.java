// LUCY GARDNER GMB18183
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;

/*Class to gather data on the group from the user and use that data to generate recommendations
 on whether 1 ride or all rides of the theme park are suitable or not for the group based
 on the group data and if they match the features of each ride */
public class Recommendations {

    private Scanner scanner = new Scanner(System.in);
    private String eOrP;
    private Group group = new Group();
    private List<String> reasons = new ArrayList<>();
    private List<Ride> applicableRides = new ArrayList<>();
    private Ride ride = new Ride();
    private int option;
    private FileHandler fileHandler = new FileHandler();
    private Graph graph = new Graph();

    public static void main(String[] args) throws IOException {
        Recommendations recommendations = new Recommendations();
        recommendations.start();
    }

    private void start() throws IOException {
        System.out.println("Welcome to the Time Travellers Theme Park!");
        printMenu();
        fileHandler.getRidesFromFile();
        applicableRides = fileHandler.getRides();

        String input = scanner.next();
        while(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")){
            System.out.println("Please enter either 1, 2, 3, 4 or 5: ");
            input = scanner.next();
        }
        switch (input) {
            case "1":
                option =1;
                setRide();
                break;
            case "2":
                option =2;
                name();
                break;
            case "3":
                option = 3;
                mapOption();
                break;
            case "4":
                option =4;
                quickestRoute();
                break;
            case "5":
                option =5;
                System.out.println("Goodbye - thank you for visiting.");
                System.exit(0);
        }
    }

    private void printMenu(){
        //Display menu options to the user
        System.out.println("\nPlease choose an option:");
        System.out.println();
        System.out.println("1. Get recommendations for 1 ride of your choice.");
        System.out.println("2. Get recommendations for the entire theme park");
        System.out.println("3. Display Map of the park");
        System.out.println("4. Display quickest Route around the park");
        System.out.println("5. Quit.");
        System.out.println();
    }

    private void setRide() throws IOException {
        /* Displays the rides of the theme park to the users & aks them to input which 1 ride they would like recommendations for
           - checks if the ride entered by the user is in fact a ride in the theme park
           -if so sets the ride data that the group's data will be compared to as the ride they have selected
         */
        scanner.useDelimiter("\\n");
        System.out.println("Rides at the theme park:\n");
        for (String name: fileHandler.getRideNames()) {
            System.out.println(name);
        }
        System.out.println("\n** Please enter the name of the ride you wish to get recommendations for **\n");
        String rideName = scanner.next().toUpperCase();
        while(!rideName.matches("[a-zA-Z\\s]+[.]?[a-zA-Z\\s]*$")) {
            System.out.println("\nInvalid Ride Name!\n** Please re-enter only use upper or lower case letters **");
            rideName =  scanner.next().toUpperCase();
        }
        while(!fileHandler.getRideNames().contains(rideName)){
            System.out.println("\nRide does not exist!\n** Please enter a ride name from the list shown: ");
            rideName = scanner.next().toUpperCase();
        }
        for (Ride rollerCoaster: fileHandler.rides) {
            if (rollerCoaster.getName().toUpperCase().equals(rideName.toUpperCase())) {
                ride = rollerCoaster;
            }
        }
        System.out.println("\nRide chosen is: " + ride.getName() + "\n");
        name();
    }

    private void name() throws IOException {
        // checks if what the user enters as their name only contains string characters and assigns to group name if so
        System.out.println("Please enter information on your group so we can generate your recommendations! \n\nPlease enter your first name: ");
        String name = scanner.next();
        while(!name.matches("[a-zA-Z\\s]+[.]?[a-zA-Z\\s]*$")) {
            System.out.println("\nInvalid Name - ** Please re-enter only use upper or lower case LETTERS **");
            name =  scanner.next();
        }
        group.setName(name);
        email();
    }

    private void email() throws IOException {
        /* checks if what the user enters matches the email or print options
         - if user chooses email, asks the user to enter their email address and validates if it contains a @
         - if user selects print moves on to ask for the size of te group
        */
        System.out.println("\nHi " + group.getName() + "\nWould you prefer to only print your recommendations on the screen or would you also like a copy emailed to you? \n** Please enter capital E for email or P for print** ");
        eOrP = scanner.next().toUpperCase();
        while(!eOrP.equals("E") && !eOrP.equals("P")){
            System.out.println("Invalid - please enter either E or P: ");
            eOrP = scanner.next().toUpperCase();
        }
        if(eOrP.equals("E")){
            System.out.println("You have chosen email - please enter a valid email address: ");
            boolean validated = group.setEmail(scanner.next());
            while(!validated) {
                validated = group.setEmail(scanner.next());
            }
            setGroupSize();
        } else if(eOrP.equals("P")) {
            System.out.println("You have chosen to only print your recommendations.");
            setGroupSize();
        }
    }

    private void setGroupSize() throws IOException {
        // checks if what the user enters as the size of their group is a number and is within the range of 1 - 1000 and assigns to group size if so
        System.out.println("\nHow many people are in your group? ");
        boolean validated = group.setGroupSize(scanner.next());
        while(!validated){
            validated = group.setGroupSize(scanner.next());
        }
        setHeight();
    }

    private void setHeight() throws IOException {
        // checks if what the user enters as the height of smallest member of their group is a number and is within the range of 0.7-2.5 and assigns to group height if so
        System.out.println("\nPlease use the height chart next to this terminal to measure the height of the SMALLEST group in your group. \n** Please enter height in meters? e.g 1.2 **");
        boolean validated = group.setHeight(scanner.next());
        while(!validated){
            validated = group.setHeight(scanner.next());
        }
        setWheelchair();
    }

    private void setWheelchair() throws IOException {
        // checks if what the user enters is either yes or no and assigns to group wheelchair if so
        System.out.println("\nIs anyone in your group a wheelchair user? \n** Please enter Y for yes and N for no **");
        boolean validated = group.setWheelchair(scanner.next().toUpperCase());
        while(!validated){
            validated = group.setWheelchair(scanner.next().toUpperCase());
        }
        getRides();
    }


    private void getRides() throws IOException {
        /* Displays the ride types to the user and allows the user to state which ones they want / don't want
           they do so by inputting Y for yes and N for no - the program checks tp ensure what the users enters is Y or N
           - if Y then that type is added to a list of types liked by the group
        */
        String input;
        List<String> types = new ArrayList<>();
        types.add("Kids");
        types.add("Water");
        types.add("Horror");
        types.add("Adrenaline");

        System.out.println("\nWhich of the following ride types does your group like? ");
        System.out.println("** Please enter Y for yes and N for no **");
        for (int i=0; i< types.size(); i++){
            System.out.println(types.get(i) + ": ");
            input = scanner.next();

            if(!input.equals("Y") && !input.equals("N")){
                System.out.println("Please enter either LETTER Y or N! ");
                i--;
            }
            if(input.equals("Y")){
                group.addRideType(types.get(i));
            }
        }

        System.out.println("\nGetting your recommendations... ");
        if(option == 1){
            getRecommendations();
        }
        else if( option == 2 || option ==4){
            getParkRecommendations();
        }
    }

    private void getRecommendations() throws IOException {
        /*Constructs 4 decision trees - each decision tree compares 1 factor of the group to the same factor of the ride to determine
          whether the group data is suitable to that of the ride i.e Group Size, Height, Wheelchair Use & Ride Types
          For the first 3 trees - firstly checks if the ride has a group size, height or wheelchair restriction
          - if it does, it goes on to compare the group data to the ride for each of these factors
          - if not it moves on to traversing the next tree.
          For the 4th tree on the ride Types they always exist for each ride, this node compares if the ride types
          liked by the user matches the ride types that this ride has.
        */

        System.out.println("\nRecommendations for " + group.getName() + "'s group created on " + setDateTime() + "->");

        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeNo = new LinkedBinaryTree.Node<>(this::addGroupSize, null,null);
        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkGroupSizeNode = new LinkedBinaryTree.Node<>(this::checkGroupSize, groupSizeYes, groupSizeNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeRoot = new LinkedBinaryTree.Node<>(this::groupSizeExists, checkGroupSizeNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> heightNo = new LinkedBinaryTree.Node<>(this::addHeight, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> heightYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkHeightNode = new LinkedBinaryTree.Node<>(this::checkHeight, heightYes, heightNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> heightRoot = new LinkedBinaryTree.Node<>(this::heightExists, checkHeightNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes = new LinkedBinaryTree.Node<>(this::addWheelchair, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkWheelchairNode = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairYes, wheelchairNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairRoot = new LinkedBinaryTree.Node<>(this::wheelchairExists, checkWheelchairNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> typesNo = new LinkedBinaryTree.Node<>(this::addTypes, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesRoot = new LinkedBinaryTree.Node<>(this::checkTypes, typesYes, typesNo);

        preOrder(groupSizeRoot);
        preOrder(heightRoot);
        preOrder(wheelchairRoot);
        preOrder(typesRoot);

        printEndOptions();
    }

    private void getParkRecommendations() throws IOException {
        /*Constructs 4 decision trees - each decision tree compares 1 factor of the group to the same factor of each ride in the full park
          to determine whether the group data is suitable to that of the ride i.e Group Size, Height, Wheelchair Use & Ride Types
          For the first 3 trees - firstly checks if the ride has a group size, height or wheelchair restriction
          - if it does, it goes on to compare the group data to the ride for each of these factors
          - if not it moves on to traversing the next tree.
          For the 4th tree on the ride Types they always exist for each ride, this node compares if the ride types
          liked by the user matches the ride types that this ride has.
        */

        System.out.println("\nRecommendations for " + group.getName() + "'s group created on " + setDateTime() + "->");

        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeNo = new LinkedBinaryTree.Node<>(this::addGroupSize, null,null);
        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkGroupSizeNode = new LinkedBinaryTree.Node<>(this::checkGroupSize, groupSizeYes, groupSizeNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> groupSizeRoot = new LinkedBinaryTree.Node<>(this::groupSizeExists, checkGroupSizeNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> heightNo = new LinkedBinaryTree.Node<>(this::addHeight, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> heightYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkHeightNode = new LinkedBinaryTree.Node<>(this::checkHeight, heightYes, heightNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> heightRoot = new LinkedBinaryTree.Node<>(this::heightExists, checkHeightNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairNo = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes = new LinkedBinaryTree.Node<>(this::addWheelchair, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkWheelchairNode = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairYes, wheelchairNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairRoot = new LinkedBinaryTree.Node<>(this::wheelchairExists, checkWheelchairNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> typesNo = new LinkedBinaryTree.Node<>(this::addTypes, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesRoot = new LinkedBinaryTree.Node<>(this::checkTypes, typesYes, typesNo);

        List<Ride> rides = fileHandler.getRides();

        ride = rides.get(0);

        for(int i = 0; i < fileHandler.ridesListSize(); i++){
            ride = rides.get(i);
            preOrder(groupSizeRoot);
            preOrder(heightRoot);
            preOrder(wheelchairRoot);
            preOrder(typesRoot);
        }

        if(option == 2){
            printEndOptionsFull();
        }

    }

    private void preOrder(LinkedBinaryTree.Node<Supplier<Boolean>> node) {
        /* used to traverse each tree, moving left / right depending on outcome of the method call at each node
          - if  methods called at the nodes return True the tree is traversed to the left, otherwise to the right
        */
        if (node == null || node.getElement() == null) {
            return;
        }
        if (node.getElement().get()) {
            preOrder(node.getLeft());
        } else {
            preOrder(node.getRight());
        }
    }

    //EXISTS methods check if the restriction is in place on the ride so whether to check against the group data or not
    //CHECK methods compare the group data against the ride data to check if they are compatible
    /*ADD methods
      - if user selected for 1 ride these methods add the appropriate factor to a list of reasons as to why the ride
        is not suitable for the group
      - if the user selected recommendations for the full park then it removes the ride from the list of applicable
        rides for te group - except for group size & types where this is done in the checking method.
     */

    private boolean checkTypes(){
        /* This method checks if any of the ride types that the ride has are not in the types liked by the group
           - if the ride has a type that is not liked by the group then that ride is removed from
             the list of rides suitable for the group
        */
        boolean types = true;

        for (String type : ride.getTypes()) {
            if(type.contains("Kids") && !group.getRideTypes().contains("Kids")){
                applicableRides.remove(ride);
                types = false;
            }
            if(type.contains("Adrenaline") && !group.getRideTypes().contains("Adrenaline")){
                applicableRides.remove(ride);
                types = false;
            }
            if(type.contains("Water") && !group.getRideTypes().contains("Water")){
                applicableRides.remove(ride);
                types = false;
            }
            if(type.contains("Horror") && !group.getRideTypes().contains("Horror")){
                applicableRides.remove(ride);
                types = false;
            }
        }
        return types;
    }


    private boolean addTypes(){
        reasons.add("- " + ride.getName() + " is in the " + ride.getTypes() + " category(s)");
        return false;
    }

    private boolean wheelchairExists(){
        return ride.getWheelchair().equals("N");
    }

    private boolean checkWheelchair(){ return group.isWheelchair().equals("Y");
    }

    private boolean addWheelchair(){
        reasons.add(ride.getName() + " is not suitable for wheelchair users.");
        applicableRides.remove(ride);
        return false;
    }

    private boolean heightExists(){
        return ride.getHeightRange() != null;
    }

    private boolean checkHeight(){
        return ride.heightRange.check(group.getHeight());
    }

    private boolean addHeight(){
        reasons.add("- Someone in your group is not a suitable height for " + ride.getName());
        applicableRides.remove(ride);
        return false;
    }

    private boolean groupSizeExists(){
        return ride.getGroupRange() != null || ride.getGroupSize() != 0;
    }

    private boolean checkGroupSize(){
        if(ride.getGroupRange() != null){
            if(!ride.groupRange.check(group.getGroupSize())){
                applicableRides.remove(ride);
                return false;
            }
        }
        if(ride.getGroupSize() == 0){
            return true;
        }
        if(group.getGroupSize() != ride.getGroupSize()) {
            applicableRides.remove(ride);
            return false;
        }
        else{
            return true;
        }
    }

    private boolean addGroupSize(){
        reasons.add("- " + ride.getName() + " is not suitable for the size of your group.");
        return false;
    }

    private void printEndOptionsFull() throws IOException {
        /* This method checks if there are any rides that are suitable for the group in each Themed section of the park
          - if there are tells the user which rides are suitable in each themed section
          - if not tells the user that no rides in that section are suitable for them
        */
        System.out.println("Applicable Rides: ");
        for (Ride ride : applicableRides){
            System.out.println(ride.getName() + "\n");
        }
        System.out.println("\nMedieval Zone: ");
        boolean medieval = false;
        for (Ride ride: applicableRides){
            if(ride.theme.equals("Medieval")){
                System.out.println("\n" + ride.name);
                medieval = true;
            }
        }
        if(!medieval){
            System.out.println("No medieval rideCount where suitable for your group.");
        }

        System.out.println("\nFuturistic Zone: ");
        boolean futuristic = false;
        for (Ride ride: applicableRides){
            if(ride.theme.equals("Futuristic")){
                System.out.println(ride.name);
                futuristic = true;
            }
        }
        if(!futuristic){
            System.out.println("No Futuristic rideCount where suitable for your group.");
        }

        System.out.println("\nJurassic Zone: ");
        boolean jurassic = false;
        for (Ride ride: applicableRides){
            if(ride.theme.equals("Jurassic")){
                System.out.println(ride.name);
                jurassic = true;
            }
        }
        if(!jurassic){
            System.out.println("No Jurassid rideCount where suitable for your group.");
        }

        System.out.println("\nIndustrial Zone: ");
        boolean industrial = false;
        for (Ride ride: applicableRides){
            if(ride.theme.equals("Industrial")){
                System.out.println(ride.name);
                industrial = true;
            }
        }
        if(!industrial){
            System.out.println("No Industrial rideCount where suitable for your group.");
        }

        lastOptions();
    }
    private void printEndOptions() throws IOException {
        /* This method checks if there are any reasons why the 1 ride is not suitable
          - if there are tells the user the ride is not suitable and prints the reasons why
            otherwise prints that the ride is suitable for the group.
        */
        if(reasons.size() != 0){
            System.out.println("\nBased on your inputs " + ride.getName() + " is not suitable for your party because: ");
            for (String reason : reasons) {
                System.out.println(reason);
            }
        }
        else{
            System.out.println(ride.getName() + " is suitable for your party - enjoy!");
        }

        lastOptions();
    }

    private void lastOptions() throws IOException {
        // This method asks the user whether they want to revise their data or continue to email / print
        System.out.println("\nDo you want to revise your choices or continue? \n** Please enter R to revise C to continue ** ");
        String input = scanner.next();
        switch (input){
            case "R":
                reasons.clear();
                group.clearRideTypes();
                email();
                break;
            case "C":
                if(eOrP.equals("P")){
                    System.out.println("Thank you " + group.getName() + " for visiting - we hope you enjoyed your time at Time Travellers Theme Park!");
                    System.exit(0);
                }
                else{
                    System.out.println("Thank you " + group.getName() + ", your recommendations have been emailed to " + group.getEmail() + ". We hope you enjoyed your time at Time Travellers Theme Park!");
                    System.exit(0);
                }
        }
    }

    private void mapOption() throws IOException {
        /* This method firstly constructs a graph and then calls the shortest path method on the graph
           Once the map of the park (graph) has been displayed to the user it then asks the user
           if they would like to see the menu options again or quit
         */
        graph.dijkstra(graph.setUpGraph());

        graphEndOptions();
    }

    private void quickestRoute() throws IOException {

        graph.primMST(graph.setUpShortestGraph());

        graphEndOptions();
    }

    private void graphEndOptions() throws IOException {
        // This method asks the user whether they want to see the menu options again or quit
        System.out.println("\nDo you want to choose another option or quit? \n** Please enter M to display Recommendations or Q to quit ** ");
        String input = scanner.next();
        if(!input.equals("M") && !input.equals("Q")){
            System.out.println("Invalid - please enter either M or Q: ");
            input = scanner.next();
        }
        switch (input){
            case "M":
                start();
                break;
            case "Q":
                System.out.println("Goodbye - Thank you for visiting Time Travellers Theme Park!.");
                System.exit(0);
        }
    }

    private String setDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy @ HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

}
