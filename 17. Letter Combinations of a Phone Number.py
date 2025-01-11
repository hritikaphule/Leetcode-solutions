class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []

        # Mapping of digits to corresponding letters
        phone_map = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz"
        }

        def backtrack(index, path):
            # If the path length equals the digits length, add the combination
            if index == len(digits):
                combinations.append("".join(path))
                return

            # Get the letters corresponding to the current digit
            possible_letters = phone_map[digits[index]]
            for letter in possible_letters:
                path.append(letter)  # Choose
                backtrack(index + 1, path)  # Explore
                path.pop()  # Un-choose

        combinations = []
        backtrack(0, [])
        return combinations