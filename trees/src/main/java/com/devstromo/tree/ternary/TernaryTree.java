package com.devstromo.tree.ternary;

public class TernaryTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;

    @Override
    public Tree<T> insert(String word, T value) {
        root = insert(root, word, value, 0);
        return this;
    }

    @Override
    public boolean contains(String word) {
        Node<T> node = search(root, word, 0);
        return node != null && node.isEndOfWord();
    }

    @Override
    public T get(String word) {
        Node<T> node = search(root, word, 0);
        return node.getValue();
    }

    @Override
    public void softDelete(String word) {
        delete(root, word,0);
    }

    private void delete(Node<T> node, String word, int index) {
        if(node == null)
            return;
        char character = word.charAt(index);
        if (character < node.getCharacter()) {
            delete(node.getLeftChild(), word, index);
        } else if (character > node.getCharacter()) {
            delete(node.getRightChild(), word, index);
        } else if (index < word.length() - 1) {
            delete(node.getMiddleChild(), word, index + 1);
        } else if (index == word.length() -1 && node.isEndOfWord()) {
            node.setValue(null);
        }
    }

    private Node<T> insert(Node<T> node, String word, T value, int index) {
        char character = word.charAt(index);
        if (node == null) {
            node = new Node<T>(character);
        }
        if (character < node.getCharacter()) {
            node.setLeftChild(insert(node.getLeftChild(), word, value, index));
        } else if (character > node.getCharacter()) {
            node.setRightChild(insert(node.getRightChild(), word, value, index));
        } else if (index < word.length() - 1) {
            node.setMiddleChild(insert(node.getMiddleChild(), word, value, index + 1));
        } else if (index == word.length() - 1) {
            node.setValue(value);
        }
        return node;
    }

    private Node<T> search(Node<T> node, String word, int index) {
        if (node == null) {
            return null;
        }
        char character = word.charAt(index);
        if (character < node.getCharacter()) {
            return search(node.getLeftChild(), word, index);
        } else if (character > node.getCharacter()) {
            return search(node.getRightChild(), word, index);
        } else if (index < word.length() - 1) {
            return search(node.getMiddleChild(), word, index + 1);
        }
        return node;
    }
}
