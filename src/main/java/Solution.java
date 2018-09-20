/* 
Your previous Python 2 content is preserved below:

# Given a string of characters from the set "(", ")", ":", 
# identify if the string is a valid parentheses expression. 
# The ":" character can either be 
# considered standalone, or combine with open or closed braces to form smileys, 
# such as ":(", ":)", "(:", "):"
# Examples:
# - Valid strings: 
#     ":()"
#     "()"
#     ":"
#     "::"
#     ":)"
#     "(:)"
#     "(:))"
#     "():("
#     "()()()()()):"
# - Invalid strings: 
#     ":(("
#     "())"
#     ":()("
#     ":)("
*/

/*
 * solution idea:
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public static void printMatrix(boolean[][] matrix, String s) {

        System.out.println(Arrays.toString(s.toCharArray()));

        for (boolean[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }

    public static Boolean isValid(String s) {


        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ':') dp[i][i] = true;
            if (i < n - 1 &&
                    (s.charAt(i) == '(' || s.charAt(i) == ':') &&
                    (s.charAt(i + 1) == ')' || s.charAt(i + 1) == ':')) { // (: , () , :), ::
                dp[i][i + 1] = true;

                if (s.charAt(i) == ':') {
                    dp[i + 1][i + 1] = true;
                }
            }

            if (i < n - 1 &&
                    ((s.charAt(i) == ':' && s.charAt(i + 1) == '(') || // :(
                            (s.charAt(i) == ')' && s.charAt(i + 1) == ':'))) {   // ):
                dp[i][i + 1] = true;
            }
        }

        for (int size = 2; size < n; size++) {
            for (int i = 0; i + size < n; i++) {
                if (s.charAt(i) == ':' && dp[i + 1][i + size]) {

                    dp[i][i + size] = true;

                } else if (s.charAt(i) == '(') {
                    for (int k = i + 1; k <= i + size; k++) {
                        if ((s.charAt(k) == ')') &&
                                (k == i + 1 || dp[i + 1][k - 1]) &&
                                (k == i + size || dp[k + 1][i + size])) {

                            dp[i][i + size] = true;

                        }
                    }
                } else if (s.charAt(i) == ')' && dp[i][i] && dp[i + 1][i + size]) {  // ) is as :)
                    dp[i][i + size] = true;
                }
            }
        }

        return dp[0][n - 1];

    }

    public static void main(String[] args) {

        ArrayList<String> VALID_CASES = new ArrayList<String>();
        VALID_CASES.addAll(Arrays.asList(":()()()()()((::))):", "()", ":", "::", ":)", "(((((((:::::::)))))))", "(:)", "(:))", "():(", "()()()()()):"));

        ArrayList<String> INVALID_CASES = new ArrayList<String>();
        INVALID_CASES.addAll(Arrays.asList(":((", ":())", ":()(", ":)(", ":(((((((((((", ":))))))))", ":::::::)))))((((("));

        for (String validCase : VALID_CASES) {
            System.out.println(validCase + " is " + isValid(validCase));

        }
        for (String invalidCase : INVALID_CASES) {
            System.out.println(invalidCase + " is " + isValid(invalidCase));
        }
    }
}
