class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def numComponents(self, head: ListNode, nums: List[int]) -> int:
        nums_set = set(nums)
        count = 0
        in_component = False

        while head:
            if head.val in nums_set:
                if not in_component:
                    count += 1
                    in_component = True
            else:
                in_component = False
            head = head.next

        return count
