public class TreeImpl<E extends Comparable<? super  E>> implements Tree<E> {

    private Node<E> root;
    private int size;

    private class NodeAndParent {
        private Node<E> current;
        private Node<E> parent;

        public NodeAndParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        return nodeAndParent.current != null;
    }

    private NodeAndParent doFind(E value) {
        Node<E> current = root;
        Node<E> parent = null;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, parent);
            }

            parent = current;

            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
            } else {
                current = parent.getRightChild();
            }
        }

        return new NodeAndParent(null, parent);
    }

    @Override
    public boolean add(E value) {
        return false;
    }

    @Override
    public boolean remove(E value) {
        return false;
    }

    @Override
    public boolean isEmpty(E value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void display() {

    }

    @Override
    public void traverse(TraversMode mode) {

    }
}
