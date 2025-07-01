from collections import defaultdict

class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        reserved = defaultdict(set)
        
        for row, seat in reservedSeats:
            reserved[row].add(seat)

        total = 0

        for row in reserved:
            blocked = reserved[row]
            can_place = 0

            # Check three positions
            left = all(seat not in blocked for seat in [2,3,4,5])
            right = all(seat not in blocked for seat in [6,7,8,9])
            middle = all(seat not in blocked for seat in [4,5,6,7])

            if left:
                can_place += 1
            if right:
                can_place += 1
            if not left and not right and middle:
                can_place = 1

            total += can_place

        # Each unreserved row can always place 2 groups
        total += (n - len(reserved)) * 2

        return total
