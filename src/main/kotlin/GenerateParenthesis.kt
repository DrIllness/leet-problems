/**
 * 22. Generate Parentheses
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 * */

fun main() {
    generateParenthesis(3).printList()
    generateParenthesis(1).printList()
}


private fun generateParenthesis(n: Int): List<String> {
    val res = ArrayList<String>()

    fun getParanthesis(open: Int, close: Int, s: String, n: Int) {
        // valid combo
        if (s.length == 2 * n) {
            res.add(s)
        }
        if (open < n) {
            getParanthesis(open + 1, close, "$s(", n)
        }
        if (close < open) {
            getParanthesis(open, close + 1, "$s)", n)
        }
    }

    getParanthesis(0, 0, "", n)
    return res
}
