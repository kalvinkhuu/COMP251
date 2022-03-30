import java.util.*;


public class Q2 {

    public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {

        int currPlanet = 0;
        int numberOfTransport = 0;
        int numberOfNode = graph.size();
        int[] inDegree = new int[numberOfNode];
        for (int i = 0; i < numberOfNode; i++) {
            for(Integer integer: graph.get(i)){
                inDegree[integer]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numberOfNode; i++) {
            if(inDegree[i] == 0){
                queue.add(i);
                currPlanet = location[i];
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
            // Sort the adjacent node
            int[] tempArray = sortNodePlanet(location, unsortedAdjNode);

            // Queue in order node
            if(currPlanet == 2){
                for (int i = 0; i < tempArray.length; i++) {
                    queue.add(tempArray[i]);
                }
            }
            else {
                for (int i = tempArray.length-1; i >=0 ; i--) {
                    queue.add(tempArray[i]);
                }
            }

        }
        return numberOfTransport;

    }

    // Sort the adjacent node by their planet
    private static int[] sortNodePlanet(int[] planet, ArrayList<Integer> arrayList){
        ArrayList<Integer[]> nodeWithPlanet = new ArrayList<>();
        int[] tempArray = new int[arrayList.size()];
        for(Integer integer: arrayList){
            Integer[] temp = new Integer[2];
            temp[0] = integer;
            temp[1] = planet[integer];
            nodeWithPlanet.add(temp);
        }
        for (int i = 0; i < nodeWithPlanet.size(); i++) {
            tempArray[i] = arrayList.get(i);
        }

        for (int i = 0; i < nodeWithPlanet.size()-1; i++) {
            for (int j = 0; j < nodeWithPlanet.size()-1-i; j++) {
                if (planet[j]> planet[i]){
                    int temp = tempArray[j];
                    tempArray[j] = tempArray[j+1];
                    tempArray[j+1] = temp;
                }
            }
        }

        return tempArray;
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

    }

}