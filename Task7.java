// O(n)
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int value;
    List<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

class Task7 {
    public static void main(String[] args) {
        // structuring the tree by creating the nodes then add them as children to their parents
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(6);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        node1.children.add(node4);
        node2.children.add(node5);
        TreeNode node0 = new TreeNode(1);
        node0.children.addAll(List.of(node1, node2, node3));

        computeElementAndHeight(node0);
    }

    // Entry point for the code where it starts by calling the function on the root and initializing the height to be 0 for the root
    private static void computeElementAndHeight(TreeNode node) {
        System.out.println("Node height");
        computeElementAndHeight(node, 0);
    }

    private static void computeElementAndHeight(TreeNode node, int height) {
        // print this node with its height
        System.out.println(node.value + "\t  " + height);

        for (TreeNode child : node.children) {
            // call the function recursively on each child while incrementing the height
            computeElementAndHeight(child, height + 1);
        }
    }
}
