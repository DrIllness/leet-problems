/**
 * 1111. Maximum Nesting Depth of Two Valid Parentheses Strings
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * A string is a valid parentheses string (denoted VPS) if and only if it consists of "(" and ")" characters only, and:
 *
 * It is the empty string, or
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 *
 * Given a VPS seq, split it into two disjoint subsequences A and B, such that A and B are VPS's (and A.length + B.length = seq.length). The subsequences may not necessarily be contiguous.
 *
 * For example, for the sequence 123456789, one possible split is:
 *
 * A = {1, 3, 5, 7, 9},
 *
 * B = {2, 4, 6, 8}.
 *
 * This corresponds to the output [0, 1, 0, 1, 0, 1, 0, 1, 0]  where 0 indicates membership in A and 1 indicates membership in B.
 *
 * Now choose any such A and B such that max(depth(A), depth(B)) is the minimum possible value.
 *
 * Return an answer array (of length seq.length) that encodes such a choice of A and B:  answer[i] = 0 if seq[i] is part of A, else answer[i] = 1.  Note that even though multiple answers may exist, you may return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: seq = "(()())"
 * Output: [0,1,1,1,1,0]
 * Example 2:
 *
 * Input: seq = "()(())()"
 * Output: [0,0,0,1,1,0,1,1]
 *
 *
 * Constraints:
 *
 * 1 <= seq.size <= 10000
 * */


private fun main() {
    //maxDepthAfterSplit("(()())").printArr()
    //maxDepthAfterSplit("()(())()").printArr()
    maxDepthAfterSplit("((()))").printArr()
}

// disregard this solution, misunderstood problem
private fun maxDepthAfterSplit0(seq: String): IntArray {
    val res = IntArray(seq.length) { 0 }
    var nestingLevel = 0
    var open = true
    for (i in 1 until seq.length) {
        if (seq[i] == '(') {
            if (open) {
                nestingLevel++
            }
            open = true
        } else {
            if (!open) {
                nestingLevel--
            }
            open = false
        }
        res[i] =  nestingLevel
    }

    return res
}

private fun maxDepthAfterSplit1(seq: String): IntArray {
    val res = IntArray(seq.length) { 0 }
    val qA = ArrayDeque<Char>()
    val qB = ArrayDeque<Char>()

    for (i in seq.indices) {
        if (qA.isEmpty()) {
            if (seq[i] == '(') {
                qA.addLast(seq[i])
                res[i] = 0
                continue
            }
        }

        if (qB.isEmpty()) {
            if (seq[i] == '(') {
                qB.addLast(seq[i])
                res[i] = 1
                continue
            }
        }
        val curr = seq[i]
        if (curr == '(') {
            if (qA.size < qB.size) {
                qA.addLast(curr)
                res[i] = 0
            } else {
                qB.addLast(curr)
                res[i] = 1
            }
        } else {
            val lastAVal = qA.lastOrNull()
            val lastBVal = qB.lastOrNull()

            if (lastAVal == '(' && lastBVal == '(') {
                if (qA.size < qB.size) {
                    res[i] = 0
                    qA.removeLast()
                } else {
                    res[i] = 1
                    qB.removeLast()
                }
            } else if (lastAVal == '(') {
                res[i] = 0
                qA.removeLast()
            } else if (lastBVal == '(') {
                res[i] = 1
                qB.removeLast()
            }
        }
    }

    return res
}

private fun maxDepthAfterSplit(seq: String): IntArray {
    val res = IntArray(seq.length) { 0 }
    var aBalance = 0
    var bBalance = 0

    for (i in seq.indices) {
        val curr = seq[i]
        when(curr) {
            '(' -> {
                if (aBalance >= bBalance) {
                    bBalance++
                    res[i] = 1
                } else {
                    aBalance++
                }
            }
            ')' -> {
                if (aBalance >= bBalance) {
                    aBalance--
                } else {
                    bBalance--
                    res[i] = 1
                }
            }
        }
    }

    return res
}
