/**
 * 383. Ransom Note
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 *
 * */

fun main() {
    println(canConstruct("a", "b"))
    println(canConstruct("aa", "ab"))
    println(canConstruct("aa", "aab"))
}

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    if (ransomNote.length > magazine.length) return false

    val magazineLetters = hashMapOf<Char, Int>()
    for (c in magazine) {
        magazineLetters[c] = magazineLetters[c]?.inc() ?: 1
    }
    for (c in ransomNote) {
        if ((magazineLetters[c] ?: 0) > 0) magazineLetters[c] = magazineLetters[c]!!.dec()
        else
            return false
    }

    return true
}

