import java.util.*;
import java.io.File;

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();

        int n = graph.getNbNodes();

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> empty = new ArrayList<>();

        //Iterative DFS
        stack.push(source);
//        Node currNode = new Node(empty, source, empty);
        while(!stack.isEmpty()){
            Integer currNodeID = stack.pop();
            path.add(currNodeID);
            if (currNodeID.equals(destination) ){
                return path;
            }
            List<Edge> adjacent = new ArrayList<>();
            // In all the edges in the graph, I want the edges connected to the source
            for(Edge e : graph.getEdges()){
                if (e.nodes[0] == currNodeID){
                    adjacent.add(e);
                }
            }
            if (!visited[currNodeID]){
                visited[currNodeID] = true;
                for(Edge e: adjacent){
                    // Pushing the adjacent nodeID into the stack
                    stack.push(e.nodes[1]);

                }
            }
        }

        // Return the prev path of the destination
        return path;
    }


    public static String fordfulkerson( WGraph graph){
        String answer="";
        int maxFlow = 0;

        // Should compute the max flow of the path

        /* YOUR CODE GOES HERE		*/

        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }

    public static class Node {
        ArrayList<Integer> prevPath;
        Integer nodeID;
        ArrayList<Integer> futurePath;

        public Node(){};

        public Node(ArrayList<Integer> prevPath, Integer nodeID, ArrayList<Integer> futurePath) {
            this.prevPath = prevPath;
            this.nodeID = nodeID;
            this.futurePath = futurePath;
        }
    }



    // Need to change the input
    private static ArrayList<Integer> deepCopy(ArrayList<Integer> path){
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < path.size(); i++) {
            arrayList.add(path.get(i));
        }

        return arrayList;
    }

    public static void main(String[] args){
        String file = args[0];
        File f = new File(file);
        WGraph g = new WGraph(file);
        System.out.println(fordfulkerson(g));
    }
}

