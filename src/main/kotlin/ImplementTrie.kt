/**
 *208. Implement Trie (Prefix Tree)
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 *
 * */

fun main() {
    val trie = Trier()
    trie.insert("apple")
    println(trie.search("apple"))   // return True
    println(trie.search("app"))     // return False
    println(trie.startsWith("app")) // return True
    trie.insert("app")
    println(trie.search("app"))     // return True

}

class Trier() {
    var isTerminating = false
    val chars = hashMapOf<Char, Trier>()

    private fun insertr(word: String, start: Int) {
        if (start >= word.length) {
            isTerminating = true
            return
        }

        val c = word[start]
        if (chars.contains(c).not()) {
            chars[c] = Trier()
        }
        chars[c]?.insertr(word, start + 1)
    }

    fun insert(word: String) {
        insertr(word, 0)
    }

    fun searchr(word: String, start: Int): Boolean {
        if (start >= word.length) {
            return isTerminating
        }

        return chars[word[start]]?.searchr(word, start + 1) == true
    }

    fun search(word: String): Boolean {
        return searchr(word, 0)
    }

    private fun startsWithr(prefix: String, start: Int): Boolean {
        if (start >= prefix.length) return true
        return chars[prefix[start]]?.startsWithr(prefix, start + 1) == true
    }

    fun startsWith(prefix: String): Boolean {
        return startsWithr(prefix, 0)
    }

}

class Trie() {
    var isTerminating = false
    val chars = hashMapOf<Char, Trie>()

    fun insert(word: String) {
        var next: Trie? = this
        for (c in word) {
            if (next?.chars?.get(c) == null) {
                next?.chars?.put(c, Trie())
            }
            next = next?.chars?.get(c)
        }
        next?.isTerminating = true
    }

    fun search(word: String): Boolean {
        var next: Trie? = this
        for (c in word) {
            if (next?.chars?.get(c) == null) {
                return false
            }
            next = next.chars[c]
        }

        return next?.isTerminating == true
    }

    fun startsWith(prefix: String): Boolean {
        var next: Trie? = this
        for (c in prefix) {
            if (next?.chars?.get(c) == null) {
                return false
            }
            next = next.chars[c]
        }

        return true
    }
}
