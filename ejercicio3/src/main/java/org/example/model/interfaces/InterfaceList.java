package org.example.model.interfaces;

public interface InterfaceList {
    void add(InterfaceClazz interfaceClazz);
    void remove(InterfaceClazz interfaceClazz);
    InterfaceClazz get(int index);
    boolean contains(InterfaceClazz interfaceClazz);
    int size();
    boolean isEmpty();
}