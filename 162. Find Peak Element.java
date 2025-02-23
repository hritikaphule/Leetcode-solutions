class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;  // Move left (peak might be mid)
            } else {
                left = mid + 1;  // Move right
            }
        }
        
        return left;  // or return right, since left == right
    }
}
