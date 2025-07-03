package org.example.clazz;

import java.util.Set;

/**
 * Interfaz que define utilidades para validar y manipular jerarquías de clases.
 */
public interface IHelper {

    /**
     * Verifica si un conjunto de clases forma una jerarquía válida.
     * Una jerarquía es válida si:
     * - No hay ciclos
     * - Cada clase tiene como máximo un padre
     * - Hay exactamente una raíz
     *
     * @param classes conjunto de clases a validar
     * @return true si la jerarquía es válida
     */
    boolean isValidClassTree(Set<IClazz> classes);

    /**
     * Encuentra la clase raíz en una jerarquía.
     * @param classes conjunto de clases
     * @return la clase raíz, o null si no hay una única raíz
     */
    IClazz findRoot(Set<IClazz> classes);

    /**
     * Verifica si existe un ciclo en la jerarquía empezando desde una clase.
     * @param clazz clase desde la cual verificar
     * @return true si se detecta un ciclo
     */
    boolean hasCycle(IClazz clazz);

    /**
     * Obtiene todos los ancestros de una clase.
     * @param clazz la clase
     * @return conjunto de todas las clases ancestro
     */
    Set<IClazz> getAncestors(IClazz clazz);

    /**
     * Obtiene todos los descendientes de una clase.
     * @param clazz la clase
     * @return conjunto de todas las clases descendiente
     */
    Set<IClazz> getDescendants(IClazz clazz);
}