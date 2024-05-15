package Main;

public class MyHashTable<K,V> {
    private class HashNode<K,V> {
        private K key;
        private V value;
        private HashNode<K,V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K,V>[] chainArray;
    private static int M = 11;
    private int size;
    public MyHashTable() {
        this(M);
    }
    public MyHashTable(int M) {
        this.M=M;
        this.chainArray = new HashNode[M];  // Create array of HashNode
        this.size = 0;
    }

    public int hash(K key) {
        return hashCode(key) % M;
    }
    private int hashCode(K key) {
        int result = 0;
        String keyString = key.toString();
        for (int i = 0; i < keyString.length(); i++) {
            result += keyString.charAt(i);
        }
        return result;
    }
    public void put(K key, V value)  {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Null key or value not allowed.");
        }
        int index = hash(key);
        HashNode<K,V> newNode = new HashNode<>(key, value);

        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K,V> current = chainArray[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;  // Update value if key already exists
                    return;
                }
                if (current.next == null) {
                    break;  // Reached end of list
                }
                current = current.next;
            }
            current.next = newNode;  // Insert new node at end of list
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        while (current != null) {
            if(current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    // If the node to be removed is the first node in the chain
                    chainArray[index] = current.next;
                } else {
                    // If the node to be removed is not the first node
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public boolean contains(V value) {
        for(HashNode<K,V> node: chainArray) {
            if(node.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
    public K getKey(V value) {
        for(HashNode<K,V> node: chainArray) {
            if(node.value.equals(value)) {
                return node.key;
            }
        }
        return null;
    }
}
