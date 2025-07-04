package org.example.model;

import org.example.model.interfaces.Set;
import org.example.model.interfaces.SetOfSets;
import org.example.model.util.SetUtil;

import java.util.Random;

public class StaticSetOfSets implements SetOfSets {

    private static final int MAX_SIZE = 10000;
    private final Set[] array;
    private final Random random;
    private int count;

    public StaticSetOfSets() {
        this.array = new Set[MAX_SIZE];
        this.count = 0;
        this.random = new Random();
    }

    @Override
    public void add(Set a) {
        for (int i = 0; i < count; i++) {
            if (SetUtil.equals(array[i], a)) {
                return;
            }
        }
        array[count] = a;
        count++;
    }

    @Override
    public void remove(Set a) {
        for (int i = 0; i < count; i++) {
            if (SetUtil.equals(array[i], a)) {
                array[i] = array[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public Set choose() {
        if (this.isEmpty()) {
            throw new RuntimeException("No se puede elegir de un conjunto vacío");
        }
        int randomIndex = random.nextInt(count);
        return this.array[randomIndex];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
