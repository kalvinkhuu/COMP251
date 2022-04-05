import java.util.*;

public class Q2New {
    public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {
        int[] possibleSorting = topologicalSorting(graph);
        int numTransport = 0;
        for (int i = 0; i < possibleSorting.length -1; i++) {
            int currPlanet = location[possibleSorting[i]];
            int nextPlanet = location[possibleSorting[i+1]];
            if (currPlanet != nextPlanet){
                numTransport++;
            }
        }
        return numTransport;
    }

    private static int[] topologicalSorting(Hashtable<Integer, ArrayList<Integer>> graph){
        int numberOfNodes = graph.size();
        boolean[] visited = new boolean[numberOfNodes];
        int[] result = new int[numberOfNodes];
        int index = numberOfNodes - 1;

        for (int i = 0; i < numberOfNodes; i++) {
            if(!visited[i]){
                ArrayList<Integer> visitedNode = new ArrayList<>();
                DFS(i, visited, visitedNode, graph);
                for(Integer integer: visitedNode){
                    result[index] = integer;
                    index = index-1;
                }
            }
        }

        return result;

    }

    private static void DFS(int index, boolean[] visited, ArrayList<Integer> visitedNodes, Hashtable<Integer, ArrayList<Integer>> graph){
        visited[index] = true;

        ArrayList<Integer> edges = graph.get(index);
        for(Integer integer: edges){
            if (!visited[integer]){
                DFS(integer, visited, visitedNodes, graph);
            }
        }
        visitedNodes.add(index);
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
//        int[] location1 = {2,2,2,2,2};
//
//        int result1 = rings(graph1, location1);
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
