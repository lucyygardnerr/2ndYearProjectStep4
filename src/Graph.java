// LUCY GARDNER GMB18183
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Graph class which creates and manipulates the graph
public class Graph {

    private FileHandler fileHandler = new FileHandler();
    private List<Ride> rides;
    private static final int rideCount = 21;
    private List<Integer> distance = new ArrayList<>();
    private List<Integer> waitingTime = new ArrayList<>();

    private void assignDistances(){
        //Medieval Zone
        distance.add(700);
        distance.add(500);
        distance.add(500);
        distance.add(500);
        distance.add(500);

        //Futuristic Zone
        distance.add(1000);
        distance.add(500);
        distance.add(500);
        distance.add(500);
        distance.add(500);

        //Jurassic Zone
        distance.add(1000);
        distance.add(500);
        distance.add(500);
        distance.add(500);
        distance.add(500);

        //Industrial Zone
        distance.add(1000);
        distance.add(500);
        distance.add(500);
        distance.add(500);
        distance.add(500);
    }


    private void assignWaitingTime(){
        //Medieval Zone
        waitingTime.add(10);
        waitingTime.add(7);
        waitingTime.add(5);
        waitingTime.add(0);
        waitingTime.add(3);

        //Futuristic Zone
        waitingTime.add(15);
        waitingTime.add(4);
        waitingTime.add(8);
        waitingTime.add(11);
        waitingTime.add(0);

        //Jurassic Zone
        waitingTime.add(5);
        waitingTime.add(12);
        waitingTime.add(2);
        waitingTime.add(13);
        waitingTime.add(0);

        //Industrial Zone
        waitingTime.add(14);
        waitingTime.add(1);
        waitingTime.add(6);
        waitingTime.add(9);
        waitingTime.add(0);
    }

    private void getRideInfo() throws IOException {
        fileHandler.getRidesFromFile();
        rides = fileHandler.getRides();

        Ride entrance = new Ride();
        entrance.name = "entrance";
        rides.add(0, entrance);
    }

    int[][] setUpGraph() throws IOException {
        /*This method receives the rides in from the fileHandler class
          and creates a graph to match the order of the list of rides with
          the weights of the edges being the distances between 2 rides
        */

        getRideInfo();

        int graph[][] = new int[][]{{0, 700, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 700, 0, 0, 0, 0},
                {700, 0, 500, 500, 500, 500, 1000, 0, 0, 0, 0, 1000, 0, 0, 0, 0, 1000, 0, 0, 0, 0},
                {0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1400, 1000, 0, 0, 0, 0, 0, 500, 500, 500, 500, 1000, 0, 0, 0, 0, 1000, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1400, 1000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 500, 500, 500, 1000, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {700,1000,0,0,0,0,1000,0,0,0,0,1000,0,0,0,0,0,500,500,500,500},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0}};

        return graph;
    }

    int[][] setUpShortestGraph() throws IOException{
        /*This method receives the rides in from the fileHandler class and creates a graph
          to match the order of the list of rides with the weight of the edges being a mixed
          sum of distance between rides and the waiting time at each ride
        */

        getRideInfo();
        assignDistances();
        assignWaitingTime();

        // Metric used is distance between nodes / 3 * waiting time
        //If waiting time not applicable i.e at the entrance (first ride in graph) - just the distance is used

        int graph[][] = new int[][]{
                {0, 700, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 700, 0, 0, 0, 0},
                {distance.get(0), 0, distance.get(1) / (3 * waitingTime.get(0)), distance.get(2) / (3 * waitingTime.get(0)), distance.get(3) / (3 * waitingTime.get(0)), distance.get(4) / (3 * waitingTime.get(0)), distance.get(5) / (3 * waitingTime.get(0)), 0, 0, 0, 0, 1000/ (3 * waitingTime.get(0)), 0, 0, 0, 0, 1000 / (3 * waitingTime.get(0)), 0, 0, 0, 0},
                {0, distance.get(1) / (3 * waitingTime.get(1)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, distance.get(2) / (3 * waitingTime.get(2)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, distance.get(3), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, distance.get(4) / (3 * waitingTime.get(4)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1400, distance.get(5) / (3 * waitingTime.get(5)), 0, 0, 0, 0, 0, distance.get(6) / (3 * waitingTime.get(5)),  distance.get(7) / (3 * waitingTime.get(5)),  distance.get(8) / (3 * waitingTime.get(5)),  distance.get(9) / (3 * waitingTime.get(5)),  distance.get(10) / (3 * waitingTime.get(5)), 0, 0, 0, 0,  1000 / (3 * waitingTime.get(5)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(6) / (3 * waitingTime.get(6)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(7) / (3 * waitingTime.get(7)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(8) / (3 * waitingTime.get(8)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(9), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {1400, 1000 / (3 * waitingTime.get(10)), 0, 0, 0, 0, distance.get(10) / (3* waitingTime.get(10)), 0, 0, 0, 0, 0, distance.get(11) / (3 * waitingTime.get(10)), distance.get(12) / (3 * waitingTime.get(10)), distance.get(13) / (3 * waitingTime.get(10)), distance.get(14) / (3 * waitingTime.get(10)), distance.get(15) / (3 * waitingTime.get(10)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(11) / (3 * waitingTime.get(11)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(12) / (3 * waitingTime.get(12)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(13) / (3 * waitingTime.get(13)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(14), 0, 0, 0, 0, 0, 0, 0, 0, 0},

                {distance.get(0), 1000 / (3 * waitingTime.get(15)),0,0,0,0, 1000 / (3 * waitingTime.get(15)),0,0,0,0, distance.get(15) / (3 * waitingTime.get(15)),0,0,0,0,0, distance.get(16) / (3 * waitingTime.get(15)), distance.get(17) / (3 * waitingTime.get(15)),distance.get(18) / (3 * waitingTime.get(15)),distance.get(19) / (3 * waitingTime.get(15))},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(16) / (3 * waitingTime.get(16)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(17) / (3 * waitingTime.get(17)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(18) / (3 * waitingTime.get(18)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(19), 0, 0, 0, 0}};

        return graph;
    }

    public static void main(String[] args) throws IOException {
        Graph graphapp = new Graph();
        int[][] graph = graphapp.setUpShortestGraph();
        graphapp.primMST(graph);
    }

    private int minWeight(int dist[], Boolean sptSet[]){
        // This method is used as part of Dijkstra's shortest path method to find the smallest weight
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int n = 0; n< rideCount; n++){
            if(!sptSet[n] && dist[n] <= min) {
                min = dist[n];
                minIndex = n;
            }
        }
        return minIndex;
    }

    private void printSolution(int[] dist){
        /* This method prints the solution to the Dijkstra method
           It does so by splitting the rides in to their sections of the theme park
           and printing the ride name alongside the distance from the entrance / exit of the theme park
         */
        System.out.println("Ride                  Distance from Entrance/Exit");
        System.out.println("\nMedieval Zone: ");
        for(int i =1; i < rideCount; i ++){
            if(rides.get(i).getTheme().equals("Medieval")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), dist[i]));
            }
        }

        System.out.println("\nFuturistic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Futuristic")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), dist[i]));
            }
        }

        System.out.println("\nJurassic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Jurassic")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), dist[i]));
            }
        }

        System.out.println("\nIndustrial Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Industrial")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), dist[i]));
            }
        }
    }

    void dijkstra(int[][] graph){
        /* This method is used to find the shortest path between 2 nodes
           In this case 1 of those nodes is always Ride 0 - the entrance / exit of the park
           with the other being each ride of the theme park
         */
        int dist[] = new int[rideCount];
        Boolean sptSet[] = new Boolean[rideCount];

        for(int i = 0; i< rideCount; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[0] = 0;

        for(int count = 0; count< rideCount -1; count++){
            int u = minWeight(dist, sptSet);
            sptSet[u] = true;
            for(int v = 0; v< rideCount; v++){
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]){
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        printSolution(dist);
    }

    //--------------------MINIMUM SPANNING TREE CODE-----------------------------

    private int minWeightST(int key[], Boolean mstSet[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int v=0; v<rideCount; v++){
            if(!mstSet[v] && key[v]<min){
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printMST(int parent[], int graph[][]){
        /* This method prints the solution to the primMST method
           It does so by splitting the rides in to their sections of the theme park
           and printing the ride name alongside the distance from the previous ride in the route of the theme park and the waiting time at each ride
         */
        System.out.println("Ride                          Weight               Distance From Previous Ride              Waiting Time at Ride");
        System.out.println("\nMedieval Zone: ");
        for(int i =1; i < rideCount; i ++){
            if(rides.get(i).getTheme().equals("Medieval")){
                System.out.println(String.format("%-30s %-30d %-40d %-35d", rides.get(i).getName(), graph[i][parent[i]], distance.get(i-1), rides.get(i).getWaitingTime()/*waitingTime.get(i-1)*/));
            }
        }
        System.out.println("\nFuturistic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Futuristic")){
                System.out.println(String.format("%-30s %-30d %-40d %-35d", rides.get(i).getName(), graph[i][parent[i]], distance.get(i-1), rides.get(i).getWaitingTime()/*waitingTime.get(i-1)*/));            }
        }
        System.out.println("\nJurassic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Jurassic")){
                System.out.println(String.format("%-30s %-30d %-40d %-35d", rides.get(i).getName(), graph[i][parent[i]], distance.get(i-1), rides.get(i).getWaitingTime()/*waitingTime.get(i-1)*/));            }
        }
        System.out.println("\nIndustrial Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Industrial")){
                System.out.println(String.format("%-30s %-30d %-40d %-35d", rides.get(i).getName(), graph[i][parent[i]], distance.get(i-1), rides.get(i).getWaitingTime()/*waitingTime.get(i-1)*/));            }
        }
        totalWaitingTime();
    }

    private void totalWaitingTime(){
        //This method calculates and prints the total waiting time of every ride in the park
        int totalWaitingTime =0;
        for (Integer time : waitingTime) {
            totalWaitingTime += time;
        }
        System.out.println("\nTotal waiting time of the entire park is " + totalWaitingTime / 60 + " hours");
    }

    void primMST(int graph[][]){
        /* This method generates a minimum spanning tree of the park which provides the quickest
           route around the park to visit every ride
         */
        int parent[] = new int[rideCount];
        int key[] = new int[rideCount];
        Boolean mstSet[] = new Boolean[rideCount];

        for( int i =0; i<rideCount;i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for(int count=0; count<rideCount-1; count++){
            int u = minWeightST(key, mstSet);
            mstSet[u] = true;

            for(int v = 0; v<rideCount;v++){
                if(graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]){
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printMST(parent, graph);
    }
}
