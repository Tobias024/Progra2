package org.example.model.ejercicio3;

// Para P(0): Clases abstractas
public class AbstractClass extends JavaClass {

    public AbstractClass(String className) {
        super(className);
    }

    @Override
    public boolean canHaveAbstractClassChild() {
        return true; // Las clases abstractas pueden tener hijas abstractas
    }

    @Override
    public boolean canHaveClassChild() {
        return true; // Las clases abstractas pueden tener hijas concretas
    }

    public boolean canImplementInterface() {
        return true;
    }
}
