package org.example.model;

import org.example.model.interfaces.Clazz;

public class DynamicClazz implements Clazz {

    private String className;
    private Clazz parent;

    public DynamicClazz(String className) {
        this.className = className;
        this.parent = null;
    }

    public DynamicClazz(String className, Clazz parent) {
        this.className = className;
        this.parent = parent;
    }

    @Override
    public boolean isChildOf(Clazz other) {
        if (other == null) {
            return false;
        }

        Clazz current = this.parent;
        while (current != null) {
            if (current.equals(other)) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Clazz getParent() {
        return parent;
    }

    @Override
    public void setParent(Clazz parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Clazz)) return false;
        Clazz clazz = (Clazz) obj;
        return className.equals(clazz.getClassName());
    }
}