/**
 * Given a string s consisting of words and spaces, return the length of the last word in the string.
 *
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Example 1:
 *
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 * Example 2:
 *
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 * Example 3:
 *
 * Input: s = "luffy is still joyboy"
 * Output: 6
 * Explanation: The last word is "joyboy" with length 6.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of only English letters and spaces ' '.
 * There will be at least one word in s.
 *
 * **/

fun main(args: Array<String>) {
    val input = "a "
    val expectedOutput = 1
    val actualOutput = lengthOfLastWord(input)
    println("expected output == $expectedOutput, actual output == $actualOutput")
}

fun lengthOfLastWord(s: String): Int {
    var i = s.length - 1
    var length = 0

    if (i == 0) return 1

    while (i >= 0 && s[i] == ' ') {i--}
    while (i >= 0 && s[i] != ' ') {
        i--
        length++
    }

    return length
}