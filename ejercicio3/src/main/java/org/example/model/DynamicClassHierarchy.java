package org.example.model;

import org.example.model.interfaces.*;
import org.example.model.util.ClazzUtil;
import org.example.model.util.InterfaceUtil;

/**
 * Dynamic implementation of ClassHierarchy.
 * Corrected to avoid assigning root as parent for records.
 */
public class DynamicClassHierarchy implements ClassHierarchy {

    private Clazz rootClass; // Represents Object
    private InterfaceList interfaces;
    private ClazzList classes;

    public DynamicClassHierarchy() {
        this.rootClass = new DynamicClazz("Object");
        this.interfaces = new DynamicInterfaceList();
        this.classes = new DynamicClazzList();
        this.classes.add(rootClass);
    }

    @Override
    public void addClass(Clazz clazz) {
        if (clazz == null || classes.contains(clazz)) {
            return;
        }

        // === Validations for record ===
        if (clazz.isRecord() && clazz.getParent() != null) {
            throw new RuntimeException("Un record no puede heredar de una clase.");
        }
        if (clazz.getParent() != null && clazz.getParent().isRecord()) {
            throw new RuntimeException("Una clase no puede heredar de un record.");
        }

        // Only non-record classes get Object as parent if none assigned
        if (!clazz.isRecord() && clazz.getParent() == null && !clazz.equals(rootClass)) {
            clazz.setParent(rootClass);
        }

        classes.add(clazz);
    }

    @Override
    public void addInterface(InterfaceClazz interfaze) {
        if (interfaze != null && !interfaces.contains(interfaze)) {
            interfaces.add(interfaze);
        }
    }

    @Override
    public boolean canHaveInterfacesBelowClass(Clazz clazz) {
        return clazz != null;
    }

    @Override
    public ClazzList getSubclasses(Clazz parent) {
        ClazzList subclasses = new DynamicClazzList();
        for (int i = 0; i < classes.size(); i++) {
            Clazz c = classes.get(i);
            if (c.isChildOf(parent)) {
                subclasses.add(c);
            }
        }
        return subclasses;
    }

    @Override
    public boolean isValidHierarchy() {
        // Let ClassHierarchy.isValidHierarchy handle core validation
        for (int i = 0; i < classes.size(); i++) {
            Clazz c = classes.get(i);
            if (!ClazzUtil.isValidClassHierarchy(c)) {
                return false;
            }
        }
        for (int i = 0; i < interfaces.size(); i++) {
            InterfaceClazz ic = interfaces.get(i);
            InterfaceList chain = new DynamicInterfaceList();
            chain.add(ic);
            InterfaceList parents = ic.getParentInterfaces();
            for (int j = 0; j < parents.size(); j++) {
                chain.add(parents.get(j));
            }
            if (!InterfaceUtil.isValidInterfaceSequence(chain)) {
                return false;
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
        InterfaceList copy = new DynamicInterfaceList();
        for (int i = 0; i < interfaces.size(); i++) {
            copy.add(interfaces.get(i));
        }
        return copy;
    }

    @Override
    public ClazzList getClasses() {
        ClazzList copy = new DynamicClazzList();
        for (int i = 0; i < classes.size(); i++) {
            copy.add(classes.get(i));
        }
        return copy;
    }
}
