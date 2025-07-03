package org.example.model.nodes;

import org.example.model.interfaces.InterfaceClazz;

public class InterfaceListNode {

    private InterfaceClazz value;
    private InterfaceListNode next;

    public InterfaceListNode(InterfaceClazz value, InterfaceListNode next) {
        this.value = value;
        this.next = next;
    }

    public InterfaceClazz getValue() {
        return value;
    }

    public void setValue(InterfaceClazz value) {
        this.value = value;
    }

    public InterfaceListNode getNext() {
        return next;
    }

    public void setNext(InterfaceListNode next) {
        this.next = next;
    }
}