import java.util.TreeSet

/**
 *Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix
 *  such that its sum is no larger than k.
 *
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 *
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 *
 * Follow up: What if the number of rows is much larger than the number of columns?
 * */

fun main() {
    println(maxSumSubmatrix(arrayOf(intArrayOf(1, 0, 1), intArrayOf(0, -2, 3)), 2))
    println(maxSumSubmatrix(arrayOf(intArrayOf(2, 2, -1)), 3))
}

private fun maxSumSubmatrix(matrix: Array<IntArray>, k: Int): Int {
    val temp = IntArray(matrix.size)
    val pre = IntArray(matrix.size)
    var max = Int.MIN_VALUE

    for (c1 in 0 until matrix[0].size) {
        for (c2 in c1 until matrix[0].size) {

            for (rowIdx in matrix.indices) {
                temp[rowIdx] += matrix[rowIdx][c2]
            }

            val treeSet = TreeSet<Int>().apply { add(0) }
            for (rowIdx in 0 until matrix.size) {
                pre[rowIdx] = if (rowIdx == 0) temp[rowIdx] else temp[rowIdx] + pre[rowIdx - 1]
                treeSet.ceiling(pre[rowIdx] - k)?.let { ceilVal ->
                    max = maxOf(max, pre[rowIdx] - ceilVal)
                }
                treeSet.add(pre[rowIdx])
            }
            pre.fill(0)
        }
        temp.fill(0)
    }


    return max
}