package org.example;

import org.example.model.ejercicio3.*;

public class Main {
    public static void main(String[] args) {
        /* System.out.println("=".repeat(60));
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

         */

        System.out.println("=== Test Jerarquía JavaClass con raíz Object ===\n");

        // Crear raíz: Object (JavaClass especial sin padre)
        JavaClass object = new JavaClass("Object");

        // Validar que Object no tiene padre (en tu estructura no se ve padre explícito, se maneja por jerarquía de hijos)
        System.out.println("1. Creación de raíz Object");
        System.out.println("Raíz creada: " + object.getName());

        // Crear otras clases/interfaces
        JavaClass animal = new JavaClass("Animal");
        AbstractClass mamifero = new AbstractClass("Mamifero");
        JavaClass perro = new JavaClass("Perro");
        JavaClass gato = new JavaClass("Gato");
        JavaInterface runnable = new JavaInterface("Runnable");
        JavaInterface serializable = new JavaInterface("Serializable");

        // 2. Construir jerarquía válida
        System.out.println("\n2. Construcción jerarquía:");

        // Agregar hijos válidos a Object: Animal (clase), Runnable (interfaz)
        boolean addAnimal = object.addChild(animal);
        System.out.println(" - Animal agregado a Object: " + addAnimal);

        boolean addRunnable = object.addChild(runnable);
        System.out.println(" - Runnable (interfaz) agregado a Object: " + addRunnable);

        // Animal puede tener abstractas y clases hijas
        boolean addMamifero = animal.addChild(mamifero);
        System.out.println(" - Mamifero (abstracta) agregado a Animal: " + addMamifero);

        boolean addPerro = mamifero.addChild(perro);
        System.out.println(" - Perro agregado a Mamifero: " + addPerro);

        boolean addGato = mamifero.addChild(gato);
        System.out.println(" - Gato agregado a Mamifero: " + addGato);

        // Runnable puede tener interfaces hijas
        JavaInterface subRunnable = new JavaInterface("SubRunnable");
        boolean addSubRunnable = runnable.addChild(subRunnable);
        System.out.println(" - SubRunnable agregado a Runnable: " + addSubRunnable);

        // 3. Validar reglas inválidas

        System.out.println("\n3. Intentar agregar interfaz como hija de clase concreta (debería ser falso):");
        boolean addSerializableToPerro = perro.addChild(serializable);
        System.out.println(" - Serializable agregado a Perro: " + addSerializableToPerro);

        System.out.println("\n4. Intentar agregar clase concreta como hija de interfaz (debería ser true):");
        boolean addPerroToRunnable = runnable.addChild(perro);
        System.out.println(" - Perro agregado a Runnable: " + addPerroToRunnable);

        System.out.println("\n5. Validar que Object no puede tener padre (no hay método para padre, se asume raíz):");
        // No hay padre asignado explícitamente ni método, asumimos que root no tiene padre.

        // 6. Mostrar jerarquía (solo nombres, con indentación)
        System.out.println("\n6. Jerarquía completa:");
        printHierarchy(object, 0);

        // 7. Validar que no hay ciclos: aquí deberías implementar o llamar tu validador
        // No diste método para esto, pero se puede implementar si querés
        System.out.println("\n7. Validar ciclos - NO IMPLEMENTADO: Debes agregar método para detectar ciclos");

        System.out.println("\n=== Fin de test ===");
    }

    // Método auxiliar para imprimir jerarquía recursiva (usando StringBinaryTree)
    private static void printHierarchy(JavaElement element, int level) {
        if (element == null) return;
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("- " + element.getName());

        // Recorrer hijos
        StringBinaryTree children = element.getChildren();
        if (children != null && !children.isEmpty()) {
            printChildren(children, level + 1);
        }
    }

    private static void printChildren(StringBinaryTree tree, int level) {
        if (tree == null || tree.isEmpty()) return;

        // Buscar el JavaElement con ese nombre para imprimir (asumiendo que se puede buscar)
        JavaElement child = findElementByName(tree.getRoot());
        if (child != null) {
            printHierarchy(child, level);
        }

        printChildren(tree.getLeft(), level);
        printChildren(tree.getRight(), level);
    }

    // Método dummy para buscar elemento por nombre (deberías implementar un mapa o registro para buscar)
    private static JavaElement findElementByName(String name) {
        // Como no tenés estructura global, este método no puede buscar realmente.
        // En implementación real deberías tener registro global o pasar contexto.
        // Acá solo para que compile, devuelve null
        return null;
    }
}

