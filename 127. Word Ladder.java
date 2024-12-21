class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the wordList to a HashSet for fast lookup
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If the endWord is not in the wordList, return 0
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        // Queue for BFS: each entry contains (current_word, transformation_length)
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Visited set to avoid revisiting words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        int level = 1; // Start from level 1 (the beginWord)
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // If the current word is the endWord, return the level
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // Generate all possible transformations
                char[] wordArray = currentWord.toCharArray();
                for (int j = 0; j < wordArray.length; j++) {
                    char originalChar = wordArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Skip if the character is the same as the current one
                        if (c == originalChar) {
                            continue;
                        }
                        
                        // Replace the character and create a new word
                        wordArray[j] = c;
                        String transformedWord = new String(wordArray);
                        
                        // If the transformed word is in the wordSet and not visited
                        if (wordSet.contains(transformedWord) && !visited.contains(transformedWord)) {
                            visited.add(transformedWord);
                            queue.offer(transformedWord);
                        }
                    }
                    // Restore the original character
                    wordArray[j] = originalChar;
                }
            }
            
            // Increment the level after processing the current level
            level++;
        }
        
        // If no transformation sequence is found, return 0
        return 0;
    }
}