package avl;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    @Override
    public void insert(T data) {
        root = insert(root, data);
    }

    @Override
    public void delete(T data) {
        root = delete(root, data);
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            return new Node(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeftNode(insert(node.getLeftNode(), data));
        } else {
            node.setRightNode(insert(node.getRightNode(), data));
        }

        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        node = settleViolation(data, node);
        return node;
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null) return node;

        // look for node to get rid of
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftNode(delete(node.getLeftNode(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightNode(delete(node.getRightNode(), data));
        } else { // found the node to remove
            if (node.getLeftNode() == null && node.getRightNode() == null) {
                System.out.println("Removing a leaf node...");
                return null;
            }

            if (node.getLeftNode() == null) {
                System.out.println("Removing the right child...");
                Node<T> tempNode = node. getRightNode();
                node = null;
                return tempNode;
            }

            if (node.getRightNode() == null) {
                System.out.println("Removing the left child...");
                Node<T> tempNode = node. getLeftNode();
                node = null;
                return tempNode;
            }

            System.out.println("Removing item with two children");
            Node<T> tempNode = getPredecessor(node.getLeftNode());

            node.setData(tempNode.getData());
            node.setLeftNode(delete(node.getLeftNode(), tempNode.getData()));
        }

        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) + 1);
        // check on every deletion operation whether the tree has become unbalanced or not
        return settleDeletion(node);
    }

    private Node<T> settleDeletion(Node<T> node) {
        int balance = getBalance(node);
        // check if left heavy
        if (balance > 1) {
            // left right heavy situation: left rotation on parent + right
            // rotation of grandparent
            if (getBalance(node.getLeftNode()) < 0) {
                node.setLeftNode(leftRotation(node.getLeftNode()));
            }
            // right rotation on grandparent
            return rightRotation(node);
        }

        // right left heavy
        if (balance < -1) {
            if (getBalance(node.getRightNode()) > 0) {
                node.setRightNode(rightRotation(node.getRightNode()));
            }
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> getPredecessor(Node<T> node) {
        Node<T> predecessor = node;

        while (predecessor.getRightNode() != null)
            predecessor = predecessor.getRightNode();

        return predecessor;
    }

    private Node<T> settleViolation(T data, Node<T> node) {
        int balance = getBalance(node);

        // left heavy left left
        if (balance > 1 && data.compareTo(node.getLeftNode().getData()) < 0) {
            return rightRotation(node);
        }

        // right heavy right right
        if (balance < -1 && data.compareTo(node.getRightNode().getData()) >0) {
            return leftRotation(node);
        }

        // left right heavy
        if (balance > 1 && data.compareTo(node.getLeftNode().getData()) > 0) {
            node.setLeftNode(leftRotation(node.getLeftNode()));
            return rightRotation(node);
        }

        // right left heavy
        if (balance < -1 && data.compareTo(node.getRightNode().getData()) < 0) {
            node.setRightNode(rightRotation(node.getRightNode()));
            return leftRotation(node);
        }

        return node;
    }

    private Node<T> rightRotation(Node<T> node) {
        System.out.println("Rotating to the right on..." + node);

        Node<T> tempLeftNode = node.getLeftNode();
        Node<T> t = tempLeftNode.getRightNode();

        tempLeftNode.setRightNode(node);
        node.setLeftNode(t);

        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) +1);
        tempLeftNode.setHeight(Math.max(height(tempLeftNode.getLeftNode()), height(tempLeftNode.getRightNode())) +1);

        return tempLeftNode;
    }

    private Node<T> leftRotation(Node<T> node) {
        System.out.println("Rotating to the left on..." + node);

        Node<T> tempRightNode = node.getRightNode();
        Node<T> t = tempRightNode.getLeftNode();

        tempRightNode.setLeftNode(node);
        node.setRightNode(t);

        node.setHeight(Math.max(height(node.getLeftNode()), height(node.getRightNode())) +1);
        tempRightNode.setHeight(Math.max(height(tempRightNode.getLeftNode()), height(tempRightNode.getRightNode())) +1);

        return tempRightNode;
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftNode()) - height(node.getRightNode());
    }

    @Override
    public void traverse() {
        if (root == null) return;

        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node.getLeftNode() != null)
            inOrderTraversal(node.getLeftNode());

        System.out.println(node);


        if (node.getRightNode() != null)
            inOrderTraversal(node.getRightNode());

    }


}
