public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        
        while (n != 0) {
            count += (n & 1); // Check if the last bit is set
            n >>>= 1;         // Unsigned right shift to process the next bit
        }
        
        return count;
    }
}
