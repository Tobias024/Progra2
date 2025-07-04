package org.example.model.nodes;

import org.example.model.interfaces.Clazz;

public class ClazzListNode {

    private Clazz value;
    private ClazzListNode next;

    public ClazzListNode(Clazz value, ClazzListNode next) {
        this.value = value;
        this.next = next;
    }

    public Clazz getValue() {
        return value;
    }

    public void setValue(Clazz value) {
        this.value = value;
    }

    public ClazzListNode getNext() {
        return next;
    }

    public void setNext(ClazzListNode next) {
        this.next = next;
    }
}