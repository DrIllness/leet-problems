/**
 * 32. Longest Valid Parentheses
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 *
 * */

fun main() {
    println(longestValidParentheses("(()"))
    println(longestValidParentheses(")()())"))
    println(longestValidParentheses(""))
}

private fun longestValidParentheses(s: String): Int {
    var len = 0
    val stack = ArrayDeque<Int>()
    stack.addLast(-1)

    for (i in s.indices) {
        if (s[i] == '(') {
            stack.addLast(i)
        } else {
            stack.removeLastOrNull()

            if (stack.isEmpty()) {
                stack.addLast(i)
            } else {
                len = maxOf(len, i - stack.last())
            }
        }
    }

    return len
}