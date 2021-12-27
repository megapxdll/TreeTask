

public class main {
    public static void main(String[] args) {
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
    }
}
