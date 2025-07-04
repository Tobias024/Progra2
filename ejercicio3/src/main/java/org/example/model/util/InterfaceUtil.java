package org.example.model.util;

import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;
import org.example.model.DynamicInterfaceList;

public class InterfaceUtil {

    private InterfaceUtil() {
        // Clase utilitaria
    }

    // Verifica que no haya ciclos de herencia entre interfaces
    public static boolean isValidInterfaceSequence(InterfaceList interfaces) {
        if (interfaces == null || interfaces.size() == 0) return true;

        for (int i = 0; i < interfaces.size(); i++) {
            InterfaceClazz current = interfaces.get(i);
            if (hasCycle(current, new DynamicInterfaceList())) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(InterfaceClazz current, InterfaceList visited) {
        if (visited.contains(current)) return true;

        visited.add(current);

        InterfaceList parents = current.getParentInterfaces();
        for (int i = 0; i < parents.size(); i++) {
            if (hasCycle(parents.get(i), visited)) {
                return true;
            }
        }

        visited.remove(current);
        return false;
    }
}
