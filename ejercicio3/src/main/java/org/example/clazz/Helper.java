package org.example.clazz;

import java.util.*;

/**
 * Implementación de utilidades para validar y manipular jerarquías de clases.
 */
public class Helper implements IHelper {

    @Override
    public boolean isValidClassTree(IClazz[] classes) {
        if (classes == null || classes.length == 0) {
            return true;
        }

        // Verificar que no hay ciclos
        for (int i = 0; i < classes.length; i++) {
            if (hasCycle(classes[i])) {
                return false;
            }
        }

        // Verificar que hay exactamente una raíz
        int rootCount = 0;
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].getParent() == null) {
                rootCount++;
            }
        }

        return rootCount == 1;
    }

    @Override
    public IClazz findRoot(IClazz[] classes) {
        if (classes == null || classes.length == 0) {
            return null;
        }

        for (int i = 0; i < classes.length; i++) {
            if (classes[i].getParent() == null) {
                return classes[i];
            }
        }
        return null;
    }

    @Override
    public boolean hasCycle(IClazz clazz) {
        if (clazz == null) {
            return false;
        }

        DynamicArray visited = new DynamicArray();
        return hasCycleRecursive(clazz, visited);
    }

    /**
     * Método auxiliar recursivo para detectar ciclos.
     */
    private boolean hasCycleRecursive(IClazz clazz, DynamicArray visited) {
        if (visited.contains(clazz)) {
            return true;
        }

        visited.add(clazz);

        IClazz[] children = clazz.getChildren();
        for (int i = 0; i < children.length; i++) {
            DynamicArray newVisited = new DynamicArray();
            for (int j = 0; j < visited.size(); j++) {
                newVisited.add(visited.get(j));
            }
            if (hasCycleRecursive(children[i], newVisited)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IClazz[] getAncestors(IClazz clazz) {
        DynamicArray ancestors = new DynamicArray();
        if (clazz == null) {
            return ancestors.toArray();
        }

        IClazz current = clazz.getParent();
        DynamicArray visited = new DynamicArray();

        while (current != null && !visited.contains(current)) {
            visited.add(current);
            ancestors.add(current);
            current = current.getParent();
        }

        return ancestors.toArray();
    }

    @Override
    public IClazz[] getDescendants(IClazz clazz) {
        DynamicArray descendants = new DynamicArray();
        if (clazz == null) {
            return descendants.toArray();
        }

        DynamicArray visited = new DynamicArray();
        getDescendantsRecursive(clazz, descendants, visited);
        return descendants.toArray();
    }

    /**
     * Método auxiliar recursivo para obtener descendientes.
     */
    private void getDescendantsRecursive(IClazz clazz, DynamicArray descendants, DynamicArray visited) {
        if (visited.contains(clazz)) {
            return;
        }

        visited.add(clazz);

        IClazz[] children = clazz.getChildren();
        for (int i = 0; i < children.length; i++) {
            descendants.add(children[i]);
            getDescendantsRecursive(children[i], descendants, visited);
        }
    }

    /**
     * Método auxiliar para obtener nombres de un arreglo de clases.
     */
    public String[] getNames(IClazz[] classes) {
        String[] names = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            names[i] = classes[i].getName();
        }
        return names;
    }

    /**
     * Método auxiliar para imprimir un arreglo de strings.
     */
    public void printStringArray(String[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}