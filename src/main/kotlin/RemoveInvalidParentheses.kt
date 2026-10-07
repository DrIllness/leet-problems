/**
 * 301. Remove Invalid Parentheses
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 *
 * Input: s = ")("
 * Output: [""]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 *
 * */

fun main() {
    removeInvalidParentheses("()())()").printList()
    removeInvalidParentheses("(a)())()").printList()
    removeInvalidParentheses(")(").printList()
    removeInvalidParentheses("(()())").printList()
}

private fun removeInvalidParentheses(s: String): List<String> {
    val uniqueStrings = hashSetOf<String>()
    var balance = 0
    var invalidBracketsCount = 0
    for (i in s.indices) {
        when(s[i]) {
            '(' -> { balance++ }
            ')' -> {
                balance--
                if (balance < 0) {
                    balance = 0
                    invalidBracketsCount++
                }
            }
        }
    }
    invalidBracketsCount += balance

    fun helper(index: Int, removalsLeft: Int, open: Int, candidate: String) {
        if (open < 0) return

        if (index == s.length) {
            if (open == 0 && removalsLeft == 0)
                uniqueStrings.add(candidate)

            return
        }

        var openCountAfterAdd = open
        var isLetter = false
        when(s[index]) {
            '(' -> {
                openCountAfterAdd++
            }
            ')' -> {
                openCountAfterAdd--
            }
            else -> {
                isLetter = true
            }
        }

        helper(index + 1, removalsLeft, openCountAfterAdd, candidate + s[index]) //we keep

        if (!isLetter) {
            if (removalsLeft > 0) { helper(index + 1, removalsLeft - 1, open, candidate) } //
        }
    }
    helper(0, invalidBracketsCount, 0, "")

    return uniqueStrings.toList()
}