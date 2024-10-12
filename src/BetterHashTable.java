package CommonUtils;

import CommonUtils.Interfaces.BetterHashTableInterface;

import java.awt.*;
import java.util.Objects;

public class BetterHashTable<K, V> implements BetterHashTableInterface<K, V> {

    private final int INIT_CAPACITY = (1 << 4) + 3;//16+3

    private final double LOAD_FACTOR = 0.75;

    private final int INCREASE_FACTOR = 2;
    private final int CAPACITY_INCREMENT = 1 << 5;//32

    private static class Node<K, V> {
        final K key;
        V value;

        Node(K key, V value){ this.key = key; this.value = value; }

        //default intellij-generated tostring
        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }

        //default intellij-generated equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }
    }

    private final Node<K, V> DELETED = new Node<>(null, null);

    Node<K, V>[] table;
    private int capacity;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public BetterHashTable() {
        this.capacity = INIT_CAPACITY;
        this.table = (Node<K, V>[]) new Node[capacity];
    }

    @SuppressWarnings("unchecked")
    public BetterHashTable(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than zero");
        }
        this.table = (Node<K, V>[]) new Node[initialCapacity];
        this.capacity = initialCapacity;
    }

    private int usefulHash(K thing) {
        int hashCode = thing.hashCode();

        if (hashCode == Integer.MIN_VALUE) {
            hashCode = 0;
        } else {
            hashCode = Math.abs(hashCode);
        }

        return hashCode % capacity;
    }


    @Override
    public void insert(K key, V value) {
        if (key == null) {
            return;
        }

        int idx = usefulHash(key);
        int i = 0;

        while (table[idx] != null && i < capacity) {
            if (table[idx] != DELETED && table[idx].key.equals(key)) {
                table[idx].value = value;
                return;
            }
            i++;
            idx = (idx + i * i) % capacity;
        }

        table[idx] = new Node<>(key, value);
        size++;

        if ((double) size / capacity >= LOAD_FACTOR) {
            resize();
        }
    }

    @Override
    public void remove(K key) {
        if (key == null) {
            return;
        }

        int idx = usefulHash(key);
        int i =  0;

        while (table[idx] != null && i < capacity) {
            if (table[idx] != DELETED && table[idx].key.equals(key)) {
                table[idx] = DELETED;
                size--;
                return;
            }
            i++;
            idx = (idx + i * i) % capacity;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int idx = usefulHash(key);
        int i = 0;

        while (table[idx] != null && i < capacity) {
            if (table[idx] != DELETED && table[idx].key.equals(key)) {
                return table[idx].value;
            }
            i++;
            idx = (idx + i * i) % capacity;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }

        int idx = usefulHash(key);
        int i = 0;

        while (table[idx] != null && i < capacity) {
            if (table[idx] != DELETED && table[idx].key.equals(key)) {
                return true;
            }
            i++;
            idx = (idx + i * i) % capacity;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.table = (Node<K, V>[]) new Node[INIT_CAPACITY];
        this.capacity = INIT_CAPACITY;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = capacity * INCREASE_FACTOR;

        if (newCapacity <= capacity) {
            newCapacity = capacity + CAPACITY_INCREMENT;
        }

        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Node<K, V> curr = table[i];

            if (curr != null && curr != DELETED) {
                int j = 0;
                int newIndex = (Math.abs(curr.key.hashCode())) % newCapacity;

                while (newTable[newIndex] != null) {
                    j++;
                    newIndex = (newIndex + j * j) % newCapacity;
                }
                newTable[newIndex] = curr;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the hash table how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void visualize(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: visualization is to be time-based -- how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
