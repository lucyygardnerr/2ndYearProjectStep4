// LUCY GARDNER GMB18183
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphApp {

    private FileHandler fileHandler = new FileHandler();
    private List<RideData.Ride> rides;
    private static final int rideCount = 21;
    private List<Integer> distance = new ArrayList<>();
    private List<Integer> waitingTime = new ArrayList<>();

    private void assignDistances(){
        distance.add(500);
        distance.add(1000);
    }

    private void assignWaitingTime(){
        waitingTime.add(0);
        waitingTime.add(1);
        waitingTime.add(2);
        waitingTime.add(3);
        waitingTime.add(4);
        waitingTime.add(5);
        waitingTime.add(6);
        waitingTime.add(7);
        waitingTime.add(8);
        waitingTime.add(9);
        waitingTime.add(10);
        waitingTime.add(11);
        waitingTime.add(12);
        waitingTime.add(13);
        waitingTime.add(14);
        waitingTime.add(15);
    }

    private void getRideInfo() throws IOException {
        fileHandler.getRidesFromFile();
        rides = fileHandler.getRides();

        RideData.Ride entrance = new RideData.Ride();
        entrance.name = "entrance";
        rides.add(0, entrance);
    }

    int[][] setUpGraph() throws IOException {

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

        /*
        parkMap.addEdge(entrance,rideCount.get(0));
        parkMap.addEdge(entrance,rideCount.get(5));
        parkMap.addEdge(entrance,rideCount.get(10));
        parkMap.addEdge(entrance,rideCount.get(15));

        parkMap.addEdge(rideCount.get(0),rideCount.get(5));
        parkMap.addEdge(rideCount.get(0),rideCount.get(10));
        parkMap.addEdge(rideCount.get(0),rideCount.get(15));
        parkMap.addEdge(rideCount.get(0),rideCount.get(4));
        parkMap.addEdge(rideCount.get(0),rideCount.get(3));
        parkMap.addEdge(rideCount.get(0),rideCount.get(2));
        parkMap.addEdge(rideCount.get(0),rideCount.get(1));

        parkMap.addEdge(rideCount.get(5),rideCount.get(10));
        parkMap.addEdge(rideCount.get(5),rideCount.get(15));
        parkMap.addEdge(rideCount.get(5),rideCount.get(6));
        parkMap.addEdge(rideCount.get(5),rideCount.get(7));
        parkMap.addEdge(rideCount.get(5),rideCount.get(8));
        parkMap.addEdge(rideCount.get(5),rideCount.get(9));

        parkMap.addEdge(rideCount.get(10),rideCount.get(15));
        parkMap.addEdge(rideCount.get(10),rideCount.get(11));
        parkMap.addEdge(rideCount.get(10),rideCount.get(12));
        parkMap.addEdge(rideCount.get(10),rideCount.get(13));
        parkMap.addEdge(rideCount.get(10),rideCount.get(14));

        parkMap.addEdge(rideCount.get(15),rideCount.get(16));
        parkMap.addEdge(rideCount.get(15),rideCount.get(17));
        parkMap.addEdge(rideCount.get(15),rideCount.get(18));
        parkMap.addEdge(rideCount.get(15),rideCount.get(19));

        parkMap.printEdges();
        */
    }

    int[][] setUpShortestGraph() throws IOException{

        getRideInfo();
        assignDistances();
        assignWaitingTime();

        // Metric used is distance between nodes / 3 * waiting time
        //If waiting time not applicable i.e at the entrance, just the distance is used

        int graph[][] = new int[][]{{0, 700, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 1400, 0, 0, 0, 0, 700, 0, 0, 0, 0},
                {700, 0, distance.get(0) / (3 * waitingTime.get(10)), distance.get(0) / (3 * waitingTime.get(10)), distance.get(0) / (3 * waitingTime.get(10)), distance.get(0) / (3 * waitingTime.get(10)), distance.get(1) / (3 * waitingTime.get(10)), 0, 0, 0, 0, distance.get(1) / (3 * waitingTime.get(10)), 0, 0, 0, 0, distance.get(1) / (3 * waitingTime.get(10)), 0, 0, 0, 0},
                {0, distance.get(0) / (3 * waitingTime.get(3)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, distance.get(0) / (3 * waitingTime.get(5)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, distance.get(0) / (3 * waitingTime.get(7)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1400, distance.get(1) / (3 * waitingTime.get(15)), 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(15)),  distance.get(0) / (3 * waitingTime.get(15)),  distance.get(0) / (3 * waitingTime.get(15)),  distance.get(0) / (3 * waitingTime.get(15)),  distance.get(1) / (3 * waitingTime.get(15)), 0, 0, 0, 0,  distance.get(1) / (3 * waitingTime.get(15)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(4)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(8)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(11)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1400, distance.get(1) / (3 * waitingTime.get(5)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(12)), distance.get(0) / (3 * waitingTime.get(2)), distance.get(0) / (3 * waitingTime.get(13)), 500, distance.get(1) / (3 * waitingTime.get(5)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(12)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(2)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(13)), 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {700,distance.get(1) / (3 * waitingTime.get(14)),0,0,0,0,distance.get(1) / (3 * waitingTime.get(14)),0,0,0,0,distance.get(1) / (3 * waitingTime.get(14)),0,0,0,0,0, distance.get(0) / (3 * waitingTime.get(14)),distance.get(0) / (3 * waitingTime.get(14)),distance.get(0) / (3 * waitingTime.get(14)),distance.get(0) / (3 * waitingTime.get(14))},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(1)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(6)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, distance.get(0) / (3 * waitingTime.get(9)), 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0}};

        return graph;
    }

    public static void main(String[] args) throws IOException {
        GraphApp graphapp = new GraphApp();
        int[][] graph = graphapp.setUpShortestGraph();
        graphapp.primMST(graph);
    }

    private int minDistance(int dist[], Boolean sptSet[]){
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
        System.out.println("Ride                  Distance from entrance");
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
        int dist[] = new int[rideCount];
        Boolean sptSet[] = new Boolean[rideCount];

        for(int i = 0; i< rideCount; i++){
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[0] = 0;

        for(int count = 0; count< rideCount -1; count++){
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for(int v = 0; v< rideCount; v++){
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]){
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    //TODO - MINIMUM SPANNING TREE CODE

    private int minKey(int key[], Boolean mstSet[]){
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
        System.out.println("Ride                         Weight                     Distance From Previous Ride                  Waiting Time at Ride");
        System.out.println("\nMedieval Zone: ");
        for(int i =1; i < rideCount; i ++){
            if(rides.get(i).getTheme().equals("Medieval")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), graph[i][parent[i]]));
                //System.out.println(String.format("%-30s %-5d %-5d %-5d", rides.get(i).getName(), graph[i][parent[i]], distance.get(i), waitingTime.get(i)));
            }
        }

        System.out.println("\nFuturistic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Futuristic")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), graph[i][parent[i]]));
            }
        }

        System.out.println("\nJurassic Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Jurassic")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), graph[i][parent[i]]));
            }
        }

        System.out.println("\nIndustrial Zone: ");
        for (int i=1; i< rideCount; i++){
            if(rides.get(i).getTheme().equals("Industrial")){
                System.out.println(String.format("%-30s %-5d", rides.get(i).getName(), graph[i][parent[i]]));
            }
        }
        totalWaitingTimeMST(parent, graph);
    }

    private void totalWaitingTimeMST(int parent[], int graph[][]){
        int totalWaitingTime =0;
        for (Integer time : waitingTime) {
            totalWaitingTime += time;
        }
        System.out.println("\nTotal waiting time of the entire park is " + totalWaitingTime / 60 + " hours");
    }

    void primMST(int graph[][]){
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
            int u = minKey(key, mstSet);
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
