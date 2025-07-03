package org.example.clazz;

import java.util.Set;

/**
 * Interfaz que define el comportamiento de una clase Java en una jerarquía de herencia.
 * Representa un TDA (Tipo de Dato Abstracto) para manejar clases con relaciones de herencia.
 */
public interface IClazz {

    /**
     * Obtiene el nombre de la clase.
     * @return el nombre de la clase
     */
    String getName();

    /**
     * Obtiene la clase padre de esta clase.
     * @return la clase padre, o null si es la clase raíz
     */
    IClazz getParent();

    /**
     * Obtiene el conjunto de clases hijas directas.
     * @return conjunto inmutable de clases hijas
     */
    Set<IClazz> getChildren();

    /**
     * Agrega una clase hija a esta clase.
     * @param child la clase hija a agregar
     */
    void addChild(IClazz child);

    /**
     * Verifica si esta clase es hija (directa o indirecta) de otra clase.
     * @param other la clase a verificar si es ancestro
     * @return true si esta clase hereda de la clase especificada
     */
    boolean isChildOf(IClazz other);

    /**
     * Verifica si esta clase es padre (directo o indirecto) de otra clase.
     * @param other la clase a verificar si es descendiente
     * @return true si la clase especificada hereda de esta clase
     */
    boolean isParentOf(IClazz other);

    /**
     * Obtiene la profundidad de esta clase en la jerarquía.
     * @return la profundidad (0 para la raíz)
     */
    int getDepth();
}