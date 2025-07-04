class Solution:
    def closestCost(self, baseCosts: List[int], toppingCosts: List[int], target: int) -> int:
        self.closest = float('inf')

        def dfs(index, current_cost):
            # Update closest result if better
            if abs(current_cost - target) < abs(self.closest - target) or (
                abs(current_cost - target) == abs(self.closest - target) and current_cost < self.closest
            ):
                self.closest = current_cost

            # Stop if cost exceeds target and we're moving further
            if current_cost > target:
                return

            if index >= len(toppingCosts):
                return

            # Try all 0, 1, or 2 of current topping
            for i in range(3):
                dfs(index + 1, current_cost + i * toppingCosts[index])

        for base in baseCosts:
            dfs(0, base)

        return self.closest
