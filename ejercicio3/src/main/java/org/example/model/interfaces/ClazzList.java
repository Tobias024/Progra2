package org.example.model.interfaces;

public interface ClazzList {
    void add(Clazz clazz);
    void remove(Clazz clazz);
    Clazz get(int index);
    boolean contains(Clazz clazz);
    int size();
    boolean isEmpty();
}