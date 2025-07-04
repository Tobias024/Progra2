package org.example.model.util;

import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.Set;
import org.example.model.StaticSet;

public class ClazzUtil {

    private ClazzUtil() {
        // Utility class
    }

    // Verificar si el árbol de clases es válido (sin ciclos)
    public static boolean isValidClassHierarchy(Clazz clazz) {
        if (clazz == null) {
            return true;
        }

        Set visitedClasses = new StaticSet();
        return isValidHierarchyRecursive(clazz, visitedClasses);
    }

    private static boolean isValidHierarchyRecursive(Clazz current, Set visited) {
        if (current == null) {
            return true;
        }

        // Convertir el nombre de clase a hash para usar con Set de enteros
        int classHash = current.getClassName().hashCode();

        if (SetUtil.in(classHash, visited)) {
            return false; // Ciclo detectado
        }

        visited.add(classHash);
        boolean result = isValidHierarchyRecursive(current.getParent(), visited);
        visited.remove(classHash);

        return result;
    }

    // Verificar que no haya herencia circular (C ≺ C)
    public static boolean hasCircularInheritance(Clazz clazz) {
        return clazz != null && clazz.isChildOf(clazz);
    }
}