/**
 *You are given a string s consisting of uppercase English letters and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after
 * performing the above operations.
 *
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa. The string becomes "BBBB" or "AAAA".
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the middle 'A' with 'B' to get "AABBBBA". The substring "BBBB" has length 4.
 *
 * */

fun main() {
    println(longestRepeatingChrReplacement("AABABBA", 1))
}

fun longestRepeatingChrReplacement(input: String, k: Int): Int {
    if (input.isEmpty()) return 0
    if (input.length == 1) return 1

    var maxLength = 0
    var maxFreq = 0
    val encounteredChars = IntArray(26) { 0 }
    var left = 0
    var right = 0

    while (right < input.length) {
        val rightIndex = input[right] - 'A'
        encounteredChars[rightIndex]++

        maxFreq = maxOf(encounteredChars[rightIndex], maxFreq)
        val windowLength = right - left + 1
        if (windowLength - maxFreq > k) {
            val leftCharIndex = input[left] - 'A'
            encounteredChars[leftCharIndex]--
            left++
        }

        maxLength = maxOf(maxLength, right - left + 1)
        right++
    }

    return maxLength
}