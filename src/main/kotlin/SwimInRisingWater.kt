/**
 * 778. Swim in Rising Water
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.
 *
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 *
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 *
 * */


fun main() {
    println(swimInWater(arrayOf(intArrayOf(0, 2), intArrayOf(1, 3))))
    println(
        swimInWater(
            arrayOf(
                intArrayOf(0, 1, 2, 3, 4), intArrayOf(24, 23, 22, 21, 5),
                intArrayOf(12, 13, 14, 15, 16),
                intArrayOf(11, 17, 18, 19, 20),
                intArrayOf(10, 9, 8, 7, 6)
            )
        )
    )
}

private fun swimInWater(grid: Array<IntArray>): Int {
    var minTime = 2500
    val vector = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    fun canReachGivenT(y: Int, x: Int, timeLimit: Int, visited: Array<BooleanArray>): Boolean {
        visited[y][x] = true
        if (y == grid.size - 1 && x == grid[0].size - 1 && grid[y][x] <= timeLimit) return true

        var canReach = false
        for (v in vector) {
            val newY = y+v.first
            val newX = x+v.second
            if (newY < 0 || newX < 0 || newY >= grid.size || newX >= grid[0].size || visited[newY][newX] ||
                grid[y][x] > timeLimit) {
                continue
            }

            canReach = canReach || canReachGivenT(newY, newX, timeLimit, visited)
        }
        return canReach
    }

    var low = 0
    var high = 2500
    while (low <= high) {
        val mid = low + (high - low) / 2
        val visited = Array(grid.size) { BooleanArray(grid[0].size) { false } }
        if (canReachGivenT(0, 0, mid, visited)) {
            minTime = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return minTime
}
