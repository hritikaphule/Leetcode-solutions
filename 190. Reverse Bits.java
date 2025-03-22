public class Solution {
    public int reverseBits(int n) {
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            result <<= 1;         // Left shift result by 1 bit to make space
            result |= (n & 1);    // Get the last bit of n and set it in result
            n >>>= 1;            // Right shift n by 1 bit (use unsigned shift)
        }
        
        return result;
    }
}
