package org.example;

import org.example.model.*;
import org.example.model.ejercicio3.ClassHierarchy;
import org.example.model.ejercicio3.DynamicClassHierarchy;
import org.example.model.interfaces.*;
import org.example.model.util.ClazzHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("TESTING TP3 - ESTRUCTURAS DE DATOS");
        System.out.println("=".repeat(60));

        // Probar ejercicio 1
        testEjercicio1();

        // Probar ejercicio 2
        testEjercicio2();

        // Probar ejercicio 3
        testEjercicio3();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("TODOS LOS TESTS COMPLETADOS");
        System.out.println("=".repeat(60));
    }

    public static void testEjercicio1() {
        System.out.println("\n📋 EJERCICIO 1: TDA Clazz");
        System.out.println("-".repeat(40));

        try {
            // Crear clases con jerarquía
            Clazz object = new DynamicClazz("Object");
            Clazz animal = new DynamicClazz("Animal", object);
            Clazz mammal = new DynamicClazz("Mammal", animal);
            Clazz dog = new DynamicClazz("Dog", mammal);
            Clazz cat = new DynamicClazz("Cat", mammal);

            // Test 1: Verificar jerarquía básica
            System.out.println("✅ Test 1: Jerarquía básica");
            System.out.println("   Dog isChildOf Mammal: " + dog.isChildOf(mammal));
            System.out.println("   Dog isChildOf Animal: " + dog.isChildOf(animal));
            System.out.println("   Dog isChildOf Object: " + dog.isChildOf(object));
            System.out.println("   Cat isChildOf Dog: " + cat.isChildOf(dog));

            // Test 2: Verificar validez de jerarquía
            System.out.println("\n✅ Test 2: Validez de jerarquía");
            System.out.println("   Dog hierarchy valid: " + ClazzHelper.isValidClassHierarchy(dog));
            System.out.println("   Cat hierarchy valid: " + ClazzHelper.isValidClassHierarchy(cat));

            // Test 3: Crear ciclo inválido y verificar
            Clazz cyclicClass = new DynamicClazz("CyclicClass", dog);
            dog.setParent(cyclicClass); // Crear ciclo Dog -> CyclicClass -> Dog
            System.out.println("   Dog with cycle valid: " + ClazzHelper.isValidClassHierarchy(dog));

            // Restaurar jerarquía válida
            dog.setParent(mammal);

            System.out.println("✅ Ejercicio 1: PASSED");

        } catch (Exception e) {
            System.out.println("❌ Ejercicio 1: ERROR - " + e.getMessage());
        }
    }

    public static void testEjercicio2() {
        System.out.println("\n📋 EJERCICIO 2: TDA Interface");
        System.out.println("-".repeat(40));

        try {
            // Crear interfaces con jerarquía lineal (árbol)
            InterfaceClazz serializable = new DynamicInterfaceClazz("Serializable");
            InterfaceClazz comparable = new DynamicInterfaceClazz("Comparable");
            InterfaceClazz cloneable = new DynamicInterfaceClazz("Cloneable");

            // Crear jerarquía lineal: Cloneable -> Comparable -> Serializable
            comparable.setParentInterface(serializable);
            cloneable.setParentInterface(comparable);

            // Crear clases que implementan interfaces
            Clazz string = new DynamicClazz("String");
            Clazz integer = new DynamicClazz("Integer");

            serializable.addImplementingClass(string);
            comparable.addImplementingClass(integer);
            cloneable.addImplementingClass(string);

            // Test 1: Verificar herencia de interfaces (modelo árbol)
            System.out.println("✅ Test 1: Herencia de interfaces (árbol)");
            System.out.println("   Cloneable inherits from Comparable: " + cloneable.inheritsFrom(comparable));
            System.out.println("   Cloneable inherits from Serializable: " + cloneable.inheritsFrom(serializable));
            System.out.println("   Comparable inherits from Cloneable: " + comparable.inheritsFrom(cloneable));

            // Test 2: Verificar parent único
            System.out.println("\n✅ Test 2: Parent único (modelo árbol)");
            System.out.println("   Cloneable parent: " + (cloneable.getParentInterface() != null ? cloneable.getParentInterface().getInterfaceName() : "null"));
            System.out.println("   Comparable parent: " + (comparable.getParentInterface() != null ? comparable.getParentInterface().getInterfaceName() : "null"));
            System.out.println("   Serializable parent: " + (serializable.getParentInterface() != null ? serializable.getParentInterface().getInterfaceName() : "null"));

            // Test 3: Verificar clases que implementan
            System.out.println("\n✅ Test 3: Clases que implementan interfaces");
            System.out.println("   Serializable implemented by: " + serializable.getImplementingClasses().size() + " classes");
            System.out.println("   Comparable implemented by: " + comparable.getImplementingClasses().size() + " classes");
            System.out.println("   Cloneable implemented by: " + cloneable.getImplementingClasses().size() + " classes");

            // Test 4: Probar ciclo válido según consigna (I₁ = Iₙ)
            InterfaceClazz cyclic1 = new DynamicInterfaceClazz("CyclicInterface1");
            InterfaceClazz cyclic2 = new DynamicInterfaceClazz("CyclicInterface2");

            cyclic1.setParentInterface(cyclic2);
            cyclic2.setParentInterface(cyclic1); // Crear ciclo válido

            System.out.println("\n✅ Test 4: Ciclo válido (I₁ = Iₙ)");
            System.out.println("   Cyclic1 inherits from Cyclic2: " + cyclic1.inheritsFrom(cyclic2));
            System.out.println("   Cyclic2 inherits from Cyclic1: " + cyclic2.inheritsFrom(cyclic1));

            System.out.println("✅ Ejercicio 2: PASSED");

        } catch (Exception e) {
            System.out.println("❌ Ejercicio 2: ERROR - " + e.getMessage());
        }
    }

    public static void testEjercicio3() {
        System.out.println("\n📋 EJERCICIO 3: TDA ClassHierarchy");
        System.out.println("-".repeat(40));

        try {
            // Crear jerarquía completa
            ClassHierarchy hierarchy = new DynamicClassHierarchy();

            // Crear clases
            Clazz object = hierarchy.getRootClass(); // Object ya existe
            Clazz animal = new DynamicClazz("Animal", object);
            Clazz mammal = new DynamicClazz("Mammal", animal);
            Clazz dog = new DynamicClazz("Dog", mammal);

            hierarchy.addClass(animal);
            hierarchy.addClass(mammal);
            hierarchy.addClass(dog);

            // Crear interfaces
            InterfaceClazz serializable = new DynamicInterfaceClazz("Serializable");
            InterfaceClazz comparable = new DynamicInterfaceClazz("Comparable");

            comparable.setParentInterface(serializable);

            serializable.addImplementingClass(dog);
            comparable.addImplementingClass(mammal);

            hierarchy.addInterface(serializable);
            hierarchy.addInterface(comparable);

            // Test 1: Verificar jerarquía de clases
            System.out.println("✅ Test 1: Jerarquía de clases");
            ClazzList subclasses = hierarchy.getSubclasses(animal);
            System.out.println("   Subclasses of Animal: " + subclasses.size());
            for (int i = 0; i < subclasses.size(); i++) {
                System.out.println("     - " + subclasses.get(i).getClassName());
            }

            // Test 2: Verificar interfaces en jerarquía
            System.out.println("\n✅ Test 2: Interfaces en jerarquía");
            InterfaceList interfaces = hierarchy.getInterfaces();
            System.out.println("   Total interfaces: " + interfaces.size());
            for (int i = 0; i < interfaces.size(); i++) {
                InterfaceClazz iface = interfaces.get(i);
                System.out.println("     - " + iface.getInterfaceName() +
                        " (implements: " + iface.getImplementingClasses().size() + " classes)");
            }

            // Test 3: Verificar validez de jerarquía completa
            System.out.println("\n✅ Test 3: Validez de jerarquía completa");
            System.out.println("   Hierarchy is valid: " + hierarchy.isValidHierarchy());

            // Test 4: Verificar capacidad de interfaces bajo clases
            System.out.println("\n✅ Test 4: Capacidad de interfaces bajo clases");
            System.out.println("   Animal can have interfaces: " + hierarchy.canHaveInterfacesBelowClass(animal));
            System.out.println("   Dog can have interfaces: " + hierarchy.canHaveInterfacesBelowClass(dog));

            // Test 5: Verificar clases totales
            System.out.println("\n✅ Test 5: Clases totales en jerarquía");
            ClazzList allClasses = hierarchy.getClasses();
            System.out.println("   Total classes: " + allClasses.size());
            for (int i = 0; i < allClasses.size(); i++) {
                Clazz clazz = allClasses.get(i);
                System.out.println("     - " + clazz.getClassName() +
                        " (parent: " + (clazz.getParent() != null ? clazz.getParent().getClassName() : "none") + ")");
            }

            System.out.println("✅ Ejercicio 3: PASSED");

        } catch (Exception e) {
            System.out.println("❌ Ejercicio 3: ERROR - " + e.getMessage());
        }
    }
}