import java.util.*;


public class Q2 {

    public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {

        /*
        * Find all planets with no dependency
        * Start by queuing the start planet
        * Find the numberOfTransport for that starting planet
        * Find the minimum of transport and return it
        * */


        ArrayList<Integer> planetsWithNoIn = new ArrayList<>();
        ArrayList<Integer> totalNumberOfTransport = new ArrayList<>();

//        int currPlanet = 0;
//        int numberOfTransport = 0;
        int numberOfNode = graph.size();
        int[] inDegree = new int[numberOfNode];
        for (int i = 0; i < numberOfNode; i++) {
            for(Integer integer: graph.get(i)){
                inDegree[integer]++;
            }
        }

        for (int i = 0; i < numberOfNode; i++) {
            if(inDegree[i] == 0){
                planetsWithNoIn.add(i);
            }
        }

        for (Integer startPlanet: planetsWithNoIn) {
            int currPlanet = location[startPlanet];
            totalNumberOfTransport.add(ringHelper(graph, location, inDegree, startPlanet,
                    numberOfNode, currPlanet));
        }

        int minimum = numberOfNode + 1;
        for (int numTransport: totalNumberOfTransport) {
            if (numTransport < minimum){
                minimum = numTransport;
            }
        }

        return minimum;
    }

    private static int ringHelper(Hashtable<Integer, ArrayList<Integer>> graph, int[] location, int[] inDegree,
                                  int startPlanet, int numberOfNode, int startLocation){
        int currPlanet = startLocation;
        int numberOfTransport = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startPlanet);
        for (int i = 0; i < numberOfNode; i++) {
            if (inDegree[i] == 0 & i != startPlanet){
                queue.add(i);
            }
        }

        int index = 0;
        int[] finalOrder = new int[numberOfNode];

        while (!queue.isEmpty()){
            int currNode = queue.remove();
            if (location[currNode] != currPlanet){
                numberOfTransport++;
                currPlanet = location[currNode];
            }
            finalOrder[index++] = currNode;
            ArrayList<Integer> unsortedAdjNode = new ArrayList<>();
            for(Integer integer: graph.get(currNode)){
                inDegree[integer]--;
                if (inDegree[integer] == 0){
                    unsortedAdjNode.add(integer);
                }
            }
            for (int i = 0; i < unsortedAdjNode.size(); i++) {
                if(location[unsortedAdjNode.get(i)] == currPlanet){
                    queue.add(unsortedAdjNode.get(i));
                    unsortedAdjNode.remove(i);
                }
            }
            for (Integer integer: unsortedAdjNode) {
                queue.add(integer);
            }

        }
        return numberOfTransport;
    }


    public static void main(String[] args) {

//        Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<Integer, ArrayList<Integer>>();
//        graph.put(4, new ArrayList<Integer>());
//        graph.put(3, new ArrayList<Integer>());
//        graph.put(2, new ArrayList<Integer>(Arrays.asList(3,4)));
//        graph.put(1, new ArrayList<Integer>(Arrays.asList(3,4)));
//        graph.put(0, new ArrayList<Integer>(Arrays.asList(1,2)));
//        int[] location = {1,2,1,2,1};
//
//        int result = rings(graph, location);
//        System.out.println(result);

        Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<Integer, ArrayList<Integer>>();
        graph.put(4, new ArrayList<Integer>());
        graph.put(3, new ArrayList<Integer>(Arrays.asList(4)));
        graph.put(2, new ArrayList<Integer>(Arrays.asList(4)));
        graph.put(1, new ArrayList<Integer>(Arrays.asList(4)));
        graph.put(0, new ArrayList<Integer>(Arrays.asList(4)));
        int[] location = {2,2,1,2,2};

        int result = rings(graph, location);
        System.out.println(result);

    }

}