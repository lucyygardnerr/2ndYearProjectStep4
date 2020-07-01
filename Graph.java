// LUCY GARDNER GMB18183
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Graph class which creates and manipulates the graph
public class Graph {

    private FileHandler fileHandler = new FileHandler();
    private List<Ride> rides;
    private static final int rideCount = 21;

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

    public static void main(String[] args) throws IOException {
        Graph graphapp = new Graph();
        int[][] graph = graphapp.setUpGraph();
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

    private void printMST(List<Ride> sTRides, List<Integer> visitedRides, int graph[][], int parent[]){
        /* This method prints the solution to the primMST method
           It does so by splitting the rides in to their sections of the theme park
           and printing the ride name alongside the distance from the previous ride in the route of the theme park and the waiting time at each ride
         */
        System.out.println("Ride                            Theme                 Distance From Previous Ride              Waiting Time at Ride\n");
        for(int i =1; i < rideCount; i ++){
            System.out.println(String.format("%-30s %-35s %-35d %-40d", sTRides.get(i).getName(), sTRides.get(i).getTheme(), graph[parent[visitedRides.get(i)]][visitedRides.get(i)], sTRides.get(i).getWaitingTime()));
        }
        totalWaitingTime();
    }

    private void totalWaitingTime(){
        //This method calculates and prints the total waiting time of every ride in the park
        int totalWaitingTime =0;
        for (Ride ride : rides) {
            totalWaitingTime += ride.getWaitingTime();
        }
        System.out.println("\nTotal waiting time of the entire park is " + totalWaitingTime / 60 + " hours");
    }

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

    private int calculateWeight(int distance, int waitingTime){
        int weight = distance;
        if(waitingTime != 0){
            weight = weight / (3* waitingTime);
        }
        return weight;
    }

    void primMST(int graph[][]){
        /* This method generates a minimum spanning tree of the park which provides the quickest
           route around the park to visit every ride
         */
        List<Ride> sTRides = new ArrayList<>();
        List<Integer> visitedRides = new ArrayList<>();
        int key[] = new int[rideCount];
        int parent[] = new int[rideCount];
        Boolean mstSet[] = new Boolean[rideCount];

        for(int i =0; i<rideCount;i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        //weights and indexed by row in the graph
        key[0] = 0;
        //previously visited nodes
        parent[0] = -1;

        for(int count=0; count<rideCount; count++){
            int u = minWeightST(key, mstSet);
            mstSet[u] = true;
            visitedRides.add(u);
            sTRides.add(rides.get(u));

            for(int v = 0; v<rideCount;v++){
                if(graph[u][v] != 0 && !mstSet[v] && calculateWeight(graph[u][v], rides.get(v).getWaitingTime()) < key[v]){
                    //sTRides.get(u).setDistance(graph[u][v]);
                    parent[v] = u;
                    key[v] = calculateWeight(graph[u][v], rides.get(v).getWaitingTime());
                }
            }
        }
        /*for (Ride stRide: sTRides){
            System.out.println(stRide.getName());
        }
        */
        printMST(sTRides, visitedRides, graph, parent);
    }
}
