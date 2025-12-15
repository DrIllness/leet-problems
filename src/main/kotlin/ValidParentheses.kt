/**
 * 20. Valid Parentheses
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "()[]{}"
 *
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(]"
 *
 * Output: false
 *
 * Example 4:
 *
 * Input: s = "([])"
 *
 * Output: true
 *
 * Example 5:
 *
 * Input: s = "([)]"
 *
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 *
 * */

fun main() {
    println(isValid("()"))
    println(isValid("()[]{}"))
    println(isValid("(]"))
    println(isValid("([])"))
    println(isValid("([)]"))

}

fun isValid(s: String): Boolean {
    if (s.length % 2 > 0) return false
    if (s.isEmpty()) return true
    val closingBrackets = ArrayDeque<Char>()

    for (c in s) {
        when (c) {
            '(' -> {
                closingBrackets.addFirst(')')
            }

            '[' -> {
                closingBrackets.addFirst(']')
            }

            '{' -> {
                closingBrackets.addFirst('}')
            }

            else -> {
                if (closingBrackets.isEmpty()) return false
                if (c != closingBrackets.removeFirst()) return false
            }
        }
    }

    return closingBrackets.isEmpty()
}