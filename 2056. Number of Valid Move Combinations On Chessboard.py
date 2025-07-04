from typing import List
from itertools import product

class Solution:
    def countCombinations(self, pieces: List[str], positions: List[List[int]]) -> int:
        directions = {
            "rook":    [(1, 0), (-1, 0), (0, 1), (0, -1)],
            "bishop":  [(1, 1), (1, -1), (-1, 1), (-1, -1)],
            "queen":   [(1, 0), (-1, 0), (0, 1), (0, -1),
                        (1, 1), (1, -1), (-1, 1), (-1, -1)],
        }

        def in_board(r, c):
            return 1 <= r <= 8 and 1 <= c <= 8

        def generate_paths(piece, start):
            r0, c0 = start
            all_paths = []

            # Include "stay" as a valid move
            all_paths.append([(r0, c0)])

            for dr, dc in directions[piece]:
                path = []
                r, c = r0, c0
                while True:
                    r += dr
                    c += dc
                    if not in_board(r, c):
                        break
                    path.append((r, c))
                    all_paths.append([(r0, c0)] + path[:])  # path to each intermediate cell
            return all_paths

        # Generate all possible movement paths for each piece
        all_piece_paths = [generate_paths(p, pos) for p, pos in zip(pieces, positions)]

        def is_valid(moves):
            t = 0
            max_len = max(len(path) for path in moves)
            while t < max_len:
                visited = set()
                for path in moves:
                    pos = path[min(t, len(path)-1)]
                    if pos in visited:
                        return False
                    visited.add(pos)
                t += 1
            return True

        count = 0
        for combination in product(*all_piece_paths):
            if is_valid(combination):
                count += 1
        return count
