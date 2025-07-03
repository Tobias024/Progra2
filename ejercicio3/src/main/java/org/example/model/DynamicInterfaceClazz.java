package org.example.model;

import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.ClazzList;
import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;

public class DynamicInterfaceClazz implements InterfaceClazz {

    private String interfaceName;
    private InterfaceClazz parentInterface;
    private ClazzList implementingClasses;

    public DynamicInterfaceClazz(String interfaceName) {
        this.interfaceName = interfaceName;
        this.parentInterface = null;
        this.implementingClasses = new DynamicClazzList();
    }

    @Override
    public void setParentInterface(InterfaceClazz parent) {
        this.parentInterface = parent;
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

        // Recorrer la cadena de herencia lineal
        InterfaceClazz current = this.parentInterface;
        while (current != null) {
            if (current.equals(other)) {
                return true;
            }
            current = current.getParentInterface();
        }
        return false;
    }

    @Override
    public String getInterfaceName() {
        return interfaceName;
    }

    @Override
    public InterfaceClazz getParentInterface() {
        return parentInterface;
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