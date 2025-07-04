package org.example.model.ejercicio3;

public class StringBinaryTreeImpl implements StringBinaryTree {
    private String root;
    private StringBinaryTree left;
    private StringBinaryTree right;

    public StringBinaryTreeImpl() {
        this.root = null;
        this.left = null;
        this.right = null;
    }

    public StringBinaryTreeImpl(String root) {
        this.root = root;
        this.left = null;
        this.right = null;
    }

    @Override
    public String getRoot() {
        return root;
    }

    @Override
    public StringBinaryTree getLeft() {
        return left;
    }

    @Override
    public StringBinaryTree getRight() {
        return right;
    }

    @Override
    public void removeLeft() {
        this.left = null;
    }

    @Override
    public void removeRight() {
        this.right = null;
    }

    @Override
    public void addLeft(String value) {
        this.left = new StringBinaryTreeImpl(value);
    }

    @Override
    public void addRight(String value) {
        this.right = new StringBinaryTreeImpl(value);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public void setLeft(StringBinaryTree left) {
        this.left = left;
    }

    @Override
    public void setRight(StringBinaryTree right) {
        this.right = right;
    }
}
