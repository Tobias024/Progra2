package org.example;

import org.example.model.DynamicClazz;
import org.example.model.DynamicInterfaceClazz;
import org.example.model.DynamicClassHierarchy;
import org.example.model.util.HierarchyValidator;
import org.example.model.interfaces.Clazz;
import org.example.model.interfaces.InterfaceClazz;
import org.example.model.interfaces.ClassHierarchy;

public class Main {
    public static void main(String[] args) {
        boolean allPassed = true;
        System.out.println("====== VERIFICACIÓN COMPLETA DE EJERCICIOS 1-6 ======");

        // Ejercicio 1: Clases
        Clazz a1 = new DynamicClazz("A1");
        Clazz b1 = new DynamicClazz("B1");
        Clazz c1 = new DynamicClazz("C1");
        b1.setParent(a1);
        c1.setParent(b1);
        boolean test11 = b1.isChildOf(a1);
        boolean test12 = c1.isChildOf(a1);
        boolean test13 = !a1.isChildOf(c1);
        System.out.println("Ej1.1 (B1 ≺ A1): " + (test11 ? "PASS" : "FAIL"));
        System.out.println("Ej1.2 (C1 ≺ A1): " + (test12 ? "PASS" : "FAIL"));
        System.out.println("Ej1.3 (A1 !≺ C1): " + (test13 ? "PASS" : "FAIL"));
        allPassed &= test11 && test12 && test13;

        // Ejercicio 2: Interfaces
        InterfaceClazz i1 = new DynamicInterfaceClazz("I1");
        InterfaceClazz i2 = new DynamicInterfaceClazz("I2");
        InterfaceClazz i3 = new DynamicInterfaceClazz("I3");
        i2.addParentInterface(i1);
        i3.addParentInterface(i2);
        i1.addImplementingClass(a1);
        boolean test21 = i3.inheritsFrom(i1);
        boolean test22 = i2.inheritsFrom(i1);
        System.out.println("Ej2.1 (I3 ≺ I1): " + (test21 ? "PASS" : "FAIL"));
        System.out.println("Ej2.2 (I2 ≺ I1): " + (test22 ? "PASS" : "FAIL"));
        allPassed &= test21 && test22;

        // Ejercicio 3: ClassHierarchy
        ClassHierarchy hierarchy = new DynamicClassHierarchy();
        Clazz x = new DynamicClazz("X");
        Clazz y = new DynamicClazz("Y");
        y.setParent(x);
        hierarchy.addClass(a1);
        hierarchy.addClass(b1);
        hierarchy.addClass(c1);
        hierarchy.addClass(x);
        hierarchy.addClass(y);
        hierarchy.addInterface(i1);
        hierarchy.addInterface(i2);
        hierarchy.addInterface(i3);
        boolean test3 = hierarchy.isValidHierarchy();
        System.out.println("Ej3 (jerarquía sin ciclos y raíz Object): " + (test3 ? "PASS" : "FAIL"));
        allPassed &= test3;

        // Ejercicio 4: Record
        Clazz recordR = new DynamicClazz("R1", true);
        hierarchy.addClass(recordR);
        boolean pass41 = false;
        try {
            Clazz z = new DynamicClazz("Z");
            z.setParent(recordR);
            hierarchy.addClass(z);
        } catch (RuntimeException e) {
            pass41 = true;
        }
        boolean pass42 = false;
        try {
            Clazz r2 = new DynamicClazz("R2", true);
            r2.setParent(a1);
            hierarchy.addClass(r2);
        } catch (RuntimeException e) {
            pass42 = true;
        }
        System.out.println("Ej4.1 (Clase ≺ record fallo): " + (pass41 ? "PASS" : "FAIL"));
        System.out.println("Ej4.2 (Record ≺ Clase fallo): " + (pass42 ? "PASS" : "FAIL"));
        allPassed &= pass41 && pass42;

        // Ejercicio 5: Validación global
        boolean test5 = HierarchyValidator.isHierarchyValid(hierarchy);
        System.out.println("Ej5 (validación global jerarquía): " + (test5 ? "PASS" : "FAIL"));
        allPassed &= test5;

        // Ejercicio 6: canCast
        boolean test61 = HierarchyValidator.canCast("C1", "A1", hierarchy);
        boolean test62 = HierarchyValidator.canCast("B1", "A1", hierarchy);
        boolean test63 = !HierarchyValidator.canCast("A1", "C1", hierarchy);
        boolean test64 = HierarchyValidator.canCast("A1", "I1", hierarchy);
        boolean test65 = HierarchyValidator.canCast("I3", "I1", hierarchy);
        boolean test66 = !HierarchyValidator.canCast("I1", "I3", hierarchy);
        boolean test67 = !HierarchyValidator.canCast("R1", "I1", hierarchy);
        System.out.println("Ej6.1 (C1→A1): " + (test61 ? "PASS" : "FAIL"));
        System.out.println("Ej6.2 (B1→A1): " + (test62 ? "PASS" : "FAIL"));
        System.out.println("Ej6.3 (A1!→C1): " + (test63 ? "PASS" : "FAIL"));
        System.out.println("Ej6.4 (A1→I1): " + (test64 ? "PASS" : "FAIL"));
        System.out.println("Ej6.5 (I3→I1): " + (test65 ? "PASS" : "FAIL"));
        System.out.println("Ej6.6 (I1!→I3): " + (test66 ? "PASS" : "FAIL"));
        System.out.println("Ej6.7 (R1!→I1): " + (test67 ? "PASS" : "FAIL"));
        allPassed &= test61 && test62 && test63 && test64 && test65 && test66 && test67;

        System.out.println(allPassed ? "\n== TODOS LOS TESTS PASARON ==" : "\n== HAY TESTS FALLIDOS ==");
    }
}
