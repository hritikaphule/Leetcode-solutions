import heapq

class Solution:
    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        # Max heap of (-count, char)
        max_heap = []
        for count, char in [(-a, 'a'), (-b, 'b'), (-c, 'c')]:
            if count != 0:
                heapq.heappush(max_heap, (count, char))
        
        result = []

        while max_heap:
            count1, char1 = heapq.heappop(max_heap)
            
            # Check if last two characters are same as current
            if len(result) >= 2 and result[-1] == result[-2] == char1:
                if not max_heap:
                    break  # no alternative character, stop building
                count2, char2 = heapq.heappop(max_heap)
                result.append(char2)
                count2 += 1  # using one char2
                if count2 < 0:
                    heapq.heappush(max_heap, (count2, char2))
                heapq.heappush(max_heap, (count1, char1))  # push char1 back
            else:
                result.append(char1)
                count1 += 1  # using one char1
                if count1 < 0:
                    heapq.heappush(max_heap, (count1, char1))

        return ''.join(result)
