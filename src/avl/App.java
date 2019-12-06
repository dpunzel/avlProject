package avl;

public class App {
    public static void main(String[] args) {
        Tree<String> avl = new AvlTree<>();

        avl.insert("a");
        avl.insert("c");
        avl.insert("b");

//
//        avl.delete(15);

        avl.traverse();
    }
}
