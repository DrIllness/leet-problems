/**
 * Given a string s and an integer k, return the length of the longest substring of s that contains
 * at most k distinct characters.
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa" with length 2.
 * */

fun main() {
    println(longestSubstringWithKDistinctChars("eceba", 2))

}

fun longestSubstringWithKDistinctChars(input: String, k: Int): String {
    var result = ""
    val encounterCharsWithFrequency = hashMapOf<Char, Int>()
    if (input.isEmpty()) return result
    var left = 0
    var right = 0

    while (right < input.length) {
        val rightChar = input[right]
        encounterCharsWithFrequency[rightChar] = (encounterCharsWithFrequency[rightChar] ?: 0) + 1

        while (encounterCharsWithFrequency.size > k) {
            val leftChar  = input[left]
            val currentCount = encounterCharsWithFrequency[leftChar]!! - 1
            if (currentCount == 0) {
                encounterCharsWithFrequency.remove(leftChar)
            } else {
                encounterCharsWithFrequency[leftChar] = currentCount
            }
            left++
        }

        val currentWindowLength = right - left + 1
        if (currentWindowLength > result.length) {
            result = input.substring(left..right)
        }
        right++
    }

    return result
}