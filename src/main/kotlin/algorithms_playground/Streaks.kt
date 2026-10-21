package algorithms_playground

import kotlin.math.max

fun main() {
    //println(maxDepth("()(())((()()))"))
    //println(maxDepth("(1)+((2))+(((3)))"))
    //println(maxDepth("(1+(2*3)+((8)/4))+1"))

    println(isValid("([])"))
}

fun maxDepth(s: String): Int {
    var currLvl = 0
    var maxLvl = 0
    for (i in s.indices) {
        if (s[i] == '(') {
            currLvl++
            maxLvl = max(currLvl, maxLvl)
        } else if (s[i] == ')') {
            currLvl--
        } else {
            continue
        }
    }

    return maxLvl
}

private fun isValid(s: String): Boolean {
    if (s.isEmpty()) return false
    if (s.length % 2 != 0) return false
    val queue = ArrayDeque<Char>()
    for (i in s.indices) {
        when (s[i]) {
            '(' -> queue.addLast(')')
            '{' -> queue.addLast('}')
            '[' -> queue.addLast(']')
            else -> {
                val last = queue.removeLastOrNull()
                if (last != s[i]) {
                    return false
                }
            }
        }
    }

    return queue.isEmpty()
}