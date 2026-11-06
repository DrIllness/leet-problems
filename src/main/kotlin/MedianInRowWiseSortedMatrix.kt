/**
 *
 * Median in a Row-Wise Sorted Matrix
 * Difficulty: MediumAccuracy: 55.05%Submissions: 189K+Points: 4
 * Given a row-wise sorted matrix mat[][], where the number of rows and columns is always odd. Return the median of the matrix.
 *
 * Examples:
 *
 * Input: mat[][] = [[1, 3, 5], [2, 6, 9], [3, 6, 9]]
 * Output: 5
 * Explanation: Sorting matrix elements gives us [1, 2, 3, 3, 5, 6, 6, 9, 9]. Hence, 5 is median.
 * Input: mat[][] = [[2, 4, 9], [3, 6, 7], [4, 7, 10]]
 * Output: 6
 * Explanation: Sorting matrix elements gives us [2, 3, 4, 4, 6, 7, 7, 9, 10]. Hence, 6 is median.
 * Input: mat = [[3], [4], [8]]
 * Output: 4
 * Explanation: Sorting matrix elements gives us [3, 4, 8]. Hence, 4 is median.
 * Constraints:
 *
 * 1 ≤ mat.size(), mat[i].size() ≤ 400
 * 1 ≤ mat[i][j] ≤ 2*103
 *
 * */

fun main() {
    println(
        median(
            arrayOf(
                intArrayOf(1,3,5),
                intArrayOf(2,6,9),
                intArrayOf(3,6,9)
            )
        )
    )
    println(
        median(
            arrayOf(
                intArrayOf(2,4,9),
                intArrayOf(3,6,7),
                intArrayOf(4,7,10)
            )
        )
    )
}
private fun median(mat: Array<IntArray>): Int {
    // find min and max
    var right = Int.MIN_VALUE
    var left = Int.MAX_VALUE
    for (rIdx in 0 until mat.size) {
        right = maxOf(right, mat[rIdx][mat[0].size - 1])
        left = minOf(left, mat[rIdx][0])
    }

    fun countNumsLessThanMedCandidate(candidate: Int): Int {
        var counter = 0
        val cols = mat[0].size
        for (ridx in mat.indices) {
            var rowLeft = 0
            var rowRight = cols - 1
            while (rowLeft <= rowRight) {
                val mid = rowLeft + (rowRight - rowLeft) / 2
                if (mat[ridx][mid] <= candidate) {
                    rowLeft = mid + 1
                } else {
                    rowRight = mid - 1
                }
            }
            counter += rowLeft
        }
        return counter
    }

    val median = (mat[0].size * mat.size) / 2 + 1
    while (left <= right) {
        val mid = left + (right - left) / 2
        val num = countNumsLessThanMedCandidate(mid)
        if (num < median) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }


   return left
}
