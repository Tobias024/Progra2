package org.example.model.ejercicio3;

// Clase base abstracta para todos los elementos de Java
public abstract class JavaElement {
    protected String name;
    protected StringBinaryTree children;

    public JavaElement(String name) {
        this.name = name;
        this.children = new StringBinaryTreeImpl();
    }

    public String getName() {
        return name;
    }

    public StringBinaryTree getChildren() {
        return children;
    }

    // Métodos abstractos que cada tipo debe implementar
    public abstract boolean canHaveClassChild();
    public abstract boolean canHaveInterfaceChild();
    public abstract boolean canHaveAbstractClassChild();
    public abstract boolean canHaveEnumChild();
    public abstract boolean canHaveRecordChild();
    public abstract boolean canBeParentOf(JavaElement child);

    // Método para agregar un hijo validando las reglas
    public boolean addChild(JavaElement child) {
        if (canBeParentOf(child)) {
            addChildToTree(child.getName());
            return true;
        }
        return false;
    }

    protected void addChildToTree(String childName) {
        if (children.isEmpty()) {
            children.setRoot(childName);
        } else if (children.getLeft() == null) {
            children.addLeft(childName);
        } else if (children.getRight() == null) {
            children.addRight(childName);
        } else {
            // Si ambos hijos están ocupados, agregar al primer hijo disponible
            addChildToTree(children.getLeft(), childName);
        }
    }

    private void addChildToTree(StringBinaryTree tree, String childName) {
        if (tree.getLeft() == null) {
            tree.addLeft(childName);
        } else if (tree.getRight() == null) {
            tree.addRight(childName);
        } else {
            addChildToTree(tree.getLeft(), childName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JavaElement element = (JavaElement) obj;
        return name.equals(element.name);
    }
}
