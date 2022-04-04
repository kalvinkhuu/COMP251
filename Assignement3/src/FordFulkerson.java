import java.util.*;
import java.io.File;

public class FordFulkerson {

    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();

        int n = graph.getNbNodes();

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();


        HashMap<Integer, ArrayList<Integer>> prevNodes = new LinkedHashMap<>();


        //Iterative DFS

        stack.push(source);
        prevNodes.put(source, new ArrayList<>());
        while(!stack.isEmpty()){
            Integer currNode = stack.pop();
            if(currNode.equals(destination)){
                ArrayList<Integer> temp = prevNodes.get(currNode);
                temp.add(currNode);
                return temp;
            }
            List<Edge> adjacent = new ArrayList<>();
            // In all the edges in the graph, I want the edges connected to the source
            for(Edge e : graph.getEdges()){
                // Need to add more conditions
                /*
                *   if it's sourced to the current node
                *       if the weight of the edge is not at its maximum
                *   if it's destined to the current node
                *       if the weight of the edge is not at 0
                * */
                if (e.nodes[0] == currNode){
                    adjacent.add(e);
                }
            }

            if (adjacent.size() == 0){
                path.remove(currNode);
            }

            if (!visited[currNode]){
                visited[currNode] = true;
                for(Edge e: adjacent){
                    stack.push(e.nodes[1]);
                    ArrayList<Integer> temp = new ArrayList<>(prevNodes.get(e.nodes[0]));
                    temp.add(currNode);
                    prevNodes.put(e.nodes[1], temp);
                }
            }
        }

        // No path
        return null;
    }


    public static String fordfulkerson( WGraph graph){
        String answer="";
        int maxFlow = 0;

        // Copy of the graph
        WGraph resultGraph = new WGraph(graph);
        for(Edge e : resultGraph.getEdges()){
            resultGraph.setEdge(e.nodes[0], e.nodes[1], 0); // Initialized it to zero
        }
        Integer[][] weightTable = originalWeightEdge(graph);

        // Get all possible paths
        while(true){
            ArrayList<Integer> possiblePath = pathDFS(resultGraph.getSource(), resultGraph.getDestination(), resultGraph);
            if (possiblePath.size() == 0){
                break;
            }

            // Need to find a way to check the back edges and measure the bottleneck


            Integer bottleneckValue = findBottleneckValue(possiblePath, resultGraph, weightTable);
            maxFlow += bottleneckValue;

            for (int i = 0; i < possiblePath.size(); i++) {
                try{
                    Integer source = possiblePath.get(i);
                    Integer destination = possiblePath.get(i+1);
                    if (resultGraph.getEdge(source, destination) != null){
                        resultGraph.getEdge(source, destination).weight += bottleneckValue;
                    }
                    else{
                        resultGraph.getEdge(source, destination).weight -= bottleneckValue;
                    }
                } catch (Exception e){
                    break;
                }
            }
        }

        graph = resultGraph;

        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }

    private static Integer[][] originalWeightEdge(WGraph wGraph){
        Integer[][] weightTable = new Integer[wGraph.getNbNodes()][wGraph.getNbNodes()];
        for(Edge e : wGraph.getEdges()){
            Integer source = e.nodes[0];
            Integer destination = e.nodes[1];
            weightTable[source][destination] = e.weight;
        }
        return weightTable;
    }

    private static Integer findBottleneckValue(ArrayList<Integer> path, WGraph wGraph, Integer[][] weightTable){
        Integer minimumWeight = Integer.MAX_VALUE;
        for (int i = 0; i < path.size(); i++) {
            try{
                Integer source = path.get(i);
                Integer destination = path.get(i+1);
                Integer weightEdge = wGraph.getEdge(source, destination).weight;
                Integer maxWeight = weightTable[source][destination];

                Integer possibleBottleneck = maxWeight - weightEdge;
                // Could check if it's a back edge 
                if (possibleBottleneck < minimumWeight){
                    minimumWeight = possibleBottleneck;
                }

            }
            catch (Exception e){
                break;
            }
        }
        return minimumWeight;

    }


    public static void main(String[] args){
//        String file = args[0];
//        File f = new File(file);
//        WGraph g = new WGraph(file);
//        System.out.println(fordfulkerson(g));

        String path = "C:\\Users\\Kalvin\\Documents\\GitHub\\COMP251\\Assignement3\\ff2.txt";
        WGraph g = new WGraph(path);
//         System.out.println(fordfulkerson(g));
        System.out.println(pathDFS(0,5,g));



//        WGraph g = new WGraph();
//        g.setSource(0);
//        g.setDestination(5);
//        Edge[] edges = new Edge[] {
//                new Edge(0, 1, 10),
//                new Edge(0, 2, 5),
//                new Edge(2, 4, 5),
//                new Edge(1, 3, 10),
//                new Edge(1, 6, 5),
//                new Edge(3, 0, 10),
//                new Edge(3, 5, 5)
//        };
//
//        Arrays.stream(edges).forEach(e->g.addEdge(e));
//        System.out.println(FordFulkerson.pathDFS(0, 5, g));

    }
}

