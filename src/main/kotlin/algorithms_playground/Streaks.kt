package algorithms_playground

import kotlin.math.max

fun main() {
    println(maxDepth("()(())((()()))"))
    println(maxDepth("(1)+((2))+(((3)))"))
    println(maxDepth("(1+(2*3)+((8)/4))+1"))
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