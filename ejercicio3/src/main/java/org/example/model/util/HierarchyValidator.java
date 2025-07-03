package org.example.model.util;

import org.example.model.interfaces.ClassHierarchy;
import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.ClazzList;
import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.InterfaceList;
import org.example.model.DynamicInterfaceList;

public class HierarchyValidator {

    private HierarchyValidator() {
        // Helper estático
    }

    public static boolean isHierarchyValid(ClassHierarchy hierarchy) {
        Clazz root = hierarchy.getRootClass();
        ClazzList classes = hierarchy.getClasses();
        InterfaceList interfaces = hierarchy.getInterfaces();

        for (int i = 0; i < classes.size(); i++) {
            Clazz current = classes.get(i);

            // Object no debe tener padre
            if (current.equals(root) && current.getParent() != null) {
                System.out.println("❌ ERROR: Object tiene padre: " + current.getParent().getClassName());
                return false;
            }

            // Detectar ciclos clase → padre → abuelo...
            if (ClazzUtil.hasCircularInheritance(current)) {
                return false;
            }

            // Reglas para record
            if (current.isRecord() && current.getParent() != null) {
                return false;
            }
            if (current.getParent() != null && current.getParent().isRecord()) {
                return false;
            }
        }

        // Verificar que no haya ciclos entre interfaces
        for (int i = 0; i < interfaces.size(); i++) {
            InterfaceClazz current = interfaces.get(i);
            if (hasInterfaceCycle(current, new DynamicInterfaceList())) {
                System.out.println("❌ Ciclo detectado en interfaz: " + current.getInterfaceName());
                return false;
            }
        }

        return true;
    }

    private static boolean hasInterfaceCycle(InterfaceClazz current, InterfaceList visited) {
        if (visited.contains(current)) return true;

        visited.add(current);

        InterfaceList parents = current.getParentInterfaces();
        for (int i = 0; i < parents.size(); i++) {
            if (hasInterfaceCycle(parents.get(i), visited)) {
                return true;
            }
        }

        visited.remove(current);
        return false;
    }

    public static boolean canCast(String from, String to, ClassHierarchy hierarchy) {
        if (from.equals(to)) return true;

        ClazzList classes = hierarchy.getClasses();
        InterfaceList interfaces = hierarchy.getInterfaces();

        // Buscar clase origen
        Clazz fromClass = null;
        Clazz toClass = null;

        for (int i = 0; i < classes.size(); i++) {
            Clazz c = classes.get(i);
            if (c.getClassName().equals(from)) {
                fromClass = c;
            }
            if (c.getClassName().equals(to)) {
                toClass = c;
            }
        }

        if (fromClass != null && toClass != null) {
            return fromClass.isChildOf(toClass); // clase hereda de otra
        }

        // Verificar si from es clase y to es interfaz
        if (fromClass != null) {
            for (int i = 0; i < interfaces.size(); i++) {
                InterfaceClazz iface = interfaces.get(i);
                if (iface.getInterfaceName().equals(to) && iface.getImplementingClasses().contains(fromClass)) {
                    return true;
                }
            }
        }

        // Verificar si ambas son interfaces
        InterfaceClazz fromIface = null;
        InterfaceClazz toIface = null;

        for (int i = 0; i < interfaces.size(); i++) {
            InterfaceClazz iface = interfaces.get(i);
            if (iface.getInterfaceName().equals(from)) fromIface = iface;
            if (iface.getInterfaceName().equals(to)) toIface = iface;
        }

        if (fromIface != null && toIface != null) {
            return fromIface.inheritsFrom(toIface);
        }

        return false;
    }
}
