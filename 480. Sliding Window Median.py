import heapq
from collections import defaultdict
from typing import List

class DualHeap:
    def __init__(self, k):
        self.small = []  # Max-heap (invert values)
        self.large = []  # Min-heap
        self.delayed = defaultdict(int)
        self.k = k
        self.small_size = 0
        self.large_size = 0

    def prune(self, heap):
        while heap:
            num = -heap[0] if heap is self.small else heap[0]
            if self.delayed[num]:
                heapq.heappop(heap)
                self.delayed[num] -= 1
            else:
                break

    def balance(self):
        if self.small_size > self.large_size + 1:
            val = -heapq.heappop(self.small)
            heapq.heappush(self.large, val)
            self.small_size -= 1
            self.large_size += 1
            self.prune(self.small)
        elif self.small_size < self.large_size:
            val = heapq.heappop(self.large)
            heapq.heappush(self.small, -val)
            self.large_size -= 1
            self.small_size += 1
            self.prune(self.large)

    def insert(self, num):
        if not self.small or num <= -self.small[0]:
            heapq.heappush(self.small, -num)
            self.small_size += 1
        else:
            heapq.heappush(self.large, num)
            self.large_size += 1
        self.balance()

    def erase(self, num):
        self.delayed[num] += 1
        if num <= -self.small[0]:
            self.small_size -= 1
            if num == -self.small[0]:
                self.prune(self.small)
        else:
            self.large_size -= 1
            if self.large and num == self.large[0]:
                self.prune(self.large)
        self.balance()

    def get_median(self) -> float:
        if self.k % 2 == 1:
            return float(-self.small[0])
        else:
            return (-self.small[0] + self.large[0]) / 2.0

class Solution:
    def medianSlidingWindow(self, nums: List[int], k: int) -> List[float]:
        dh = DualHeap(k)
        result = []

        for i in range(len(nums)):
            dh.insert(nums[i])
            if i >= k - 1:
                result.append(dh.get_median())
                dh.erase(nums[i - k + 1])

        return result
