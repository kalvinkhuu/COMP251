// https://www.happycoders.eu/algorithms/red-black-tree-java/




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
        }

    }


    Node root = null;

    public void rightRotation(Node node){
        Node parent = node.head;
        Node lChild = node.left;

        node.left = lChild.right;
        if (lChild.right != null){
            lChild.right.head = node;
        }

        lChild.right = node;
        node.head = lChild;

    }

    private void changeParentChild (Node parent, Node oldChild, Node newChild){
        if (parent == null){
            root = newChild;
        }

    }


    public Node search(int k){
        Node node = root;
        while (node != null){
            if (k == node.data){
                return node;
            }
            if (k > node.data){
                node = node.right;
            }
            else {
                node = node.left;
            }
        }
        return null;
    }


    public void insertNode(int key){
        Node node = root;
        Node parent = null;
        while(node != null){
            parent = node;
            if (key > node.data){
                node = node.right;
            }
            else if (key < node.data){
                node = node.left;
            }
            else {
                throw new IllegalArgumentException("Key already exist");
            }
        }

        Node newNode = new Node(key);
        newNode.color = false;
        if (parent == null){
            head = newNode;
        }
        else if (key > parent.data){
            parent.right = newNode;
        }
        else {
            parent.left = newNode;
        }
        newNode.head = parent;


        repairTree(newNode);

    }


    public void repairTree(Node node){

    }

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

    public static void main(String[] args) {
        System.out.println("hi");
    }

}