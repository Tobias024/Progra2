package org.example.model.interfaces;

public interface Clazz {

    // Verificar si esta clase es hija de otra
    boolean isChildOf(Clazz other);

    // Obtener el nombre de la clase
    String getClassName();

    // Obtener la clase padre
    Clazz getParent();

    // Establecer la clase padre
    void setParent(Clazz parent);

    // Verificar igualdad por nombre de clase
    boolean equals(Object obj);
}