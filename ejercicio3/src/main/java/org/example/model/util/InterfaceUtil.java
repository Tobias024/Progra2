package org.example.model.util;

import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;

public class InterfaceUtil {

    private InterfaceUtil() {
        // Utility class
    }

    // Verificar si una secuencia de interfaces es válida (I₁ ≺ I₂ ≺ ... ≺ Iₙ implica I₁ = Iₙ)
    public static boolean isValidInterfaceSequence(InterfaceList interfaces) {
        if (interfaces == null || interfaces.size() < 2) {
            return true;
        }

        InterfaceClazz first = interfaces.get(0);
        InterfaceClazz last = interfaces.get(interfaces.size() - 1);

        // Verificar que la secuencia forme una cadena válida
        for (int i = 0; i < interfaces.size() - 1; i++) {
            InterfaceClazz current = interfaces.get(i);
            InterfaceClazz next = interfaces.get(i + 1);

            // Verificar que next herede directamente de current
            if (!next.getParentInterface().equals(current)) {
                return false;
            }
        }

        // Si hay herencia transitiva, verificar que first = last
        if (last.inheritsFrom(first)) {
            return first.equals(last);
        }

        return true;
    }

    // Verificar si una interfaz tiene ciclos en su herencia
    public static boolean hasInheritanceCycle(InterfaceClazz interfaceClazz) {
        if (interfaceClazz == null) {
            return false;
        }

        InterfaceClazz current = interfaceClazz.getParentInterface();
        while (current != null) {
            if (current.equals(interfaceClazz)) {
                return true; // Encontrado un ciclo
            }
            current = current.getParentInterface();
        }
        return false;
    }
}