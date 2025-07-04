package org.example.model.ejercicio3;

// Adaptación de BinaryTree para Strings
public interface StringBinaryTree {
    String getRoot();
    StringBinaryTree getLeft();
    StringBinaryTree getRight();
    void removeLeft();
    void removeRight();
    void addLeft(String value);
    void addRight(String value);
    boolean isEmpty();
    void setRoot(String root);
    void setLeft(StringBinaryTree left);
    void setRight(StringBinaryTree right);
}
