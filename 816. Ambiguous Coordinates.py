class Solution:
    def ambiguousCoordinates(self, s: str) -> List[str]:
        def valid_floats(part: str) -> List[str]:
            n = len(part)
            results = []

            # If it's just "0"
            if part == "0":
                return ["0"]

            # Starts with '0' and ends with '0' like "00" -> invalid
            if part[0] == '0' and part[-1] == '0':
                return []

            # Starts with '0' (like "012")
            if part[0] == '0':
                return ["0." + part[1:]] if len(part) > 1 else ["0"]

            # Ends with '0' (like "120")
            if part[-1] == '0':
                return [part]  # Can't put decimal

            # Otherwise - place decimals at all valid points
            results.append(part)
            for i in range(1, n):
                results.append(part[:i] + '.' + part[i:])
            return results

        s = s[1:-1]  # remove parentheses
        n = len(s)
        res = set()

        for i in range(1, n):
            left_parts = valid_floats(s[:i])
            right_parts = valid_floats(s[i:])
            for l in left_parts:
                for r in right_parts:
                    res.add(f"({l}, {r})")

        return list(res)
