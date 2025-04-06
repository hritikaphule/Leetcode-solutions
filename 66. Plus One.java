public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse the array from the end (least significant digit)
        for (int i = n - 1; i >= 0; i--) {
            // If digit is less than 9, just add 1 and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            // If digit is 9, set it to 0 and continue loop to carry over
            digits[i] = 0;
        }

        // If loop completes, all digits were 9 (e.g. 999 → 1000)
        int[] result = new int[n + 1];
        result[0] = 1;  // Set the new most significant digit to 1
        return result;
    }
}
