// LUCY GARDNER GMB18183
//Tree class to build the structure of the binary tree.
public class LinkedBinaryTree {

    public LinkedBinaryTree() {
    }

    static class Node<E> {

        private E element;
        private Node<E> left;
        private Node<E> right;

        Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        Node<E> getLeft() {
            return left;
        }

        Node<E> getRight() {
            return right;
        }

        E getElement() { return element; }
    }
}
