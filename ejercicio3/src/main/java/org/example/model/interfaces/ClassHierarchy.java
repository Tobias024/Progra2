package org.example.model.interfaces;

public interface ClassHierarchy {

    // Agregar una nueva clase a la jerarquía
    void addClass(Clazz clazz);

    // Agregar una nueva interfaz a la jerarquía
    void addInterface(InterfaceClazz interfaze);

    // Verificar si una clase puede tener interfaces bajo su jerarquía
    boolean canHaveInterfacesBelowClass(Clazz clazz);

    // Obtener todas las clases que heredan de una clase dada
    ClazzList getSubclasses(Clazz parent);

    // Verificar si la jerarquía es válida
    boolean isValidHierarchy();

    // Obtener la clase raíz (Object)
    Clazz getRootClass();

    // Obtener todas las interfaces
    InterfaceList getInterfaces();

    // Obtener todas las clases
    ClazzList getClasses();
}