import java.util.*;

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", num, target, 0, 0, 0);
        return result;
    }

    private void backtrack(List<String> result, String path, String num, int target, int index, long eval, long prevNum) {
        if (index == num.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            // Avoid numbers with leading zero
            if (i != index && num.charAt(index) == '0') break;

            String currStr = num.substring(index, i + 1);
            long currNum = Long.parseLong(currStr);

            if (index == 0) {
                // First number, just start the expression
                backtrack(result, path + currStr, num, target, i + 1, currNum, currNum);
            } else {
                backtrack(result, path + "+" + currStr, num, target, i + 1, eval + currNum, currNum);
                backtrack(result, path + "-" + currStr, num, target, i + 1, eval - currNum, -currNum);
                backtrack(result, path + "*" + currStr, num, target, i + 1, eval - prevNum + prevNum * currNum, prevNum * currNum);
            }
        }
    }
}
