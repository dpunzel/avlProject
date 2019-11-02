package avl;

public class AvlTree implements Tree{

    private Node root;
    @Override
    public void insert(int datas) {
    }

    @Override
    public void traverse() {
        if (root == null) {
            inOrderTraversal(root);
        }
    }

    private void inOrderTraversal(Node node) {
        if (node.getLeftNode() != null) {
            inOrderTraversal(node.getLeftNode());
        }
        System.out.println(node);
        if (node.getRightNode() != null) {
            inOrderTraversal(node.getRightNode());
        }
    }


}
