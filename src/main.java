import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        /**
        Tree<Integer> tree = new TreeImpl<>();

        tree.add(60);
        tree.add(71);
        tree.add(82);
        tree.add(91);
        tree.add(53);
        tree.add(50);
        tree.add(66);
        tree.add(40);
        tree.add(55);
        tree.add(70);
        tree.add(31);
        tree.add(42);
        tree.add(45);
        tree.add(43);
        tree.add(69);
        tree.add(67);
        tree.add(68);
        tree.add(81);

        tree.display();

        tree.remove(70);

        tree.display();

        tree.traverse(Tree.TraversMode.IN_ORDER);
        tree.traverse(Tree.TraversMode.PRE_ORDER);
        tree.traverse(Tree.TraversMode.POST_ORDER);
         */

        TreeGenerator();
    }

    public static boolean isBalanced(Node node) {
        return (node == null) ||
                isBalanced(node.getLeftChild()) &&
                        isBalanced(node.getRightChild()) &&
                        Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
    }
    private static int height(Node node) {
        return node == null ? 0 : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }

    private static void TreeGenerator() {
        int a = -25;
        int b = 25;
        int countTrees = 4;

        for (int i = 0; i < countTrees; i++) {
            Tree<Integer> tree = new TreeImpl<>();
            for (int j = 0; j < 20; j++) {
                int random = a + (int)(Math.random() * ((b - a) + 1));
                tree.add(random);
            }

            tree.display();
        }

    }

}
