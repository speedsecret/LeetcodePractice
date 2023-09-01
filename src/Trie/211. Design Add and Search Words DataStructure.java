/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false. 
word may contain dots '.' where dots can be matched with any letter.

Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

Constraints:

1 <= word.length <= 25
word in addWord consists of lowercase English letters.
word in search consist of '.' or lowercase English letters.
There will be at most 2 dots in word for search queries.
At most 104 calls will be made to addWord and search.
*/

// Methodology:
// 1. We could use Trie DataStructure as it would speed up the time complexity especially in search
// 1.1 To implement its function, we need to create a new class, such as TrieNode.
// 2. We can instead use a HashSet data structure to store previous words
class TrieNode {
    boolean isEndWord;
    TrieNode[] children;
    public TrieNode() {
        this.isEndWord = false;
        this.children = new TrieNode[26];
    }
}

class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEndWord = true;
    }
    
    public boolean search(String word) {
        return searchWord(word, 0, root);
    }

    private boolean searchWord(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            int childIndex = c - 'a';
            TrieNode childNode = node.children[childIndex];
            return childNode != null && searchWord(word, index + 1, childNode);
        } else {
            for (TrieNode child : node.children) {
                if (child != null && searchWord(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
