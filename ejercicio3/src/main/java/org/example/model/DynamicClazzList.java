package org.example.model;

import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.ClazzList;
import org.example.model.nodes.ClazzListNode;

public class DynamicClazzList implements ClazzList {

    private ClazzListNode first;
    private int count;

    public DynamicClazzList() {
        this.first = null;
        this.count = 0;
    }

    @Override
    public void add(Clazz clazz) {
        if (clazz != null && !contains(clazz)) {
            first = new ClazzListNode(clazz, first);
            count++;
        }
    }

    @Override
    public void remove(Clazz clazz) {
        if (first == null || clazz == null) {
            return;
        }

        if (first.getValue().equals(clazz)) {
            first = first.getNext();
            count--;
            return;
        }

        ClazzListNode current = first;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(clazz)) {
                current.setNext(current.getNext().getNext());
                count--;
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public Clazz get(int index) {
        if (index < 0 || index >= count) {
            throw new RuntimeException("Index out of bounds");
        }

        ClazzListNode current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    @Override
    public boolean contains(Clazz clazz) {
        ClazzListNode current = first;
        while (current != null) {
            if (current.getValue().equals(clazz)) {
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