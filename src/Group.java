// LUCY GARDNER GMB18183
import java.util.ArrayList;
import java.util.List;

//Group class to store information on the group inputted by the user.
class Group {

    private String name;
    private String email;
    private int groupSize;
    private double height;
    private String wheelchair;
    private List<String> rideTypes = new ArrayList<>();

    Group(){
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmail() {
        return email;
    }

    boolean setEmail(String email) {
        if(validateEmail(email)){
            this.email = email;
            System.out.println("Thank you, your recommendations will be emailed to " + email);
            return true;
        }
        else{
            System.out.println("Sorry " + name + " " + email + " is not a valid email address.\nPlease ensure your email contains a '@': ");
            return false;
        }
    }

    private boolean validateEmail(String email){
        boolean validated = false;
        if(!email.contains("@")){
            return false;
        }
        else if (email.contains("@") && email.contains(".com")){
            validated = true;
        }
        return validated;
    }

    int getGroupSize() {
        return groupSize;
    }

    boolean setGroupSize(String groupSize){
        try {
            int groupSizeInt = Integer.parseInt(groupSize);
            if (groupSizeInt > 1000 || groupSizeInt <= 0) {
                System.out.println("\nInvalid group size!\nPlease enter only NUMBERS between 1 and 1000: ");
                return false;
            }
            this.groupSize = groupSizeInt;
            return true;
        }catch(NumberFormatException exception){
            System.out.println("** Please enter a NUMBER **");
            return false;
        }
    }

    double getHeight() {
        return height;
    }

    boolean setHeight(String height) {
        try{
            double heightDouble = Double.parseDouble(height);
            if(heightDouble >= 0.7 && heightDouble < 2.5) {
                this.height = heightDouble;
                return true;
            }
            else {
                System.out.println("Please re-enter a normal height between 0.7 and 2.5: ");
                return false;
            }
        }catch(NumberFormatException exception){
            System.out.println("** Please enter a NUMBER **");
            return false;
        }
    }

    String isWheelchair() {
        return wheelchair;
    }

    boolean setWheelchair(String wheelchair) {
        if(!wheelchair.equals("Y") && !wheelchair.equals("N") && !wheelchair.matches("[a-zA-Z\\s]+[.]?[a-zA-Z\\s]*$")){
            System.out.println("Please enter either letter Y or N: ");
            return false;
        }
        else{
            this.wheelchair = wheelchair;
            return true;
        }
    }

    List<String> getRideTypes() {
        return rideTypes;
    }

    void addRideType(String rideType) {
        rideTypes.add(rideType);
    }

    void clearRideTypes() {
        rideTypes.clear();
    }
}
