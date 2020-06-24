// LUCY GARDNER GMB18183
//Range class to provide the ability to set the height or group size of a ride to a range between 2 numbers
public class Range {

    private double low;
    private double high;

    Range(double low, double high){
        this.low = low;
        this.high = high;
    }

    boolean check(double number){
        return (number >= low && number <= high);
    }

    public String toString(){
        return "The range is: " + low + " - " + high;
    }
}
