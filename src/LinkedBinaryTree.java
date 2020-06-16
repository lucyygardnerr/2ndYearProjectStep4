// LUCY GARDNER GMB18183
public class LinkedBinaryTree {

    protected static class Node<E> implements Position<E> {

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

        @Override
        public E getElement() {
            return element;
        }

    }

    public LinkedBinaryTree() {
    }



}
