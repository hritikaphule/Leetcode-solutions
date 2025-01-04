class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        class TrieNode:
            def __init__(self):
                self.children = {}
                self.word = None

        def build_trie(words):
            root = TrieNode()
            for word in words:
                node = root
                for char in word:
                    if char not in node.children:
                        node.children[char] = TrieNode()
                    node = node.children[char]
                node.word = word  # Mark the end of a word
            return root

        def dfs(node, x, y):
            if node.word:
                result.add(node.word)
                node.word = None  # Avoid duplicate entries

            if x < 0 or x >= m or y < 0 or y >= n or board[x][y] not in node.children:
                return

            char = board[x][y]
            board[x][y] = '#'  # Mark the cell as visited
            for dx, dy in directions:
                dfs(node.children[char], x + dx, y + dy)
            board[x][y] = char  # Restore the cell

        root = build_trie(words)
        result = set()
        m, n = len(board), len(board[0])
        directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]

        for i in range(m):
            for j in range(n):
                if board[i][j] in root.children:
                    dfs(root, i, j)

        return list(result)
