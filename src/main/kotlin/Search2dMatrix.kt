/**
 * 74. Search a 2D Matrix
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * */

fun main() {
    println(searchMatrix(
        arrayOf(
            intArrayOf(1,3,5,7),
            intArrayOf(10,11,16,20),
            intArrayOf(23,30,34,60)
        ),
        3
    ))
    println(searchMatrix(
        arrayOf(
            intArrayOf(1,3,5,7),
            intArrayOf(10,11,16,20),
            intArrayOf(23,30,34,60)
        ),
        13
    ))
}

private fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val rowSize = matrix[0].size
    val colSize = matrix.size
    var left = 0
    var right = rowSize * colSize - 1

    while (left <= right) {
        val mid = left + (right - left) / 2
        val elem = matrix[mid / rowSize][mid % rowSize]
        if (elem > target) {
            right = mid - 1
        } else if (elem < target) {
            left = mid + 1
        } else {
            return true
        }
    }

    return false
}