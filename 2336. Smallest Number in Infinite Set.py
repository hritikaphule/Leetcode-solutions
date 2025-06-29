import heapq

class SmallestInfiniteSet:
    def __init__(self):
        self.current = 1
        self.heap = []
        self.in_heap = set()

    def popSmallest(self) -> int:
        if self.heap:
            val = heapq.heappop(self.heap)
            self.in_heap.remove(val)
            return val
        else:
            val = self.current
            self.current += 1
            return val

    def addBack(self, num: int) -> None:
        if num < self.current and num not in self.in_heap:
            heapq.heappush(self.heap, num)
            self.in_heap.add(num)
