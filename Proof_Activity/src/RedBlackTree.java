// class RedBlackTree implements the operations in Red Black Tree
public class RedBlackTree {
    class Node {
        int data; // holds the key
        Node parent; // pointer to the parent
        Node left; // pointer to left child
        Node right; // pointer to right child
        boolean color;
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


    private Node searchRedBlackTree(Node node, int key) {

        while (node != null){
            counter++;
            if (key == node.data) {
                return node;
            }

            if (key < node.data) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return null;

    }

    public Node searchTree(int k) {
        return searchRedBlackTree(this.root, k);
    }

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


    public void insertNode(int key){
        Node keyNode = new Node(key);
        keyNode.left = nullTree;
        keyNode.right = nullTree;
        keyNode.color = RED;
        Node tempNode = this.root;
        Node keyNodeParent = null;

        while (tempNode != nullTree){
            keyNodeParent = tempNode;
            if (key < tempNode.data){
                tempNode = tempNode.left;
            }
            else {
                tempNode = tempNode.right;
            }
        }
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

    private void RBInsertFixup(Node node){
        Node tempNode;
        while (node.parent.color == RED){
            if (node.parent == node.parent.parent.left){
                tempNode = node.parent.parent.right;
                if (tempNode.color == RED){
                    node.parent.color = BLACK;
                    tempNode.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.right){
                        node = node.parent;
                        leftRotation(node);
                    }
                        node.parent.color = BLACK;
                        node.parent.parent.color = RED;
                        rightRotation(node.parent.parent);

                }

            }
            else{
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

    public static void main(String[] args) {
        RedBlackTree bst = new RedBlackTree();

        for (int i = 0; i < 18; i++) {
            bst.insertNode(i);
        }
        System.out.println(counter);
        bst.prettyPrint();
    }
}
