/*
208. Implement a Trie
https://leetcode.com/problems/implement-trie-prefix-tree/description/
Absolutely, let's break down the relationship between `Trie` and `TrieNode` to clarify their roles in the implementation of a Trie data structure.

1. **TrieNode:**
   
   The `TrieNode` class represents each individual node in the Trie. Think of it as a building block that holds the information needed to construct 
   the Trie. Each node contains an array of references to its child nodes, typically representing the letters of the alphabet. In your case, you're 
   using a 26-element array for the characters 'a' through 'z'. This array, often called `links`, allows you to efficiently navigate through the Trie.

   Each `TrieNode` can have the following attributes:

   - `links`: An array of size 26 (or the size of the alphabet), containing references to child nodes.
   - `isEnd`: A boolean flag that indicates whether the node represents the end of a valid word.

   The `TrieNode` class also includes methods to interact with its attributes:
   
   - `containsKey(char ch)`: Checks if the node has a child node for the given character `ch`.
   - `get(char ch)`: Retrieves the child node for the given character `ch`.
   - `put(char ch, TrieNode node)`: Associates the character `ch` with the provided `node`.
   - `setEnd()`: Marks the current node as the end of a valid word.
   - `isEnd()`: Checks if the node represents the end of a valid word.

2. **Trie:**

   The `Trie` class serves as the entry point and manager for the Trie data structure. It primarily consists of a single instance of the `TrieNode` class,
   often called the `root` node. The root node doesn't represent any specific character but acts as the starting point for constructing and navigating the Trie.

   The `Trie` class provides methods to perform operations on the Trie:

   - `insert(String word)`: Inserts a new word into the Trie by iterating through each character and creating necessary nodes.
   - `search(String word)`: Searches for a complete word in the Trie by following the path of characters, checking for `isEnd` at the end.
   - `startsWith(String prefix)`: Checks if the Trie contains any word that starts with the given prefix.

In summary, the `Trie` class encapsulates the overall structure and operations of the Trie data structure. It manages the `TrieNode` instances and provides 
methods to interact with the Trie's contents. The `TrieNode` class represents individual nodes in the Trie, storing information about characters,
links to child nodes, and whether the node marks the end of a valid word. 

The relationship between these two classes is such that the `Trie` class uses instances of `TrieNode` to construct the Trie and perform operations on it.
*/

class Trie {

    class TrieNode {
        private TrieNode[] links;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[26];
        }

        // TrieNode APIs:
        public void setEnd() {
            isEnd = true;
        }
      
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            } 
            node = node.get(ch);
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                return null;
            }
            node = node.get(ch);
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

/*
Actually there is another version of code that you can save a lot of code is under:

class TrieNode {
    TrieNode[] children;
    boolean isEnd;
    public TrieNode() {
        children = new TrieNode[26];
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.children[ch - 'a'] == null) {
                return null;
            }
            node = node.children[ch - 'a'];
        }
        return node;
    }
}
*/
