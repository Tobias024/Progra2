package org.example.model;

import org.example.model.interfaces.BinaryTree;

public class DynamicBinaryTree implements BinaryTree {

    private final int root;
    private DynamicBinaryTree left;
    private DynamicBinaryTree right;

    public DynamicBinaryTree(int root) {
        this.root = root;
    }

    @Override
    public int getRoot() {
        return this.root;
    }

    @Override
    public BinaryTree getLeft() {
        return left;
    }

    @Override
    public BinaryTree getRight() {
        return right;
    }

    @Override
    public void removeLeft() {
        if(left == null) {
            throw new RuntimeException("No se puede eliminar un hijo que no existe");
        }
        left = null;
    }

    @Override
    public void removeRight() {
        if(right == null) {
            throw new RuntimeException("No se puede eliminar un hijo que no existe");
        }
        right = null;
    }

    @Override
    public void addLeft(int a) {
        if(left != null) {
            throw new RuntimeException("No se puede pisar un hijo que ya existe");
        }
        left = new DynamicBinaryTree(a);
    }

    @Override
    public void addRight(int a) {
        if(right != null) {
            throw new RuntimeException("No se puede pisar un hijo que ya existe");
        }
        right = new DynamicBinaryTree(a);
    }
}
