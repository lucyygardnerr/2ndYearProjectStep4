// LUCY GARDNER GMB18183
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class Menu{

    private Scanner scanner = new Scanner(System.in);
    private String eOrP;
    private Group group = new Group();
    private List<String> reasons = new ArrayList<>();
    private List<RideData.Ride> applicableRides = new ArrayList<>();
    private RideData.Ride ride = new RideData.Ride();
    private int option;
    private FileHandler fileHandler = new FileHandler();
    private GraphApp graph = new GraphApp();

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        System.out.println("Welcome to the Time Travellers Theme Park!");
        menu.start();
    }

    void start() throws IOException {

        printMenu();
        fileHandler.getRidesFromFile();
        applicableRides = fileHandler.getRides();

        while(true) {
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    option =1;
                    setRide();
                    break;
                case 2:
                    option =2;
                    name();
                    break;
                case 3:
                    option = 3;
                    mapOption();
                    break;
                case 4:
                    option =4;
                    quickestRoute();
                    break;
                case 5:
                    option =5;
                    System.out.println("Goodbye - thank you for visiting.");
                    System.exit(0);
            }
        }
    }

    private void printMenu(){
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
        System.out.println("Please enter the name of the ride you would like recommendations for from the rides below: ");
        for (String name: fileHandler.getRideNames()) {
            System.out.println(name);
        }
        System.out.println("\n** Please enter the name the exact way as shown to you i.e capital letters **\n");
        String rideName = scanner.nextLine();
        boolean exists = false;
        while(!exists){
            if(fileHandler.getRideNames().contains(rideName)){
                exists = true;
            }
            System.out.println("\nRide does not exist!\n** Please enter a ride name from the list shown: ");
            rideName = scanner.nextLine();
        }
        for (RideData.Ride rollerCoaster: fileHandler.rides) {
            if (rollerCoaster.getName().equals(rideName)) {
                ride = rollerCoaster;
            }
        }

        System.out.println("\nRide chosen is: " + ride.getName() + "\n");
        System.out.println("Details on " + ride.getName() + " Height Range: " + ride.heightRange + " Wheelchair:  " + ride.wheelchair + " GroupSize: " + ride.groupSize + " Group Range: " + ride.groupRange + " Theme: " + ride.theme + " Types: " + ride.types);
        name();

    }

    private void name() throws IOException {
        System.out.println("Please enter information on your group so we can generate your recommendations! \n\nPlease enter your first name: ");
        String name = scanner.next();
        while(!Pattern.matches("[a-zA-Z]+", name)) {
            System.out.println("\nInvalid Name - ** Please re-enter only use upper or lower case letters **");
            name =  scanner.next();
        }
        group.setName(name);
        email();
    }

    private void email() throws IOException {
        System.out.println("\nHi " + group.getName() + "\nWould you prefer me to only print your recommendations on the screen or would you also like a copy emailed to you? \n** Please enter capital E for email or P for print** ");
        eOrP = scanner.next();
        if(!eOrP.equals("E") && !eOrP.equals("P")){
            System.out.println("Invalid - please enter either E or P: ");
            eOrP = scanner.next();
        }
        if(eOrP.equals("E")){
            System.out.println("You have chosen email - please enter a valid email address: ");
            boolean validated = group.setEmail(scanner.next());
            while(!validated) {
                validated = group.setEmail(scanner.next());
            }
            setGroupSize();
        }
        else if(eOrP.equals("P")) {
            System.out.println("You have chosen to only print your recommendations.");
            setGroupSize();
        }
    }

    private void setGroupSize() throws IOException {
        System.out.println("\nHow many people are in your group? ");

        /*
        String inputString = Integer.toString(input);
        if(Pattern.matches("[a-zA-Z]+", inputString)){
            System.out.println("\nPlease re-enter using only numbers: ");
            input = scanner.nextInt();
        }*/

        int input = scanner.nextInt();
        if(input > 1000){
            System.out.println("\nSorry we have a group limit of 100 here at the Theme Park. Please enter a smaller group number: ");
            input = scanner.nextInt();
        }
        group.setGroupSize(input);
        setHeight();
    }

    private void setHeight() throws IOException {
        System.out.println("\nPlease use the height chart next to this terminal to measure the height of the SMALLEST group in your group. \n** Please enter height in meters? e.g 1.2 **");
        boolean validated = group.setHeight(scanner.nextDouble());
        while(!validated){
            validated = group.setHeight(scanner.nextDouble());
        }
        setWheelchair();
    }

    private void setWheelchair() throws IOException {
        System.out.println("\nIs anyone in your group a wheelchair user? \n** Please enter capital Y for yes and N for no **");
        boolean validated = group.setWheelchair(scanner.next());
        while(!validated){
            validated = group.setWheelchair(scanner.next());
        }
        getRides();
    }


    private void getRides() throws IOException {

        String input;
        List<String> types = new ArrayList<>();
        types.add("Kids");
        types.add("Water");
        types.add("Horror");
        types.add("Adrenaline");

        System.out.println("\nWhich of the following ride types does your group like? ");
        System.out.println("** Please enter capital Y for yes and N for no **");
        for (int i=0; i< types.size(); i++){
            System.out.println(types.get(i) + ": ");
            input = scanner.next();

            if(!input.equals("Y") && !input.equals("N")){
                System.out.println("Please enter either capital letter Y or N! ");
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
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes = new LinkedBinaryTree.Node<>(this::addwheelchair, null, null);
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
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairYes = new LinkedBinaryTree.Node<>(this::addwheelchair, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> checkWheelchairNode = new LinkedBinaryTree.Node<>(this::checkWheelchair, wheelchairYes, wheelchairNo);
        LinkedBinaryTree.Node<Supplier<Boolean>> wheelchairRoot = new LinkedBinaryTree.Node<>(this::wheelchairExists, checkWheelchairNode, null);

        LinkedBinaryTree.Node<Supplier<Boolean>> typesNo = new LinkedBinaryTree.Node<>(this::addTypes, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesYes = new LinkedBinaryTree.Node<>(null, null, null);
        LinkedBinaryTree.Node<Supplier<Boolean>> typesRoot = new LinkedBinaryTree.Node<>(this::checkTypes, typesYes, typesNo);

        List<RideData.Ride> rides = fileHandler.getRides();

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
        if (node == null || node.getElement() == null) {
            return;
        }
        if (node.getElement().get()) {
            preOrder(node.getLeft());
        } else {
            preOrder(node.getRight());
        }
    }

    private boolean checkTypes(){
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

    private boolean addwheelchair(){
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
        System.out.println("Applicable Rides: ");
        for (RideData.Ride ride : applicableRides){
            System.out.println(ride.getName() + "\n");
        }
        System.out.println("\nMedieval Zone: ");
        boolean medieval = false;
        for (RideData.Ride ride: applicableRides){
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
        for (RideData.Ride ride: applicableRides){
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
        for (RideData.Ride ride: applicableRides){
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
        for (RideData.Ride ride: applicableRides){
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

        graph.dijkstra(graph.setUpGraph());

        graphEndOptions();
    }

    private void quickestRoute() throws IOException {

        graph.primMST(graph.setUpShortestGraph());

        graphEndOptions();
    }

    private void graphEndOptions() throws IOException {
        System.out.println("\nDo you want to choose another option or quit? \n** Please enter M to display Menu or Q to quit ** ");
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
