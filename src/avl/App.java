package avl;

public class App {
    public static void main(String[] args) {
        Tree avl = new AvlTree();

        avl.insert(3);
        avl.insert(1);
        avl.insert(2);

        avl.traverse();
    }
}
