package org.example.model.interfaces;

public interface Clazz {

    boolean isChildOf(Clazz other);

    String getClassName();

    Clazz getParent();

    void setParent(Clazz parent);

    boolean equals(Object obj);

    // === NUEVO: Soporte para record ===
    boolean isRecord();
    void setIsRecord(boolean isRecord);
}
