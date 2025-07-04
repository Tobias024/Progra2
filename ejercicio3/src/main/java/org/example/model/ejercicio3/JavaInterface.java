package org.example.model.ejercicio3;

// Ejercicio 2: TDA Interface redefinido como JavaInterface
public class JavaInterface extends JavaElement {

    public JavaInterface(String interfaceName) {
        super(interfaceName);
    }

    @Override
    public boolean canHaveClassChild() {
        return true; // Las interfaces pueden tener clases que las implementen
    }

    @Override
    public boolean canHaveInterfaceChild() {
        return true; // Las interfaces pueden tener interfaces hijas (extends)
    }

    @Override
    public boolean canHaveAbstractClassChild() {
        return true; // Las clases abstractas pueden implementar interfaces
    }

    @Override
    public boolean canHaveEnumChild() {
        return true; // Los enums pueden implementar interfaces
    }

    @Override
    public boolean canHaveRecordChild() {
        return true; // Los records pueden implementar interfaces
    }

    @Override
    public boolean canBeParentOf(JavaElement child) {
        if (child instanceof JavaClass) {
            return canHaveClassChild();
        } else if (child instanceof JavaInterface) {
            return canHaveInterfaceChild();
        } else if (child instanceof AbstractClass) {
            return canHaveAbstractClassChild();
        } else if (child instanceof Enumeration) {
            return canHaveEnumChild();
        } else if (child instanceof Record) {
            return canHaveRecordChild();
        }
        return false;
    }
}
