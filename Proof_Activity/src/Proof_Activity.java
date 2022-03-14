import org.junit.Test;

// https://www.happycoders.eu/algorithms/red-black-tree-java
public class Proof_Activity {

    public class Node {
        int data;

        Node left;
        Node right;
        Node head;

        // red as false, black as true
        boolean color;

        public Node(int data){
            this.data = data;
            this.left = this.right = null;
        }

    }

    boolean RED = false;
    boolean BLACK = true;

    public Node rightRotation(Node node){
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        return newRoot;
    }

    public Node leftRotation(Node node){
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    public Node search(int k, Node root){
        Node node = root;
        while (node != null){
            if (k == node.data){
                return node;
            }
            if (k < node.data){
                node = node.left;
            }
            else{
                node = node.right;
            }
        }
        return null;
    }

    public Node insertNode(Node T, int z){
        if (T == null){
            return new Node(z);
        }
        if (z < T.data){
            T.left = insertNode(T.left, z);
        }
        else {
            T.right = insertNode(T.right, z);
        }




        return T;
    }


//    public void repairTree(Node node, Node root){
//        Node parent = node.head;
//
//        // Case 1: Parent is null (root) aka Base Case
//        if (parent == null) {
//            // Node is root
//            node.color = true; // Black
//            return;
//        }
//        if (parent.color){
//            return;
//        }
//
//        Node grandparent = parent.head;
//
//        // Case 2: Parent and root are Red
//
//
//        Node uncle = getUncle(parent);
//        // Case 3: Parent and Uncle are Red
//        if (uncle != null && uncle.color == Red){
//            parent.color = Black;
//            grandparent.color = Red;
//            uncle.color = Black;
//
//            repairTree(grandparent, root);
//        }
//
//        // Case 4: Parent is red, Uncle is black
//        // Left side
//        else if (parent == grandparent.left){
//            // If inner child
//            if (node == parent.right){
//                leftRotation(parent, root);
//                parent = node;
//            }
//
//            rightRotation(grandparent, root);
//
//            parent.color = Black;
//            grandparent.color = Red;
//        }
//
//        // Right side
//        else{
//            // If inner child
//            if (node == parent.left){
//                rightRotation(parent, root);
//                parent = node;
//            }
//
//            leftRotation(grandparent,root);
//
//            parent.color = Black;
//            grandparent.color = Red;
//        }
//
//
//    }

    private Node getUncle(Node parent){
        Node grandparent = parent.head;
        if (grandparent.left == parent){
            return grandparent.right;
        }
        else if (grandparent.right == parent){
            return grandparent.left;
        }
        else {
            throw new IllegalArgumentException("parent is not a child of grandparent");
        }
    }

//    public Node createTree (int[] keyArray){
//        Node tree = new Node(0);
//        for (int key: keyArray) {
//            insertNode(key, tree);
//        }
//        return tree;
//    }

    public static void printTree(){
        System.out.println();
    }

    @Test
    public void test1(){
        int[] test1 = {1,3};
        Node tree = new Node(2);
        insertNode(tree, 1);
        System.out.println(tree.left.color);
    }



    public static void main(String[] args) {

    }

}