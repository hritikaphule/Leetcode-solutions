from collections import Counter
from functools import lru_cache

class Solution:
    def findMinStep(self, board: str, hand: str) -> int:
        hand_count = Counter(hand)

        def clean(s):
            i = 0
            while i < len(s):
                j = i
                while j < len(s) and s[j] == s[i]:
                    j += 1
                if j - i >= 3:
                    return clean(s[:i] + s[j:])
                i = j
            return s

        @lru_cache(None)
        def dfs(board, hand_tuple):
            hand = Counter()
            for k, v in hand_tuple:
                hand[k] = v

            board = clean(board)
            if not board:
                return 0
            if not any(hand.values()):
                return -1

            res = float('inf')
            for i in range(len(board) + 1):
                for c in hand:
                    if hand[c] == 0:
                        continue
                    # Skip if same color as previous to avoid duplicates
                    if i > 0 and board[i - 1] == c:
                        continue
                    new_board = board[:i] + c + board[i:]
                    hand[c] -= 1
                    tmp = dfs(new_board, tuple(sorted(hand.items())))
                    if tmp != -1:
                        res = min(res, tmp + 1)
                    hand[c] += 1
            return res if res != float('inf') else -1

        return dfs(board, tuple(sorted(hand_count.items())))
