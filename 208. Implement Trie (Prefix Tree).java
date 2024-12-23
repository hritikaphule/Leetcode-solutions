class Trie {

    // Definition of TrieNode
    private class TrieNode {
        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // for 26 letters in the English alphabet
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    /** Initialize the trie object. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts the string word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    /** Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    /** Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // Helper method to search for a prefix or word in the trie
    private TrieNode searchPrefix(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}
