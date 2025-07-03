package org.example.clazz;

import java.util.*;

/**
 * Implementación de utilidades para validar y manipular jerarquías de clases.
 */
public class Helper implements IHelper {

    @Override
    public boolean isValidClassTree(Set<IClazz> classes) {
        if (classes == null || classes.isEmpty()) {
            return true;
        }

        // Verificar que no hay ciclos
        for (IClazz clazz : classes) {
            if (hasCycle(clazz)) {
                return false;
            }
        }

        // Verificar que hay exactamente una raíz
        List<IClazz> roots = new ArrayList<>();
        for (IClazz clazz : classes) {
            if (clazz.getParent() == null) {
                roots.add(clazz);
            }
        }

        return roots.size() == 1;
    }

    @Override
    public IClazz findRoot(Set<IClazz> classes) {
        if (classes == null || classes.isEmpty()) {
            return null;
        }

        for (IClazz clazz : classes) {
            if (clazz.getParent() == null) {
                return clazz;
            }
        }
        return null;
    }

    @Override
    public boolean hasCycle(IClazz clazz) {
        if (clazz == null) {
            return false;
        }

        Set<IClazz> visited = new HashSet<>();
        return hasCycleRecursive(clazz, visited);
    }

    /**
     * Método auxiliar recursivo para detectar ciclos.
     */
    private boolean hasCycleRecursive(IClazz clazz, Set<IClazz> visited) {
        if (visited.contains(clazz)) {
            return true;
        }

        visited.add(clazz);

        for (IClazz child : clazz.getChildren()) {
            if (hasCycleRecursive(child, new HashSet<>(visited))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Set<IClazz> getAncestors(IClazz clazz) {
        Set<IClazz> ancestors = new HashSet<>();
        if (clazz == null) {
            return ancestors;
        }

        IClazz current = clazz.getParent();
        Set<IClazz> visited = new HashSet<>();

        while (current != null && !visited.contains(current)) {
            visited.add(current);
            ancestors.add(current);
            current = current.getParent();
        }

        return ancestors;
    }

    @Override
    public Set<IClazz> getDescendants(IClazz clazz) {
        Set<IClazz> descendants = new HashSet<>();
        if (clazz == null) {
            return descendants;
        }

        getDescendantsRecursive(clazz, descendants, new HashSet<>());
        return descendants;
    }

    /**
     * Método auxiliar recursivo para obtener descendientes.
     */
    private void getDescendantsRecursive(IClazz clazz, Set<IClazz> descendants, Set<IClazz> visited) {
        if (visited.contains(clazz)) {
            return;
        }

        visited.add(clazz);

        for (IClazz child : clazz.getChildren()) {
            descendants.add(child);
            getDescendantsRecursive(child, descendants, visited);
        }
    }
}