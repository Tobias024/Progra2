package org.example.model;

import org.example.model.interfaces.*;
import org.example.model.util.ClazzHelper;
import org.example.model.util.InterfaceUtil;

public class DynamicClassHierarchy implements ClassHierarchy {

    private Clazz rootClass; // Representa Object en Java
    private InterfaceList interfaces;
    private ClazzList classes;

    public DynamicClassHierarchy() {
        this.rootClass = new DynamicClazz("Object"); // Object es la raíz
        this.interfaces = new DynamicInterfaceList();
        this.classes = new DynamicClazzList();
        this.classes.add(rootClass);
    }

    @Override
    public void addClass(Clazz clazz) {
        if (clazz != null && !classes.contains(clazz)) {
            // Si no tiene padre, por defecto hereda de Object
            if (clazz.getParent() == null && !clazz.equals(rootClass)) {
                clazz.setParent(rootClass);
            }
            classes.add(clazz);
        }
    }

    @Override
    public void addInterface(InterfaceClazz interfaze) {
        if (interfaze != null && !interfaces.contains(interfaze)) {
            interfaces.add(interfaze);
        }
    }

    @Override
    public boolean canHaveInterfacesBelowClass(Clazz clazz) {
        if (clazz == null) {
            return false;
        }

        // En Java, las clases pueden implementar interfaces,
        // pero las interfaces no pueden estar "debajo" de clases en la jerarquía
        // Una clase solo puede tener otras clases como hijas
        return true; // Las clases pueden tener interfaces implementadas
    }

    @Override
    public ClazzList getSubclasses(Clazz parent) {
        ClazzList subclasses = new DynamicClazzList();

        for (int i = 0; i < classes.size(); i++) {
            Clazz clazz = classes.get(i);
            if (clazz.isChildOf(parent)) {
                subclasses.add(clazz);
            }
        }

        return subclasses;
    }

    @Override
    public boolean isValidHierarchy() {
        // Verificar que todas las clases tengan jerarquías válidas
        for (int i = 0; i < classes.size(); i++) {
            Clazz clazz = classes.get(i);
            if (!ClazzHelper.isValidClassHierarchy(clazz)) {
                return false;
            }
        }

        // Verificar que todas las interfaces tengan relaciones válidas
        // Ahora solo verificamos que no haya ciclos infinitos
        for (int i = 0; i < interfaces.size(); i++) {
            InterfaceClazz interfaze = interfaces.get(i);
            if (InterfaceUtil.hasInheritanceCycle(interfaze)) {
                // Solo es inválido si el ciclo no termina en sí mismo
                InterfaceClazz current = interfaze.getParentInterface();
                boolean validCycle = false;

                while (current != null && !current.equals(interfaze)) {
                    current = current.getParentInterface();
                }

                if (current != null && current.equals(interfaze)) {
                    validCycle = true; // Es un ciclo válido según la consigna
                }

                if (!validCycle) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Clazz getRootClass() {
        return rootClass;
    }

    @Override
    public InterfaceList getInterfaces() {
        // Devolver una copia para evitar modificaciones externas
        InterfaceList copy = new DynamicInterfaceList();
        for (int i = 0; i < interfaces.size(); i++) {
            copy.add(interfaces.get(i));
        }
        return copy;
    }

    @Override
    public ClazzList getClasses() {
        // Devolver una copia para evitar modificaciones externas
        ClazzList copy = new DynamicClazzList();
        for (int i = 0; i < classes.size(); i++) {
            copy.add(classes.get(i));
        }
        return copy;
    }
}