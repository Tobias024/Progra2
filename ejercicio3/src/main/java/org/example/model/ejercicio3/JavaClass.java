package org.example.model.ejercicio3;

// Ejercicio 1: TDA Clazz redefinido como JavaClass
public class JavaClass extends JavaElement {

    public JavaClass(String className) {
        super(className);
    }

    @Override
    public boolean canHaveClassChild() {
        return true; // Las clases pueden tener clases hijas
    }

    @Override
    public boolean canHaveInterfaceChild() {
        return false; // NUNCA una clase puede tener una interfaz como hija
    }

    @Override
    public boolean canHaveAbstractClassChild() {
        return true; // Las clases pueden tener clases abstractas como hijas
    }

    @Override
    public boolean canHaveEnumChild() {
        return true; // Las clases pueden tener enums como hijos
    }

    @Override
    public boolean canHaveRecordChild() {
        return false; // Los records no pueden heredar de clases
    }

    @Override
    public boolean canBeParentOf(JavaElement child) {
        if (child instanceof JavaClass) {
            return canHaveClassChild();
        } else if (child instanceof JavaInterface) {
            return canHaveInterfaceChild();
        } else if (child instanceof AbstractClass) {
            return canHaveAbstractClassChild();
        } else if (child instanceof Record) {
            return canHaveRecordChild();
        }
        return false;
    }


    public boolean isChildOf(JavaClass parent) {
        return hasParentInHierarchy(this.children, parent.getName());
    }

    private boolean hasParentInHierarchy(StringBinaryTree tree, String parentName) {
        if (tree == null || tree.isEmpty()) {
            return false;
        }

        if (tree.getRoot().equals(parentName)) {
            return true;
        }

        return hasParentInHierarchy(tree.getLeft(), parentName) ||
                hasParentInHierarchy(tree.getRight(), parentName);
    }
}
