/**
 * 1901. Find a Peak Element II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
 *
 * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: mat = [[1,4],[3,2]]
 * Output: [0,1]
 * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
 * Example 2:
 *
 *
 *
 * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * Output: [1,1]
 * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 105
 * No two adjacent cells are equal.
 * */

fun main() {
/*    findPeakGrid(
        arrayOf(
            intArrayOf(0,1,0),
            intArrayOf(0,0,1),
            intArrayOf(1,0,0),
        )
    ).printArr()

    findPeakGrid(
        arrayOf(
            intArrayOf(0,20,15),
            intArrayOf(21,30,14),
            intArrayOf(7,16,32)
        )
    ).printArr()*/

    findPeakGrid(
        arrayOf(
            intArrayOf(41,8,2,48,18),
            intArrayOf(16,15,9,7,44),
            intArrayOf(48,35,6,38,28),
            intArrayOf(3,2,14,15,33),
            intArrayOf(39,36,13,46,42)
        )
    ).printArr()
}

// fails
private fun findPeakGrid1(mat: Array<IntArray>): IntArray {
    for ((rIdx, row) in mat.withIndex()) {
        var left = 0
        var right = mat.size - 1
        while (left < right) {
            val mid = left + (right - left) / 2

            val safeRight = if (mid + 1 >= row.size) -1 else row[mid + 1]
            val c = mat[rIdx] [mid] - safeRight
            when {
                c < 0 -> {
                    left = mid + 1
                    continue
                }
                c > 0 -> {
                    right = mid
                    val candidate = mat[rIdx][mid]
                    val safeTop = if (rIdx - 1 < 0) -1 else mat[rIdx - 1] [mid]
                    val safeBottom = if (rIdx + 1 >= mat.size) -1 else mat[rIdx + 1] [mid]
                    if (candidate > safeBottom && candidate > safeTop) {
                        return intArrayOf(rIdx, mid)
                    }
                }
            }
        }

    }

    return intArrayOf(-1,-1)
}

private fun findPeakGrid(mat: Array<IntArray>): IntArray {
    var left = 0
    var right = mat[0].size - 1
    while (left <= right) {
        var mid = left + (right - left) / 2

        var rowIdx = 0
        var max = -1
        var maxIdx = -1
        while (rowIdx < mat.size) {
            val candidate = mat[rowIdx][mid]
            if (candidate > max) {
                max = candidate
                maxIdx = rowIdx
            }
            rowIdx++
        }

        val safeLeft = if (mid - 1 >= 0) mat[maxIdx][mid - 1] else -1
        val safeRight = if (mid + 1 < mat[0].size) mat[maxIdx][mid + 1] else -1
        if (max > safeRight && max > safeLeft) {
            return intArrayOf(maxIdx, mid)
        } else if (safeRight > max) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    return intArrayOf(-1,-1)
}