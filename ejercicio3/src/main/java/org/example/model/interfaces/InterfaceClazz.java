package org.example.model.interfaces;

public interface InterfaceClazz {

    // Establecer la interfaz padre (solo una)
    void setParentInterface(InterfaceClazz parent);

    // Agregar una clase que implementa esta interfaz
    void addImplementingClass(Clazz clazz);

    // Verificar si esta interfaz hereda de otra
    boolean inheritsFrom(InterfaceClazz other);

    // Obtener el nombre de la interfaz
    String getInterfaceName();

    // Obtener la interfaz padre (solo una)
    InterfaceClazz getParentInterface();

    // Obtener las clases que implementan esta interfaz
    ClazzList getImplementingClasses();

    // Verificar igualdad
    boolean equals(Object obj);
}