import kotlin.math.max

/**
 *1. Longest Substring Without Repeating Characters (Sliding Window)
 * Problem Statement
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Examples
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. Note that the answer must be a substring; "pwke" is a subsequence and not a substring.
 *
 *  Constraints
 * 0 <= s.length <= 5 * 10^4
 *
 * s consists of English letters, digits, symbols, and spaces.
 * */

fun main() {
    val longest = longestSubstringWOrepeatingChars("abba")
    println("Length == ${longest}")

}

fun longestSubstringWOrepeatingChars(s: String): Int {
    if (s.isEmpty()) return 0
    var longest = 0
    val strToIndex = hashMapOf<Char, Int>()

    var i = 0
    var start = 0
    while (i < s.length) {
        strToIndex[s[i]]?.let { index ->
            if (index >= start) {
                start = index + 1
            }
        }

        strToIndex[s[i]] = i
        longest = max(longest, i - start + 1)
        i++
    }

    return longest
}