class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        // Convert the bank to a HashSet for fast lookup
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        
        // If the endGene is not in the bank, there's no valid path
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        
        // Characters allowed in the gene string
        char[] geneChars = {'A', 'C', 'G', 'T'};
        
        // BFS queue: (current_gene, mutations_count)
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        
        // Visited set to avoid revisiting genes
        Set<String> visited = new HashSet<>();
        visited.add(startGene);
        
        int mutations = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();
                
                // If the current gene is the target, return the mutation count
                if (currentGene.equals(endGene)) {
                    return mutations;
                }
                
                // Generate all possible mutations
                char[] geneArray = currentGene.toCharArray();
                for (int j = 0; j < geneArray.length; j++) {
                    char originalChar = geneArray[j];
                    for (char geneChar : geneChars) {
                        // Skip if the character is the same as the current one
                        if (geneChar == originalChar) {
                            continue;
                        }
                        
                        // Create a new gene string by mutating one character
                        geneArray[j] = geneChar;
                        String mutatedGene = new String(geneArray);
                        
                        // If the mutation is valid and not visited
                        if (bankSet.contains(mutatedGene) && !visited.contains(mutatedGene)) {
                            visited.add(mutatedGene);
                            queue.offer(mutatedGene);
                        }
                    }
                    // Restore the original character
                    geneArray[j] = originalChar;
                }
            }
            
            // Increment mutation count after processing a level
            mutations++;
        }
        
        // If we exhaust the queue and don't find the endGene
        return -1;
    }
}
