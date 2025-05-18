MOD = 10**9 + 7

class Solution:
    def colorTheGrid(self, m: int, n: int) -> int:
        from itertools import product, groupby

        # Step 1: Generate all valid column states
        def valid_columns():
            return [
                state for state in product(range(3), repeat=m)
                if all(state[i] != state[i+1] for i in range(m - 1))
            ]

        valid = valid_columns()

        # Step 2: Check if two column states can be adjacent
        def is_compatible(a, b):
            return all(x != y for x, y in zip(a, b))

        # Step 3: Build transitions
        transitions = {state: [] for state in valid}
        for a in valid:
            for b in valid:
                if is_compatible(a, b):
                    transitions[a].append(b)

        # Step 4: DP initialization
        dp = {state: 1 for state in valid}

        # Step 5: DP over columns
        for _ in range(n - 1):
            new_dp = {state: 0 for state in valid}
            for state in valid:
                for next_state in transitions[state]:
                    new_dp[next_state] = (new_dp[next_state] + dp[state]) % MOD
            dp = new_dp

        return sum(dp.values()) % MOD

