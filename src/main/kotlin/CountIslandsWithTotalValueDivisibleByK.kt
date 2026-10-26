/**
 * 3619. Count Islands With Total Value Divisible by K
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an m x n matrix grid and a positive integer k. An island is a group of positive integers
 * (representing land) that are 4-directionally connected (horizontally or vertically).
 *
 * The total value of an island is the sum of the values of all cells in the island.
 *
 * Return the number of islands with a total value divisible by k.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
 *
 * Output: 2
 *
 * Explanation:
 *
 * The grid contains four islands. The islands highlighted in blue have a total value that is divisible by 5,
 * while the islands highlighted in red do not.
 *
 * Example 2:
 *
 *
 * Input: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
 *
 * Output: 6
 *
 * Explanation:
 *
 * The grid contains six islands, each with a total value that is divisible by 3.
 *
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 105
 * 0 <= grid[i][j] <= 106
 * 1 <= k <= 106
 *
 * */

fun main() {
    println(countIslands(arrayOf(
        intArrayOf(0,2,1,0,0),
        intArrayOf(0,5,0,0,5),
        intArrayOf(0,0,1,0,0),
        intArrayOf(0,1,4,7,0),
        intArrayOf(0,2,0,0,8),
    ),5))

    println(
        countIslands(
            arrayOf(
                intArrayOf(3,0,3,0),
                intArrayOf(0,3,0,3),
                intArrayOf(3,0,3,0),
            ), 3
        )
    )
}

private fun countIslands(grid: Array<IntArray>, k: Int): Int {
    // up right down left, y to x
    val direction = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun iValue(y: Int, x: Int): Long {
        var iVal = 0L

        if (grid[y][x] == 0) return 0

        iVal = grid[y][x].toLong()
        grid[y][x] = 0
        for (d in 0..3) {
            val newY = y + direction[d].first
            val newX = x + direction[d].second
            if (newY >= 0 && newY < grid.size && newX >= 0 && newX < grid[0].size) {
                iVal += iValue(newY, newX)
            }
        }

        return iVal
    }

    var ic = 0
    for ((rIdx, row) in grid.withIndex()) {
        for ((cIdx, _) in row.withIndex()) {
            if (grid[rIdx][cIdx] == 0) {
                continue
            } else {
                val total = iValue(rIdx, cIdx)
                if (total % k.toLong() == 0L) { ic++ }
            }

        }
    }

    return ic
}