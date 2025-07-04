package org.example.model.ejercicio3;

// Ejercicio 3: TDA ClassHierarchy - Corregido
public class ClassHierarchy {
    private JavaElement root;
    private StringBinaryTree hierarchyTree;
    private static final String OBJECT_CLASS = "Object";

    public ClassHierarchy() {
        this.root = new ObjectClass();
        this.hierarchyTree = new StringBinaryTreeImpl(OBJECT_CLASS);
    }

    // Método genérico para agregar cualquier elemento
    public boolean addElement(JavaElement parent, JavaElement child) {
        if (parent.canBeParentOf(child)) {
            return parent.addChild(child);
        }
        return false;
    }

    // Agregar una clase que hereda de Object
    public boolean addClassToObject(String className) {
        JavaClass newClass = new JavaClass(className);
        return addToObject(newClass);
    }

    // Agregar una interfaz que hereda de Object
    public boolean addInterfaceToObject(String interfaceName) {
        JavaInterface newInterface = new JavaInterface(interfaceName);
        return addToObject(newInterface);
    }

    // Agregar elemento directamente a Object
    public boolean addToObject(JavaElement element) {
        boolean added = addElement(root, element);
        if (added) {
            addToHierarchyTree(hierarchyTree, element.getName());
        }
        return added;
    }

    private void addToHierarchyTree(StringBinaryTree tree, String name) {
        if (tree.getLeft() == null) {
            StringBinaryTree newNode = new StringBinaryTreeImpl(name);
            tree.setLeft(newNode);
        } else if (tree.getRight() == null) {
            StringBinaryTree newNode = new StringBinaryTreeImpl(name);
            tree.setRight(newNode);
        } else {
            // Si ambos hijos están ocupados, agregar al primer hijo disponible
            addToHierarchyTree(tree.getLeft(), name);
        }
    }

    // Agregar una clase que hereda de otra clase
    public boolean addClassToClass(String parentClassName, String childClassName) {
        JavaElement parentNode = findElement(parentClassName);
        if (parentNode != null && parentNode instanceof ClassElement) {
            return addElement(parentNode, new ClassElement(childClassName));
        }
        return false;
    }

    // Agregar una interfaz que hereda de otra interfaz
    public boolean addInterfaceToInterface(String parentInterfaceName, String childInterfaceName) {
        JavaElement parentNode = findElement(parentInterfaceName);
        if (parentNode != null && parentNode instanceof InterfaceElement) {
            return addElement(parentNode, new InterfaceElement(childInterfaceName));
        }
        return false;
    }

    // Una clase implementa una interfaz (la clase se agrega como hija de la interfaz)
    public boolean addClassImplementsInterface(String interfaceName, String className) {
        JavaElement interfaceNode = findElement(interfaceName);
        if (interfaceNode != null && interfaceNode instanceof InterfaceElement) {
            return addElement(interfaceNode, new ClassElement(className));
        }
        return false;
    }

    // Buscar un elemento en la jerarquía
    public JavaElement findElement(String name) {
        return findElementInTree(hierarchyTree, name);
    }

    private JavaElement findElementInTree(StringBinaryTree tree, String name) {
        if (tree == null || tree.isEmpty()) {
            return null;
        }

        if (tree.getRoot().equals(name)) {
            // Aquí necesitaríamos mantener una referencia a los objetos reales
            // Por simplicidad, retornamos null por ahora
            return null;
        }

        JavaElement leftResult = findElementInTree(tree.getLeft(), name);
        if (leftResult != null) {
            return leftResult;
        }

        return findElementInTree(tree.getRight(), name);
    }

    public JavaElement getRoot() {
        return root;
    }

    public StringBinaryTree getHierarchyTree() {
        return hierarchyTree;
    }

    public boolean containsElement(String name) {
        return searchInTree(hierarchyTree, name);
    }

    private boolean searchInTree(StringBinaryTree tree, String name) {
        if (tree == null || tree.isEmpty()) {
            return false;
        }

        if (tree.getRoot().equals(name)) {
            return true;
        }

        return searchInTree(tree.getLeft(), name) || searchInTree(tree.getRight(), name);
    }
}
