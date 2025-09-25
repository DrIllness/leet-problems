/**
 * 28. Find the Index of the First Occurrence in a String
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * Example 2:
 *
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 * Constraints:
 *
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 * */

fun main(args: Array<String>) {
    val haystack = "mississippi"
    val needle = "issip"
    val output = strStr(haystack, needle)
    println("haystack == $haystack, needle == $needle, output == $output")
}

fun strStr(haystack: String, needle: String): Int {
    if (needle.length > haystack.length) return -1
    var i = 0
    while (i < haystack.length) {
        if (haystack[i] == needle[0]) {
            val indexCandidate = i
            var j = 0
            var tmpi = i
            var pass = true
            if (haystack.length - indexCandidate < needle.length)
                return -1

            while (j < needle.length) {
                if (haystack[tmpi++] != needle[j++]) {
                    pass = false
                    break
                }
            }
            if (pass) {
                return indexCandidate
            }
        }

        i++

    }

    return -1
}