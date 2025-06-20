from collections import deque

class Solution:
    def racecar(self, target: int) -> int:
        queue = deque([(0, 1)])  # (position, speed)
        visited = set((0, 1))
        steps = 0
        
        while queue:
            for _ in range(len(queue)):
                pos, speed = queue.popleft()
                if pos == target:
                    return steps

                # Accelerate
                next_pos = pos + speed
                next_speed = speed * 2
                if (next_pos, next_speed) not in visited and abs(next_pos) <= 2 * target:
                    visited.add((next_pos, next_speed))
                    queue.append((next_pos, next_speed))

                # Reverse
                reverse_speed = -1 if speed > 0 else 1
                if (pos, reverse_speed) not in visited:
                    visited.add((pos, reverse_speed))
                    queue.append((pos, reverse_speed))

            steps += 1

        return -1  # Should never reach here
