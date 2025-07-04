package org.example.model.ejercicio3;

// Clase especial Object - raíz de toda la jerarquía - Corregida
public class ObjectClass extends JavaElement {

    public ObjectClass() {
        super("Object");
    }

    @Override
    public boolean canHaveClassChild() {
        return true;
    }

    @Override
    public boolean canHaveInterfaceChild() {
        return true; // Object es especial, puede tener interfaces directamente
    }

    @Override
    public boolean canHaveAbstractClassChild() {
        return true;
    }

    @Override
    public boolean canHaveEnumChild() {
        return true;
    }

    @Override
    public boolean canHaveRecordChild() {
        return true;
    }

    @Override
    public boolean canBeParentOf(JavaElement child) {
        // Object puede ser padre de cualquier elemento
        return true;
    }
}
