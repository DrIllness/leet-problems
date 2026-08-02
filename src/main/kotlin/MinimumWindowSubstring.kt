/**
 *Given two strings s and t of lengths m and n respectively, return
 * the minimum window substring of s such that every character in t (including duplicates)
 * is included in the window. If there is no such substring, return the empty string "".
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: 't' requires two 'a's, but 's' only has one, so return "".
 *
 * */

fun main() {
    println(minimumWindowSubstring("ADOBECODEBANC", "ABC"))
}

fun minimumWindowSubstring(s: String, t: String): String {
    if (t.length > s.length) return ""
    var result = ""
    val expectedFreq = hashMapOf<Char, Int>()
    val actualFreq = hashMapOf<Char, Int>()
    // fill expected
    for (i in t.indices) {
        expectedFreq[t[i]] = (expectedFreq[t[i]] ?: 0) + 1
    }

    var right = 0
    var left = 0

    var required = expectedFreq.size
    var matched = 0
    while (right < s.length) {
        actualFreq[s[right]] = (actualFreq[s[right]] ?: 0) + 1

        if (actualFreq[s[right]] == expectedFreq[s[right]]) {
            matched++
        }

        while (matched == required) {
            val newResult = s.substring(left, right + 1)
            result = if (result.isEmpty()) {
                newResult
            } else {
                if (newResult.length < result.length) newResult else result
            }

            val leftChar = s[left]
            actualFreq[leftChar] = (actualFreq[leftChar] ?: 0) - 1

            if (expectedFreq.containsKey(leftChar) && actualFreq[leftChar]!! < expectedFreq[leftChar]!!) {
                matched--
            }
            left++
        }
        right++
    }


    return result
}