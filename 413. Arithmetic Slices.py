class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        if len(nums) < 3:
            return 0

        count = 0  # number of slices ending at current index
        res = 0    # total slices

        for i in range(2, len(nums)):
            if nums[i] - nums[i-1] == nums[i-1] - nums[i-2]:
                count += 1
                res += count
            else:
                count = 0

        return res
