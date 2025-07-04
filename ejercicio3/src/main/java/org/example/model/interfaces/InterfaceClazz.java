package org.example.model.interfaces;

public interface InterfaceClazz {

    // Agregar una interfaz padre
    void addParentInterface(InterfaceClazz parent);

    // Agregar una clase que implementa esta interfaz
    void addImplementingClass(Clazz clazz);

    // Verificar si esta interfaz hereda de otra
    boolean inheritsFrom(InterfaceClazz other);

    // Obtener el nombre de la interfaz
    String getInterfaceName();

    // Obtener las interfaces padre
    InterfaceList getParentInterfaces();

    // Obtener las clases que implementan esta interfaz
    ClazzList getImplementingClasses();

    // Verificar igualdad
    boolean equals(Object obj);
}