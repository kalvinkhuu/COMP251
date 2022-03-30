import java.util.*;
import java.io.File;

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();

        int n = graph.getNbNodes();

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();


        //Iterative DFS
        stack.push(source);
        while(!stack.isEmpty()){
            Integer currNode = stack.pop();

            List<Edge> adjacent = new ArrayList<>();
            // In all the edges in the graph, I want the edges connected to the source
            for(Edge e : graph.getEdges()){
                if (e.nodes[0] == currNode || e.nodes[1] == currNode){
                    adjacent.add(e);
                }
            }
            if (!visited[currNode]){
                visited[currNode] = true;
                for(Edge e: adjacent){
                    if (e.nodes[0] == currNode){
                        stack.push(e.nodes[1]);
                    }
                    else if (e.nodes[1] == currNode){
                        stack.push(e.nodes[0]);
                    }
                    else {
                        System.out.println("Something is wrong");
                    }
                }
            }
        }

        // Should find a path from source to destination

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


    public static void main(String[] args){
        String file = args[0];
        File f = new File(file);
        WGraph g = new WGraph(file);
        System.out.println(fordfulkerson(g));
    }
}

