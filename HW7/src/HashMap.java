import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.ArrayList;
/**
 * Your implementation of HashMap.
 * 
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];

    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        MapEntry<K, V> addition = new MapEntry<>(key, value);
        if (((size + 1.0) / table.length) >= MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            table[index] = addition;
            size++;
        } else {
            MapEntry<K, V> loc = table[index];
            MapEntry<K, V> iter = table[index];
            while (iter != null) {
                if (iter.getKey().equals(key)) {
                    V data = iter.getValue();
                    iter.setValue(value);
                    return data;
                }
                iter = iter.getNext();
            }
            addition.setNext(loc);
            table[index] = addition;

            size++;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            throw new NoSuchElementException("No such element was present");
        } else {
            MapEntry<K, V> previous = table[index];
            if (previous.getKey().equals(key)) {
                table[index] = previous.getNext();
                size--;
                return previous.getValue();
            }
            while (previous.getNext() != null) {
                if (previous.getNext().getKey().equals(key)) {
                    V data = previous.getNext().getValue();
                    previous.setNext(previous.getNext().getNext());
                    size--;
                    return data;
                }
                previous = previous.getNext();
            }
        }
        throw new NoSuchElementException("No such element was present");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            throw new NoSuchElementException("Element is not in map");
        } else {
            MapEntry<K, V> loc = table[index];
            while (loc != null) {
                if (loc.getKey().equals(key)) {
                    return loc.getValue();
                }
                loc = loc.getNext();
            }
        }
        throw new NoSuchElementException("Element is not in map");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            return false;
        } else {
            MapEntry<K, V> loc = table[index];
            if (loc.getKey().equals(key)) {
                return true;
            }
            while (loc.getNext() != null) {
                if (loc.getKey().equals(key)) {
                    return true;
                }
                loc = loc.getNext();
            }
            if (loc.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> loc = table[i];
                while (loc != null) {
                    set.add(loc.getKey());
                    loc = loc.getNext();
                }
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> loc = table[i];
                while (loc != null) {
                    list.add(loc.getValue());
                    loc = loc.getNext();
                }
            }
        }
        return list;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (size > length) {
            throw new IllegalArgumentException("New length is less than size");
        }
        if (0 >= length) {
            throw new IllegalArgumentException("New length is less than 0");
        }
        MapEntry<K, V>[] newTable = new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> loc = table[i];
                while (loc != null) {
                    MapEntry<K, V> next = loc.getNext();
                    MapEntry<K, V> temp =
                            new MapEntry<>(loc.getKey(), loc.getValue());
                    int index = Math.abs(loc.getKey().hashCode() % length);
                    if (newTable[index] == null) {
                        newTable[index] = temp;
                    } else {
                        MapEntry<K, V> current = newTable[index];
                        temp.setNext(current);
                        newTable[index] = temp;
                    }
                    loc = next;
                }
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
