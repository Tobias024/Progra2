package org.example.model.ejercicio3;

// Ejercicio 2: TDA Interface
public class Interface {
    private String interfaceName;
    private StringBinaryTree children; // Puede tener interfaces o clases como hijos

    public Interface(String interfaceName) {
        this.interfaceName = interfaceName;
        this.children = new StringBinaryTreeImpl();
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void addImplementer(String implementerName) {
        if (children.isEmpty()) {
            children.setRoot(implementerName); //Si está vacío: seteo raíz
        } else if (children.getLeft() == null) {
            children.addLeft(implementerName); //si falta hijo izq agrego
        } else if (children.getRight() == null) {
            children.addRight(implementerName); // lo mismo pero derecho
        }
    }

    public StringBinaryTree getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Interface interfaceObj = (Interface) obj;
        return interfaceName.equals(interfaceObj.interfaceName);
    }
}
