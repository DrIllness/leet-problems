/**
 *678. Valid Parenthesis String
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 *
 * The following rules define a valid string:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "(*)"
 * Output: true
 * Example 3:
 *
 * Input: s = "(*))"
 * Output: true
 * Example 4:
 *
 * Input: s = "("
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s[i] is '(', ')' or '*'.
 *
 * */


fun main() {
    println(checkValidString("()"))
    println(checkValidString("(*)"))
    println(checkValidString("(*))"))
    println(checkValidString("("))
}

private fun checkValidString(s: String): Boolean {
    if (s.isEmpty()) return true
    var balance1 = 0
    var balance2 = 0

    for (c in s) {
        when (c) {
            '(' -> {
                balance1++
                balance2++
            }

            ')' -> {
                balance1--
                balance2--
            }

            else -> {
                balance1--
                balance2++
            }
        }
        if (balance2 < 0) return false
        if (balance1 < 0) balance1 = 0
    }

    return balance1 == 0
}