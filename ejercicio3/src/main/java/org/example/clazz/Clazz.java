package org.example.clazz;

import org.example.clazz.IClazz;

import java.util.*;

/**
 * Implementación del TDA Clazz que representa una clase Java en una jerarquía de herencia.
 * Utiliza una estructura de árbol donde cada nodo puede tener un padre y múltiples hijos.
 */
public class Clazz implements IClazz {
    private final String name;
    private IClazz parent;
    private final DynamicArray children;

    /**
     * Constructor para crear una clase sin padre (clase raíz).
     * @param name el nombre de la clase
     */
    public Clazz(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("El nombre de la clase no puede ser null o vacío");
        }
        this.name = name.trim();
        this.parent = null;
        this.children = new DynamicArray();
    }

    /**
     * Constructor para crear una clase con padre.
     * @param name el nombre de la clase
     * @param parent la clase padre
     */
    public Clazz(String name, IClazz parent) {
        this(name);
        setParent(parent);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IClazz getParent() {
        return parent;
    }

    @Override
    public IClazz[] getChildren() {
        return children.toArray();
    }

    @Override
    public int getChildrenCount() {
        return children.size();
    }

    @Override
    public void addChild(IClazz child) {
        if (child == null) {
            throw new IllegalArgumentException("La clase hija no puede ser null");
        }
        if (child == this) {
            throw new IllegalArgumentException("Una clase no puede ser hija de sí misma");
        }
        if (this.isChildOf(child)) {
            throw new IllegalArgumentException("No se puede crear un ciclo en la jerarquía");
        }
        if (!children.contains(child)) {
            this.children.add(child);
            if (child instanceof Clazz) {
                ((Clazz) child).parent = this;
            }
        }
    }

    @Override
    public boolean isChildOf(IClazz other) {
        if (other == null || other == this) {
            return false;
        }

        IClazz current = this.parent;
        DynamicArray visited = new DynamicArray();

        while (current != null && !visited.contains(current)) {
            visited.add(current);
            if (current.equals(other)) {
                return true;
            }
            current = current.getParent();
        }
        return false;
    }

    @Override
    public boolean isParentOf(IClazz other) {
        return other != null && other.isChildOf(this);
    }

    @Override
    public int getDepth() {
        int depth = 0;
        IClazz current = this.parent;
        DynamicArray visited = new DynamicArray();

        while (current != null && !visited.contains(current)) {
            visited.add(current);
            depth++;
            current = current.getParent();
        }
        return depth;
    }

    /**
     * Establece el padre de esta clase.
     * @param parent la nueva clase padre
     */
    private void setParent(IClazz parent) {
        if (parent != null) {
            if (parent == this) {
                throw new IllegalArgumentException("Una clase no puede ser padre de sí misma");
            }
            if (parent.isChildOf(this)) {
                throw new IllegalArgumentException("No se puede crear un ciclo en la jerarquía");
            }
            parent.addChild(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof IClazz)) return false;
        IClazz other = (IClazz) obj;
        return this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Clazz{name='" + name + "', depth=" + getDepth() + "}";
    }
}