package org.example.model;

import org.example.model.interfaces.Set;

import java.util.Random;

public class StaticSet implements Set {

    private static final int MAX_SIZE = 10000;
    private final int[] array;
    private final Random random;
    private int count;

    public StaticSet() {
        this.array = new int[MAX_SIZE];
        this.count = 0;
        this.random = new Random();
    }

    @Override
    public void add(int a) {
        for (int i = 0; i < count; i++) {
            if (array[i] == a) {
                return;
            }
        }
        array[count] = a;
        count++;
    }

    @Override
    public void remove(int a) {
        for (int i = 0; i < count; i++) {
            if (array[i] == a) {
                array[i] = array[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public int choose() {
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
