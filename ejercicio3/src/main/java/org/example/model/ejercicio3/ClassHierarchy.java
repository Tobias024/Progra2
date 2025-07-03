package org.example.model.ejercicio3;

import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.ClazzList;
import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;

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