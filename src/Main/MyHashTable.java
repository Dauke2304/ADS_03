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
        int index = hash(key);  // Calculate the index for the key in the hash table
        HashNode<K,V> current = chainArray[index];  // Get the head of the linked list at the calculated index

        // Traverse the linked list to find the node with the matching key
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;  // Return the value if the key is found
            }
            current = current.next;  // Move to the next node in the linked list
        }

        return null;  // Return null if the key is not found in the hash table
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K,V> current = chainArray[index];
        HashNode<K,V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // Check if the node to be removed is the head of the linked list
                if (previous == null) {
                    // Update the head of the linked list
                    chainArray[index] = current.next;
                } else {
                    // Skip the current node by updating the previous node's next pointer
                    previous.next = current.next;
                }
                size--;  // Decrease size since a node is removed
                return current.value;  // Return the value of the removed node
            }
            previous = current;
            current = current.next;
        }

        return null;  // Key not found, return null
    }


    public boolean contains(V value) {

        for (HashNode<K,V> node : chainArray) {
            // Traverse the linked list and check if any node's value matches the target value
            while (node != null) {
                if (node.value.equals(value)) {
                    return true; // Return true if the value is found in the hash table
                }
                node = node.next; // Move to the next node in the linked list
            }
        }
        return false; // Return false if the value is not found in the hash table
    }

    public K getKey(V value) {

        for (HashNode<K,V> node : chainArray) {
            // Traverse the linked list and check if any node's value matches the target value
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key; // Return the key corresponding to the target value
                }
                node = node.next; // Move to the next node in the linked list
            }
        }
        return null; // Return null if the value is not found in the hash table
    }

}
