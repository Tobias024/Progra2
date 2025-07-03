package org.example.model.util;

import org.example.model.interfaces.Set;
import org.example.model.interfaces.SetOfSets;
import org.example.model.StaticSet;
import org.example.model.StaticSetOfSets;

public class SetUtil {

    public static Set copy(Set set) {
        Set result = new StaticSet();
        Set aux = new StaticSet();

        while (!set.isEmpty()) {
            int chosen = set.choose();
            result.add(chosen);
            aux.add(chosen);
            set.remove(chosen);
        }

        while (!aux.isEmpty()) {
            int chosen = aux.choose();
            set.add(chosen);
            aux.remove(chosen);
        }

        return result;
    }

    public static boolean in(int a, Set set) {
        Set copy = copy(set);
        while (!copy.isEmpty()) {
            int chosen = copy.choose();
            if (chosen == a) {
                return true;
            }
            copy.remove(chosen);
        }
        return false;
    }

    public static boolean subsetEq(Set set, Set set2) {
        Set copy = copy(set);

        while (!copy.isEmpty()) {
            int chosen = copy.choose();
            if (!in(chosen, set2)) {
                return false;
            }
            copy.remove(chosen);
        }
        return true;
    }

    public static int size(Set set) {
        Set copy = copy(set);

        int size = 0;
        while (!copy.isEmpty()) {
            size++;
            copy.remove(copy.choose());
        }
        return size;
    }

    public static Set intersection(Set set, Set set2) {
        Set copy = copy(set);
        Set result = new StaticSet();

        while (!copy.isEmpty()) {
            int chosen = copy.choose();
            if (in(chosen, set2)) {
                result.add(chosen);
            }
            copy.remove(chosen);
        }
        return result;
    }

    public static Set union(Set set, Set set2) {
        Set copy = copy(set);
        Set copy2 = copy(set2);

        while (!copy2.isEmpty()) {
            int chosen = copy2.choose();
            copy.add(chosen);
            copy2.remove(chosen);
        }
        return copy;
    }

    public static Set difference(Set set, Set set2) {
        Set copy = copy(set);
        Set result = new StaticSet();

        while (!copy.isEmpty()) {
            int chosen = copy.choose();
            if (!in(chosen, set2)) {
                result.add(chosen);
            }
            copy.remove(chosen);
        }
        return result;
    }

    public static Set symmetricDifference(Set set, Set set2) {
        return union(difference(set, set2), difference(set2, set));
    }

    public static boolean equals(Set set, Set set2) {
        return subsetEq(set, set2) && subsetEq(set2, set2);
    }

    /*
        for(a;b;c) {d} -> O(a) + O(x) * (O(b) + O(c) + O(d)
                       -> O(C) + O(2^N) * (O(C) + O(C) + O(N)) = O(N2^N)
     */
    public static SetOfSets parts(Set set) {
        int size = size(set);
        double limit = Math.pow(2, size);
        int[] elements = map(set, size); // O(N^2)
        SetOfSets result = new StaticSetOfSets();

        for (int i = 0; i < limit; i++) {
            boolean[] bin = intToBin(i, size);
            Set set2 = part(elements, bin);
            result.add(set2);
        }

        return result;
    }

    private static Set part(int[] elements, boolean[] bin) {
        Set result = new StaticSet();
        for (int i = 0; i < bin.length; i++) {
            if (bin[i]) {
                result.add(elements[i]);
            }
        }
        return result;
    }

    private static int[] map(Set set, int size) {
        Set copy = copy(set);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int chosen = copy.choose();
            result[i] = chosen;
            copy.remove(chosen);
        }
        return result;
    }

    /*

    if a then b else c -> O(a) + max{O(b), O(c)}
                          O(C) + max{O(C), O(N)}
                          O(C) + O(N)
                          O(N)

    Genralmente       O(C) + O(N)
    for(a;b;c) {d} -> O(a) + O(x) * (O(b) + O(c) + O(d)
                   -> O(C) + O(N) * (O(C)) = O(N)
     */

    private static boolean[] intToBin(int n, int len) {
        if (n < 0 || n < Math.pow(2, len)) {
            throw new RuntimeException("n está fuera de rango");
        } else {
            boolean[] result = new boolean[len];
            int a = n;


            for (int i = 0; a != 0 && i < len; i++) {
                result[i] = a % 2 == 1;
                a /= 2;
            }

            return result;
        }
    }


}
