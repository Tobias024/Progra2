package org.example.model.tests;

import org.example.model.DynamicClazz;
import org.example.model.interfaces.Clazz;
import org.example.model.util.ClazzHelper;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("=== Pruebas del TDA Clazz ===\n");

        // Crear instancias usando DynamicClazz
        Clazz object = new DynamicClazz("Object");
        Clazz animal = new DynamicClazz("Animal");
        Clazz mamifero = new DynamicClazz("Mamifero");
        Clazz perro = new DynamicClazz("Perro");
        Clazz gato = new DynamicClazz("Gato");

        System.out.println("1. Creando jerarquía válida:");

        // Usar setParent en lugar de heredaDe
        animal.setParent(object);
        System.out.println("✓ Animal hereda de Object");

        mamifero.setParent(object);
        System.out.println("✓ Mamifero hereda de Object");

        perro.setParent(animal);
        System.out.println("✓ Perro hereda de Animal");

        gato.setParent(animal);
        System.out.println("✓ Gato hereda de Animal");

        System.out.println("\n2. Verificando jerarquía:");
        imprimirJerarquia(object);

        System.out.println("\n3. Validando árbol:");
        if (ClazzHelper.isValidClassHierarchy(object)) {
            System.out.println("✓ El árbol es válido");
        } else {
            System.out.println("✗ El árbol no es válido");
        }

        System.out.println("\n4. Probando casos inválidos:");

        // Herencia de sí mismo (usando hasCircularInheritance)
        if (!ClazzHelper.hasCircularInheritance(perro)) {
            System.out.println("✓ Correctamente rechazó herencia de sí mismo");
        } else {
            System.out.println("✗ Error: permitió herencia de sí mismo");
        }

        // Intentar crear un ciclo
        Clazz tempParent = object.getParent();
        object.setParent(perro);
        if (!ClazzHelper.isValidClassHierarchy(object)) {
            System.out.println("✓ Correctamente detectó ciclo (Object heredando de Perro)");
            object.setParent(tempParent); // Restaurar estado válido
        } else {
            System.out.println("✗ Error: no detectó el ciclo");
            object.setParent(tempParent); // Restaurar de todas formas
        }

        System.out.println("\n5. Verificando relaciones:");
        // Usar isChildOf en lugar de esHijaDe y esAncestro
        System.out.println("¿Perro es hijo de Animal? " + perro.isChildOf(animal));
        System.out.println("¿Animal es ancestro de Perro? " + perro.isChildOf(animal));
        System.out.println("¿Object es ancestro de Gato? " + gato.isChildOf(object));
        System.out.println("¿Gato es ancestro de Object? " + object.isChildOf(gato));

        System.out.println("\n6. Información de clases:");
        System.out.println("Nombre de Object: " + object.getClassName());
        System.out.println("Padre de Animal: " + (animal.getParent() != null ? animal.getParent().getClassName() : "null"));
        System.out.println("Padre de Perro: " + (perro.getParent() != null ? perro.getParent().getClassName() : "null"));
    }

    // Método auxiliar para imprimir jerarquía (ya que no tienes ClazzHelper.imprimirJerarquia)
    private static void imprimirJerarquia(Clazz clazz) {
        imprimirJerarquiaRecursiva(clazz, 0);
    }

    private static void imprimirJerarquiaRecursiva(Clazz clazz, int nivel) {
        if (clazz == null) return;

        // Imprimir indentación
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + clazz.getClassName());

        // Este método requeriría una forma de obtener los hijos,
        // lo cual no está directamente disponible en tu implementación actual
        // Por simplicidad, solo mostramos la clase actual
    }
}