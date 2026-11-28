/**
 *
 * 14. Longest Common Prefix
 * Solved
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters if it is non-empty.
 *
 * */


fun main() {
    println(longestCommonPrefix(arrayOf("flower","flow","flight")))
    println(longestCommonPrefix(arrayOf("dog","racecar","car")))
    println(longestCommonPrefix(arrayOf("dogerecar","doggerecar","dog")))
}

private fun longestCommonPrefix(strs: Array<String>): String {
    var high = 0
    for (i in strs[0].indices) {
        val c = strs[0][i]
        for (s in strs) {
            if (i > s.length - 1 || c != s[i]) {
                return strs[0].substring(0, high)
            }
        }
        high++
    }
    return strs[0].substring(0, high)
}