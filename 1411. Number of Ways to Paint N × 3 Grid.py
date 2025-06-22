class Solution:
    def numOfWays(self, n: int) -> int:
        MOD = 10**9 + 7
        from itertools import product

        colors = [0, 1, 2]  # 0: Red, 1: Yellow, 2: Green

        # Step 1: Generate all valid colorings for a single row
        valid_patterns = []
        for p in product(colors, repeat=3):
            if p[0] != p[1] and p[1] != p[2]:
                valid_patterns.append(p)

        # Step 2: Build transitions between valid patterns (no vertical overlap)
        pattern_count = len(valid_patterns)
        transitions = [[] for _ in range(pattern_count)]

        for i in range(pattern_count):
            for j in range(pattern_count):
                # Ensure no vertical overlap (same color in same column)
                if all(valid_patterns[i][k] != valid_patterns[j][k] for k in range(3)):
                    transitions[i].append(j)

        # Step 3: Dynamic Programming initialization
        dp = [1] * pattern_count  # For row 0, each pattern has 1 way

        # Step 4: Fill DP table row by row
        for _ in range(1, n):
            new_dp = [0] * pattern_count
            for i in range(pattern_count):        # current pattern
                for j in transitions[i]:          # next valid pattern
                    new_dp[j] = (new_dp[j] + dp[i]) % MOD
            dp = new_dp

        return sum(dp) % MOD
