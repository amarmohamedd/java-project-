// O(n)

// creates the tree data structure class
class Tree {
    int data;
    Tree[] children;

    public Tree(int data, int numChildren) {
        this.data = data;
        children = new Tree[numChildren];
    }
}

public class Task6 {
    // the logic for checking if two trees are isomorphic or not
    public static boolean isIsomorphic(Tree t1, Tree t2) {
        // first case where both trees are empty
        if (t1 == null && t2 == null) {
            return true;
        // if one tree is empty but the other isn't then not
        } else if (t1 == null || t2 == null) {
            return false;
        }
        // if the two nodes have unequal number of children
        if (t1.children.length != t2.children.length) {
            return false;
        }
        // recursively check if each subtree is isomorphic then the whole tree is isomorphic
        for (int i = 0; i < t1.children.length; i++) {
            if (!isIsomorphic(t1.children[i], t2.children[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Example 1: Isomorphic Trees
        Tree t1 = new Tree(1, 2);
        t1.children[0] = new Tree(2, 0);
        t1.children[1] = new Tree(3, 0);

        Tree t2 = new Tree(1, 2);
        t2.children[0] = new Tree(2, 0);
        t2.children[1] = new Tree(3, 0);

        System.out.println("Example 1: Isomorphic Trees");
        System.out.println("Tree 1:");
        printTree(t1);
        System.out.println("");
        System.out.println("Tree 2:");
        printTree(t2);
        System.out.println("");
        System.out.println("Isomorphic: " + isIsomorphic(t1, t2));
        System.out.println();

        // Example 2: Isomorphic Trees
        Tree t3 = new Tree(1, 3);
        t3.children[0] = new Tree(2, 0);
        t3.children[1] = new Tree(3, 2);
        t3.children[2] = new Tree(4, 0);

        Tree t4 = new Tree(1, 3);
        t4.children[0] = new Tree(2, 0);
        t4.children[1] = new Tree(3, 2);
        t4.children[2] = new Tree(4, 0);

        System.out.println("Example 2: Isomorphic Trees");
        System.out.println("Tree 3:");
        printTree(t3);
        System.out.println("");
        System.out.println("Tree 4:");
        printTree(t4);
        System.out.println("");
        System.out.println("Isomorphic: " + isIsomorphic(t3, t4));
        System.out.println();

        // Example 3: Non-Isomorphic Trees
        Tree t5 = new Tree(1, 2);
        t5.children[0] = new Tree(2, 1);
        t5.children[0].children[0] = new Tree(3, 0);
        t5.children[1] = new Tree(4, 0);

        Tree t6 = new Tree(1, 2);
        t6.children[0] = new Tree(2, 0);
        t6.children[1] = new Tree(4, 0);

        System.out.println("Example 3: Non-Isomorphic Trees");
        System.out.println("Tree 5:");
        printTree(t5);
        System.out.println("");
        System.out.println("Tree 6:");
        printTree(t6);
        System.out.println("");
        System.out.println("Isomorphic: " + isIsomorphic(t5, t6));
        System.out.println();

        // Example 4: Non-Isomorphic Trees
        Tree t7 = new Tree(1, 2);
        t7.children[0] = new Tree(2, 0);
        t7.children[1] = new Tree(3, 0);

        Tree t8 = new Tree(1, 2);
        t8.children[0] = new Tree(2, 0);
        t8.children[1] = new Tree(4, 0);

        System.out.println("Example 4: Isomorphic Trees");
        System.out.println("Tree 7:");
        printTree(t7);
        System.out.println("");
        System.out.println("Tree 8:");
        printTree(t8);
        System.out.println("");
        System.out.println("Isomorphic: " + isIsomorphic(t7, t8));
        System.out.println();
    }

    // helper function that prints the whole tree in one line
    public static void printTree(Tree root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " [");
        for (int i = 0; i < root.children.length; i++) {
            printTree(root.children[i]);
            if (i != root.children.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }
}
