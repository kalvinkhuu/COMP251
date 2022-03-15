import java.io.FileWriter;
import java.io.IOException;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;

// class RedBlackTree implements the operations in Red Black Tree
public class RedBlackTree {

    /*
    * Node Structure with respective parameters
    * */
    static class Node {
        int data; // Key or Value of Node
        Node parent; // Pointer to parent Node
        Node left; // Pointer to left Node
        Node right; // Pointer to right Node
        boolean color; // Color of Node: Red = true & Black = false
        Node(int data){
            this.data = data;
        }
        Node(){};
    }




    private Node root;
    private Node nullTree;
    private static int counter = 0;
    private static final boolean RED = true;
    private static final boolean BLACK = false;


    public RedBlackTree() {
         nullTree = new Node();
         nullTree.color = BLACK;
         nullTree.left = null;
         nullTree.right = null;
         root = nullTree;
    }


    // Search for a key in a tree, returns the node if found
    private Node searchRedBlackTree(Node node, int key) {

        // Iterates through the tree
        while (node != null){
            counter++;
            // If found, the node will be return
            if (key == node.data) {
                return node;
            }

            // Goes to the left node if key has a value lower than the node data
            if (key < node.data) {
                node = node.left;
            }
            // Goes to the right node if key has a value higher than the node data
            else {
                node = node.right;
            }
        }
        // Returns null if not found
        return null;

    }

    public Node searchTree(int k) {
        return searchRedBlackTree(this.root, k);
    }

    // Left rotation of the node with respect to their parents
    public void leftRotation(Node node){
        Node newNode = node.right;
        node.right = newNode.left;
        newNode.left.parent = node;
        newNode.left = node;
        if (node.parent == null){
            this.root = newNode;
        }

        else if (node == node.parent.left){
            node.parent.left = newNode;
        }
        else {
            node.parent.right = newNode;
        }
        newNode.parent = node.parent;
        newNode.left = node;
        node.parent = newNode;
    }

    // Right rotation of the node with respect to their parents
    public void rightRotation(Node node){
        Node newNode = node.left;
        node.left = newNode.right;
        newNode.right.parent = node;
        newNode.right = node;
        if (node.parent == null){
            this.root = newNode;
        }

        else if (node == node.parent.left){
            node.parent.left = newNode;
        }
        else {
            node.parent.right = newNode;
        }
        newNode.parent = node.parent;
        newNode.right = node;
        node.parent = newNode;

    }


    // Insertion of a node with key value
    public void insertNode(int key){
        Node keyNode = new Node(key);
        keyNode.left = nullTree;
        keyNode.right = nullTree;
        keyNode.color = RED;
        Node tempNode = this.root;
        Node keyNodeParent = null;

        // Traverse through the tree
        while (tempNode != nullTree){
            keyNodeParent = tempNode;
            if (key < tempNode.data){
                tempNode = tempNode.left;
            }
            else {
                tempNode = tempNode.right;
            }
        }

        // Sets node to the corresponding pointer with respect to its parent
        keyNode.parent = keyNodeParent;
        if (keyNodeParent == null){
            this.root = keyNode;
        }
        else if (key < keyNodeParent.data){
            keyNodeParent.left = keyNode;
        }
        else if (key > keyNodeParent.data){
            keyNodeParent.right = keyNode;
        }


        if (keyNode.data == root.data){
            keyNode.color = BLACK;
            return;
        }
        if (keyNode.parent.parent == nullTree){
            return;
        }
        RBInsertFixup(keyNode);
    }

    // Fix Insertion Node with respect to the properties of a red-black tree
    private void RBInsertFixup(Node node){
        Node tempNode;
        while (node.parent.color == RED){
            if (node.parent == node.parent.parent.left){
                tempNode = node.parent.parent.right; // Uncle
                if (tempNode.color == RED){
                    // Case 1 of the Lecture 6 - Comp251BST_RedBlack Page 22
                    node.parent.color = BLACK;
                    tempNode.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.right){
                        // Case 2 of the Lecture 6 - Comp251BST_RedBlack Page 22
                        node = node.parent;
                        leftRotation(node);
                    }
                    // Case 3 of the Lecture 6 - Comp251BST_RedBlack Page 22
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotation(node.parent.parent);

                }

            }
            else{
                // Symmetry
                tempNode = node.parent.parent.left;
                if (tempNode.color == RED){
                    node.parent.color = BLACK;
                    tempNode.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                }
                else{
                    if (node == node.parent.left){
                        node = node.parent;
                        rightRotation(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotation(node.parent.parent);
                }

            }
            if (node == root){
                break;
            }
        }
        this.root.color = BLACK;
    }

    public static int runSearch(int n){
        counter = 0;
        RedBlackTree tree = new RedBlackTree();
        for (int i = 1; i <= n ; i++) {
            tree.insertNode(i);
        }
        tree.searchTree(n);

        return counter;
    }




    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != nullTree) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == RED ? "RED" : "BLACK";
            System.out.println(root.data + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public static void main(String[] args) throws IOException {
        RedBlackTree bst = new RedBlackTree();

        for (int i = 0; i < 50; i++) {
            bst.insertNode(i);
        }
        System.out.println(counter);
        bst.prettyPrint();
//
//
//        int samples = 1000;
//        double[] ns = new double[samples];
//        double[] execution_times = new double[samples];
//        for (int i=0; i<samples; i++) {
//            execution_times[i] = runSearch(i);
//            ns[i] = i;
//        }
//
//        // create chart
//        XYChart chart = QuickChart.getChart("Execution Time of Search in Red-Black Tree", "Number of nodes", "Number of operations", "Search Runtime", ns, execution_times);
//        double[] n2s = new double[samples];
//        // add reference quadratic
////        for (int i=0; i<samples; i++) {
////            n2s[i] = (Math.pow(ns[i], 2)/25 + 500);
////        }
////        chart.addSeries("n^2 / 25 + 500", ns, n2s).setMarker(SeriesMarkers.NONE);;
//        // display chart
//        //new SwingWrapper<>(chart).displayChart();
//
//        // save chart
//        try {
//            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }





//        FileWriter data = new FileWriter("runtime.csv", false);
//
//
//        double[] nodes = new double[1001];
//        double[] numOperations = new double[1001];
//        for(int runs = 1; runs <= 1000; runs++){
//
//            numOperations[runs] = runSearch(runs);
//            nodes[runs] = runs;
//
//            data.append(runs + "," + numOperations[runs] + "\n");
//        }
//        XYChart chart = QuickChart.getChart("Execution Time of Search in Red-Black Tree", "Number of nodes", "Number of operations", "Search Runtime", nodes, numOperations);
//
//        try {
//            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        data.flush();
//        data.close();

    }
}
