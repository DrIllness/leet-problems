/**
 * 668. Kth Smallest Number in Multiplication Table
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Nearly everyone has used the Multiplication Table.
 * The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 *
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 * Example 2:
 *
 *
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 3 * 104
 * 1 <= k <= m * n
 *
 * */

fun main() {
    println(findKthNumber(3, 3, 5))
    println(findKthNumber(2, 3, 6))
}

private fun findKthNumber(m: Int, n: Int, k: Int): Int {
    fun countSmallerOrEqual(kCan: Int): Int {
        var count = 0
        val maxRow = minOf(kCan, m)
        for (i in 1..maxRow) {
            val rowMid = kCan / i
            count += minOf(rowMid, n)
        }

        return count
    }

    var res = 0
    var low = 0
    var high = 3 * 100000000
    while (low <= high) {
        val mid = low + (high - low) / 2
        val smallerOrEqual = countSmallerOrEqual(mid)
        if (smallerOrEqual >= k) {
            res = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return res
}