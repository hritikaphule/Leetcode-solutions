from typing import List

class Solution:
    def smallestSufficientTeam(self, req_skills: List[str], people: List[List[str]]) -> List[int]:
        from collections import defaultdict

        skill_index = {skill: i for i, skill in enumerate(req_skills)}
        n = len(req_skills)
        dp = {0: []}  # skill_mask: list of person indices

        for i, person in enumerate(people):
            person_mask = 0
            for skill in person:
                if skill in skill_index:
                    person_mask |= 1 << skill_index[skill]

            if person_mask == 0:
                continue  # person has no useful skill

            # iterate over a copy of current dp
            for existing_mask, team in list(dp.items()):
                new_mask = existing_mask | person_mask
                if new_mask not in dp or len(dp[new_mask]) > len(team) + 1:
                    dp[new_mask] = team + [i]

        full_mask = (1 << n) - 1
        return dp[full_mask]
