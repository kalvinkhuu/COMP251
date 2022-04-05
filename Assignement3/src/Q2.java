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
        boolean twoDifferentPlanets = false;
        int planet = -1;
        int numberOfNode = graph.size();
        int[] inDegree = new int[numberOfNode];
        for (int i = 0; i < numberOfNode; i++) {
            for(Integer integer: graph.get(i)){
                inDegree[integer]++;
            }
        }

        for (int i = 0; i < numberOfNode; i++) {
            if(inDegree[i] == 0){
                if (planet == -1){
                    planet = location[i];
                    planetsWithNoIn.add(i);
                }
                if (location[i] != planet){
                    twoDifferentPlanets = true;
                    planetsWithNoIn.add(i);
                    break;
                }
            }
        }

        if (twoDifferentPlanets){
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
        else {
            return ringHelper(graph, location, inDegree, planetsWithNoIn.get(0), numberOfNode,
                    location[planetsWithNoIn.get(0)]);
        }


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
        System.out.println(numberOfTransport);
        return numberOfTransport;
    }


    public static void main(String[] args) {

        Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<Integer, ArrayList<Integer>>();
        graph.put(4, new ArrayList<Integer>());
        graph.put(3, new ArrayList<Integer>());
        graph.put(2, new ArrayList<Integer>(Arrays.asList(3,4)));
        graph.put(1, new ArrayList<Integer>(Arrays.asList(3,4)));
        graph.put(0, new ArrayList<Integer>(Arrays.asList(1,2)));
        int[] location = {1,2,1,2,1};

        int result = rings(graph, location);
        System.out.println(result);

//        Hashtable<Integer, ArrayList<Integer>> graph1 = new Hashtable<Integer, ArrayList<Integer>>();
//        graph1.put(4, new ArrayList<Integer>());
//        graph1.put(3, new ArrayList<Integer>(Arrays.asList(4)));
//        graph1.put(2, new ArrayList<Integer>(Arrays.asList(4)));
//        graph1.put(1, new ArrayList<Integer>(Arrays.asList(4)));
//        graph1.put(0, new ArrayList<Integer>(Arrays.asList(4)));
//        int[] location1 = {2,2,1,2,2};
//
//        int result1 = rings(graph1, location1);
//        System.out.println("\n");
//        System.out.println(result1);
//
//        Hashtable<Integer, ArrayList<Integer>> graph2 = new Hashtable<Integer, ArrayList<Integer>>();
//        graph2.put(6, new ArrayList<Integer>());
//        graph2.put(5, new ArrayList<Integer>(Arrays.asList(3,4)));
//        graph2.put(4, new ArrayList<Integer>(Arrays.asList(6)));
//        graph2.put(3, new ArrayList<Integer>(Arrays.asList(6)));
//        graph2.put(2, new ArrayList<Integer>(Arrays.asList(3)));
//        graph2.put(1, new ArrayList<Integer>(Arrays.asList(2,5)));
//        graph2.put(0, new ArrayList<Integer>(Arrays.asList(1,2)));
//        int[] location2 = {1,2,1,2,1,2,1};
//
//        int result2 = rings(graph2, location2);
//        System.out.println(result2);
//
//        Hashtable<Integer, ArrayList<Integer>> graph3 = new Hashtable<Integer, ArrayList<Integer>>();
//        graph3.put(6, new ArrayList<Integer>());
//        graph3.put(5, new ArrayList<Integer>(Arrays.asList(3,4)));
//        graph3.put(4, new ArrayList<Integer>(Arrays.asList(6)));
//        graph3.put(3, new ArrayList<Integer>(Arrays.asList(6)));
//        graph3.put(2, new ArrayList<Integer>(Arrays.asList(3)));
//        graph3.put(1, new ArrayList<Integer>(Arrays.asList(2,5)));
//        graph3.put(0, new ArrayList<Integer>(Arrays.asList(1,2)));
//        int[] location3 = {2,2,2,2,1,2,1};
//
//        int result3 = rings(graph3, location3);
//        System.out.println(result3);
//
//        Hashtable<Integer, ArrayList<Integer>> graph4 = new Hashtable<Integer, ArrayList<Integer>>();
//        graph4.put(0, new ArrayList<Integer>());
//        int[] location4 = {2};
//
//        int result4 = rings(graph4, location4);
//        System.out.println(result4);


    }

}