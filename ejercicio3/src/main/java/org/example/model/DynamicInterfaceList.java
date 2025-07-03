package org.example.model;

import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;
import org.example.model.nodes.InterfaceListNode;

public class DynamicInterfaceList implements InterfaceList {

    private InterfaceListNode first;
    private int count;

    public DynamicInterfaceList() {
        this.first = null;
        this.count = 0;
    }

    @Override
    public void add(InterfaceClazz interfaceClazz) {
        if (interfaceClazz != null && !contains(interfaceClazz)) {
            first = new InterfaceListNode(interfaceClazz, first);
            count++;
        }
    }

    @Override
    public void remove(InterfaceClazz interfaceClazz) {
        if (first == null || interfaceClazz == null) {
            return;
        }

        if (first.getValue().equals(interfaceClazz)) {
            first = first.getNext();
            count--;
            return;
        }

        InterfaceListNode current = first;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(interfaceClazz)) {
                current.setNext(current.getNext().getNext());
                count--;
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public InterfaceClazz get(int index) {
        if (index < 0 || index >= count) {
            throw new RuntimeException("Index out of bounds");
        }

        InterfaceListNode current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public boolean contains(InterfaceClazz interfaceClazz) {
        InterfaceListNode current = first;
        while (current != null) {
            if (current.getValue().equals(interfaceClazz)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}