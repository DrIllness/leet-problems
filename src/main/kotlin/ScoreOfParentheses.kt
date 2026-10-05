/**
 * 856. Score of Parentheses
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given a balanced parentheses string s, return the score of the string.
 *
 * The score of a balanced parentheses string is based on the following rule:
 *
 * "()" has score 1.
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: 1
 * Example 2:
 *
 * Input: s = "(())"
 * Output: 2
 * Example 3:
 *
 * Input: s = "()()"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 2 <= s.length <= 50
 * s consists of only '(' and ')'.
 * s is a balanced parentheses string.
 *
 * */

fun main() {
    println(scoreOfParentheses("()"))
    println(scoreOfParentheses("(()()())"))
    println(scoreOfParentheses("(())"))
    println(scoreOfParentheses("()()"))
}

private fun scoreOfParentheses(s: String): Int {
    var res = 0
    var nesting = 0
    var prevOpen = true

    for (c in s) {
        when(c) {
            '(' -> {
                nesting++
                prevOpen = true
            }
            ')' -> {
                nesting--

                if (prevOpen) {
                    res += 1 shl nesting
                }
                prevOpen = false
            }
        }
    }

    return res
}