/**
 * 49. Group Anagrams
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Explanation:
 *
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 * Example 2:
 *
 * Input: strs = [""]
 *
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 *
 * Output: [["a"]]
 *
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 *
 * */

fun main() {
    groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat")).printListOfLists()
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val buffer = IntArray(26) { 0 }
    var anagrams = hashMapOf<String, MutableList<String>>()
    var i = 0
    while (i < strs.size) {
        var strKey = makeAnagramKey(strs[i], buffer)
        if (anagrams.containsKey(strKey)) {
            anagrams[strKey]?.add(strs[i])
        } else {
            anagrams[strKey] = mutableListOf(strs[i])
        }
        clearBuffer(buffer)
        i++
    }

    return anagrams.values.toList()
}

private fun makeAnagramKey(source: String, buffer: IntArray): String {
    var i = 0
    while (i < source.length) {
        buffer[source[i++] - 'a'] += 1
    }

    i = 0
    val anagramBuffer = StringBuilder()
    while (i < buffer.size) {
        anagramBuffer.append(buffer[i++])
        anagramBuffer.append(" ")
    }

    return anagramBuffer.toString()
}

private fun clearBuffer(buffer: IntArray) {
    var i = 0
    while (i < buffer.size) {
        buffer[i++] = 0
    }
}


fun List<List<Any>>.printListOfLists() {
    forEach { containerList ->
        print("[ ")
        containerList.forEach { print("$it ") }
        print("]")
    }
}