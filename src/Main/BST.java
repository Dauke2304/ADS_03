package Main;

public class BST<K extends Comparable<K>, V>{
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public BST() {
        root = null;
    }
    public void put(K key, V val) {
        // Call the private recursive put method starting from the root of the tree
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        // Base case: If the current node is null, create a new node with the key-value pair
        if (node == null) {
            size++; // Increment size since a new node is added
            return new Node(key, val);
        }

        // Compare the key with the key of the current node
        int cmp = key.compareTo(node.key);

        // Recursive cases based on the comparison result
        if (cmp < 0) {
            node.left = put(node.left, key, val); // Insert into the left subtree
        } else if (cmp > 0) {
            node.right = put(node.right, key, val); // Insert into the right subtree
        } else {
            node.value = val; // Update the value if the key already exists
        }

        return node; // Return the modified node
    }


}
