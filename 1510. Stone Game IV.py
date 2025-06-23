class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        dp = [False] * (n + 1)  # dp[i] = True if the current player can win with i stones

        for i in range(1, n + 1):
            j = 1
            while j * j <= i:
                if not dp[i - j * j]:  # If opponent loses with remaining stones
                    dp[i] = True
                    break
                j += 1

        return dp[n]
