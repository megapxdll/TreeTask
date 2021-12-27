import java.util.Stack;

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
        NodeAndParent nodeAndParent = doFind(value);

        if (nodeAndParent.current != null) {

            return false;
        }

        Node<E> parent = nodeAndParent.parent;
        Node<E> node = new Node<>(value);

        if(isEmpty()) {
            root = node;
        } else if (parent.isLeftChild(value)) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        Node<E> removed = nodeAndParent.current;
        Node<E> parent = nodeAndParent.parent;

        if (removed == null) {
            return false;
        }

        if (removed.isLeaf()) {
            removeLeafNode(removed, parent);
        } else if (removed.hasOnlyOneChild()) {
            removeNodeWithOneChild(removed, parent);
        } else {
            removeNodeWithAllChildren(removed, parent);
        }

        size--;
        return true;
    }

    private void removeNodeWithAllChildren(Node<E> removed, Node<E> parent) {
        Node<E> successor = getSuccessor(removed);

        if (removed == root) {
            root = successor;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
    }

    private Node<E> getSuccessor(Node<E> removed) {
        Node<E> successor = removed;
        Node<E> successorParent = null;
        Node<E> current = removed.getRightChild();

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removed.getRightChild() && successorParent != null) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removed.getRightChild());
        }
        successor.setLeftChild(removed.getLeftChild());

        return successor;
    }

    private void removeNodeWithOneChild(Node<E> removed, Node<E> parent) {
        Node<E> child = removed.getLeftChild() != null
                ? removed.getLeftChild()
                : removed.getRightChild();

        if (removed == root) {
            root = child;
        } else if(parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(child);
        } else parent.setRightChild(child);
    }

    private void removeLeafNode(Node<E> removed, Node<E> parent) {
        if (removed == root) {
            root = null;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public void traverse(TraversMode mode) {

    }
}
