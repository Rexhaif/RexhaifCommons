package rexhaif.commons.utils;

import java.util.*;
import java.util.function.Function;

public class BloomFilter<T> {

    private BitSet bits;

    private int m;

    private int k;

    private List<Function<T, Integer>> hashingFunctions;

    /**
     * Constructs BloomFilter with given m and only one hash function Object::hashCode
     * @param m number of bits in bit array
     */
    public BloomFilter(int m) {

        this.m = m;
        this.bits = new BitSet(m);
        this.k = 1;
        this.hashingFunctions = Collections.unmodifiableList(Collections.singletonList(Objects::hashCode));

    }

    /**
     * Constructs BloomFilter with given m and given hash functions
     * @param m number of bits in bit array
     * @param hashingFunctions list of hash functions
     */
    public BloomFilter(int m, List<Function<T, Integer>> hashingFunctions) {
        this.m = m;
        this.bits = new BitSet(m);
        this.k = hashingFunctions.size();
        this.hashingFunctions = hashingFunctions;
    }

    /**
     * Inserts given object into filter
     * @param obj object to insert
     */
    public void add(T obj) {

        for (Function<T, Integer> hash : hashingFunctions) {
            int pos = hash.apply(obj) % m;
            bits.set(pos);
        }

    }

    /**
     * Check if given object can probably been inserted into that filter before
     * @param obj object to check
     * @return true if it is possible to that object to be inserted into filter, or false if it is impossible
     */
    public boolean contains(T obj) {

        for(Function<T, Integer> hash : hashingFunctions) {
            int pos = hash.apply(obj) % m;
            if (!bits.get(pos)) return false;
        }

        return true;

    }

}
