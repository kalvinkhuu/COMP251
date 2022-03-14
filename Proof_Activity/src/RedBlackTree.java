// class RedBlackTree implements the operations in Red Black Tree
public class RedBlackTree {
    class Node {
        int data; // holds the key
        Node parent; // pointer to the parent
        Node left; // pointer to left child
        Node right; // pointer to right child
        boolean color; // 1 . Red, 0 . Black
    }




    private Node root;
    private Node TNULL;
    private static int counter = 0;
    private static final boolean RED = true;
    private static final boolean BLACK = false;


    public RedBlackTree() {
        TNULL = new Node();
        TNULL.color = BLACK;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
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

    public void leftRotate(Node node) {
        Node newNode = node.right;
        node.right = newNode.left;
        if (newNode.left != TNULL) {
            newNode.left.parent = node;
        }
        newNode.parent = node.parent;

        // If it is the root
        if (node.parent == null) {
            this.root = newNode;
        }

        // Setting the parent to the new root
        else if (node == node.parent.left) {
            node.parent.left = newNode;
        }
        else {
            node.parent.right = newNode;
        }

        newNode.left = node;
        node.parent = newNode;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;

        // If it is the root
        if (x.parent == null) {
            this.root = y;
        }

        // Setting the parent to the new root
        else if (x == x.parent.right) {
            x.parent.right = y;
        }
        else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }

    public void insert(int key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = RED; // new node must be red

        Node y = null;
        Node x = this.root;

        // Traverse through the tree
        while (x != TNULL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // Setting the node in place
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        // if new node is a root node, simply return
        if (node.parent == null) {
            node.color = BLACK;
            return;
        }

        // if the grandparent is null, simply return
        if (node.parent.parent == null) {
            return;
        }

        // Fix the tree
        fixInsert(node);
    }

    // fix the red-black tree
    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == RED) {
                    // case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                }
                else {
                    if (k == k.parent.left) {
                        // case 3.2.2
                        k = k.parent;
                        rightRotate(k);
                    }
                    // case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    leftRotate(k.parent.parent);
                }
            }
            else {
                u = k.parent.parent.right; // uncle

                if (u.color == RED) {
                    // mirror case 3.1
                    u.color = BLACK;
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // mirror case 3.2.2
                        k = k.parent;
                        leftRotate(k);
                    }
                    // mirror case 3.2.1
                    k.parent.color = BLACK;
                    k.parent.parent.color = RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = BLACK;
    }



    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    private void printHelper(Node root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != TNULL) {
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
        bst.insert(8);
        bst.insert(18);
        bst.insert(5);
        bst.insert(15);
        bst.insert(17);
        bst.insert(25);
        bst.insert(40);
        bst.insert(80);
        bst.searchTree(80);
        System.out.println(counter);
        bst.prettyPrint();
    }
}
