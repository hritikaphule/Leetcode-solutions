class WordDictionary {

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

    /** Initialize the object. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds word to the data structure. */
    public void addWord(String word) {
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

    /** Returns true if there is any string in the data structure that matches word or false otherwise. */
    public boolean search(String word) {
        return searchInNode(word, 0, root);
    }

    // Helper method for recursive search
    private boolean searchInNode(String word, int index, TrieNode node) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char ch = word.charAt(index);
        if (ch == '.') {
            // Wildcard: check all possible child nodes
            for (TrieNode child : node.children) {
                if (child != null && searchInNode(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Regular character: proceed with the corresponding child node
            int charIndex = ch - 'a';
            return searchInNode(word, index + 1, node.children[charIndex]);
        }
    }
}
