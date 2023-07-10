import java.util.Arrays;

public class Task5<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.5;
    private static final int MAX_REHASH_ATTEMPTS = 1000;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] table;
    private int size;

    // Constructs a CuckooHashMap with the default initial capacity.
    @SuppressWarnings("unchecked")
    public Task5() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }


    // Returns the number of key-value pairs in this map.
    public int size() {
        return size;
    }


    // Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if ((double) size / table.length >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }

        int hash1 = hash1(key);
        int hash2 = hash2(key);

        for (int i = 0; i < MAX_REHASH_ATTEMPTS; i++) {
            if (putEntry(hash1, key, value)) {
                return;
            }

            if (putEntry(hash2, key, value)) {
                return;
            }

            evictEntry(hash1);
        }

        rehash(); // Rehash if maximum rehash attempts exceeded
        put(key, value); // Try again after rehashing
    }

    /**
     * Associates the specified key-value pair with the specified hash in the map.
     * If the specified hash index is available, the entry is stored there.
     *
     * @param hash  the hash value of the key
     * @param key   the key to be associated with the specified value
     * @param value the value to be associated with the specified key
     * @return true if the entry was successfully inserted, false otherwise
     */
    private boolean putEntry(int hash, K key, V value) {
        int index = indexFor(hash, table.length);

        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            size++;
            return true;
        }

        if (table[index].key.equals(key)) {
            table[index].value = value; // Update existing entry
            return true;
        }

        return false; // Collision occurred, entry not inserted
    }

    /**
     * Evicts the entry at the specified hash index by reinserting it in an alternate location.
     *
     * @param hash the hash value of the key to be evicted
     */
    private void evictEntry(int hash) {
        int index = indexFor(hash, table.length);
        Entry<K, V> evictedEntry = table[index];

        int hash1 = hash1(evictedEntry.key);
        int hash2 = hash2(evictedEntry.key);

        if (table[indexFor(hash1, table.length)] == evictedEntry) {
            table[indexFor(hash1, table.length)] = null;
        } else if (table[indexFor(hash2, table.length)] == evictedEntry) {
            table[indexFor(hash2, table.length)] = null;
        }

        put(evictedEntry.key, evictedEntry.value); // Re-insert the evicted entry
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int hash1 = hash1(key);
        int hash2 = hash2(key);

        int index1 = indexFor(hash1, table.length);
        if (table[index1] != null && table[index1].key.equals(key)) {
            return table[index1].value;
        }

        int index2 = indexFor(hash2, table.length);
        if (table[index2] != null && table[index2].key.equals(key)) {
            return table[index2].value;
        }

        return null; // Key not found
    }

    /**
     * Rehashes the map by doubling the table size and reinserting all entries.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        table = (Entry<K, V>[]) new Entry[newCapacity];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    /**
     * Returns the hash value computed by the first hash function for the specified key.
     *
     * @param key the key to compute the hash value for
     * @return the hash value computed by the first hash function
     */
    private int hash1(K key) {
        return key.hashCode() % table.length;
    }

    /**
     * Returns the hash value computed by the second hash function for the specified key.
     *
     * @param key the key to compute the hash value for
     * @return the hash value computed by the second hash function
     */
    private int hash2(K key) {
        return (key.hashCode() / table.length) % table.length;
    }

    /**
     * Returns the index in the table for the specified hash value and table length.
     *
     * @param hash        the hash value
     * @param tableLength the length of the table
     * @return the index in the table for the specified hash value and table length
     */
    private int indexFor(int hash, int tableLength) {
        return hash & (tableLength - 1);
    }

    public static void main(String[] args) {
        Task5<String, Integer> map = new Task5<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);

        System.out.println("Size: " + map.size());
        System.out.println("Is empty? " + map.isEmpty());

        System.out.println("Value for 'two': " + map.get("two"));
        System.out.println("Value for 'four': " + map.get("four"));
        System.out.println("Value for 'seven': " + map.get("seven"));
    }
}
