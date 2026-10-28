/**
 * 3882. Minimum XOR Path in a Grid
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given a 2D integer array grid of size m * n.
 *
 * You start at the top-left cell (0, 0) and want to reach the bottom-right cell (m - 1, n - 1).
 *
 * At each step, you may move either right or down.
 *
 * The cost of a path is defined as the bitwise XOR of all the values in the cells along that path, including the start and end cells.
 *
 * Return the minimum possible XOR value among all valid paths from (0, 0) to (m - 1, n - 1).
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,2],[3,4]]
 *
 * Output: 6
 *
 * Explanation:
 *
 * There are two valid paths:
 *
 * (0, 0) → (0, 1) → (1, 1) with XOR: 1 XOR 2 XOR 4 = 7
 * (0, 0) → (1, 0) → (1, 1) with XOR: 1 XOR 3 XOR 4 = 6
 * The minimum XOR value among all valid paths is 6.
 *
 * Example 2:
 *
 * Input: grid = [[6,7],[5,8]]
 *
 * Output: 9
 *
 * Explanation:
 *
 * There are two valid paths:
 *
 * (0, 0) → (0, 1) → (1, 1) with XOR: 6 XOR 7 XOR 8 = 9
 * (0, 0) → (1, 0) → (1, 1) with XOR: 6 XOR 5 XOR 8 = 11
 * The minimum XOR value among all valid paths is 9.
 *
 * Example 3:
 *
 * Input: grid = [[2,7,5]]
 *
 * Output: 0
 *
 * Explanation:
 *
 * There is only one valid path:
 *
 * (0, 0) → (0, 1) → (0, 2) with XOR: 2 XOR 7 XOR 5 = 0
 * The XOR value of this path is 0, which is the minimum possible.
 *
 *
 *
 * Constraints:
 *
 * 1 <= m == grid.length <= 1000
 * 1 <= n == grid[i].length <= 1000
 * m * n <= 1000
 * 0 <= grid[i][j] <= 1023​
 *
 * */

fun main() {

    println(minCost(
        arrayOf(
            intArrayOf(1,2),
            intArrayOf(3,4)
        )
    ))

    println(
        minCost(
            arrayOf(
                intArrayOf(6,7),
                intArrayOf(5,8)
            )
        )
    )


    println(
        minCost(
            arrayOf(
                intArrayOf(2,7,5)
            )
        )
    )
}

// time limit exceeds
private fun minCost1(grid: Array<IntArray>): Int {
    var minXor = Int.MAX_VALUE
    val endX = grid[0].size - 1
    val endY = grid.size - 1

    fun calcXor(y: Int, x: Int, curXor: Int) {
        val xor = curXor xor grid[y][x]
        if (y == endY && x == endX) {
            minXor = minOf(xor, minXor)
            return
        }

        if (x < endX) {
            calcXor(y, x+1, xor)
        }
        if (y < endY) {
            calcXor(y+1, x, xor)
        }
    }

    calcXor(0,0, 0)

    return minXor
}

private fun minCost(grid: Array<IntArray>): Int {
    val n = grid[0].size
    var dp = Array(n) { BooleanArray(1024) }
    dp[0][0] = true

    for (row in grid) {
        for (j in 0 until n) {
            val next = BooleanArray(1024)
            for (x in 0 until 1024) {
                if (dp[j][x] || (j > 0 && dp[j - 1][x])) {
                    next[x xor row[j]] = true
                }
            }
            dp[j] = next
        }
    }

    var x = 0
    while (!dp[n - 1][x]) {
        x++
    }
    return x
}