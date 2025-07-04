package org.example.model;

import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.ClazzList;
import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;

public class DynamicInterfaceClazz implements InterfaceClazz {

    private String interfaceName;
    private InterfaceList parentInterfaces;
    private ClazzList implementingClasses;

    public DynamicInterfaceClazz(String interfaceName) {
        this.interfaceName = interfaceName;
        this.parentInterfaces = new DynamicInterfaceList();
        this.implementingClasses = new DynamicClazzList();
    }

    @Override
    public void addParentInterface(InterfaceClazz parent) {
        if (parent != null) {
            parentInterfaces.add(parent);
        }
    }

    @Override
    public void addImplementingClass(Clazz clazz) {
        if (clazz != null) {
            implementingClasses.add(clazz);
        }
    }

    @Override
    public boolean inheritsFrom(InterfaceClazz other) {
        if (other == null) {
            return false;
        }

        for (int i = 0; i < parentInterfaces.size(); i++) {
            InterfaceClazz parent = parentInterfaces.get(i);
            if (parent.equals(other) || parent.inheritsFrom(other)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getInterfaceName() {
        return interfaceName;
    }

    @Override
    public InterfaceList getParentInterfaces() {
        // Devolver una copia para evitar modificaciones externas
        InterfaceList copy = new DynamicInterfaceList();
        for (int i = 0; i < parentInterfaces.size(); i++) {
            copy.add(parentInterfaces.get(i));
        }
        return copy;
    }

    @Override
    public ClazzList getImplementingClasses() {
        // Devolver una copia para evitar modificaciones externas
        ClazzList copy = new DynamicClazzList();
        for (int i = 0; i < implementingClasses.size(); i++) {
            copy.add(implementingClasses.get(i));
        }
        return copy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof InterfaceClazz)) return false;
        InterfaceClazz that = (InterfaceClazz) obj;
        return interfaceName.equals(that.getInterfaceName());
    }
}