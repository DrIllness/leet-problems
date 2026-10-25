/**
 *1254. Number of Closed Islands
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given a 2D grid consists of 0s (land) and 1s (water).
 * An island is a maximal 4-directionally connected group of 0s and a closed island is
 * an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 *
 *
 *
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * */

fun main() {
    println(
        closedIsland(
            arrayOf(
                intArrayOf(1,1,1,1,1,1,1),
                intArrayOf(1,0,0,0,0,0,1),
                intArrayOf(1,0,1,1,1,0,1),
                intArrayOf(1,0,1,0,1,0,1),
                intArrayOf(1,0,1,1,1,0,1),
                intArrayOf(1,0,0,0,0,0,1),
                intArrayOf(1,1,1,1,1,1,1)
            )
        )
    )

    println(
        closedIsland(
            arrayOf(
                intArrayOf(0,0,1,1,0,1,0,0,1,0),
                intArrayOf(1,1,0,1,1,0,1,1,1,0),
                intArrayOf(1,0,1,1,1,0,0,1,1,0),
                intArrayOf(0,1,1,0,0,0,0,1,0,1),
                intArrayOf(0,0,0,0,0,0,1,1,1,0),
                intArrayOf(0,1,0,1,0,1,0,1,1,1),
                intArrayOf(1,0,1,0,1,1,0,0,0,1),
                intArrayOf(1,1,1,1,1,1,0,0,0,0),
                intArrayOf(1,1,1,0,0,1,0,1,0,1),
                intArrayOf(1,1,1,0,1,1,0,1,1,0),
            )
        )
    )
}

private fun closedIsland(grid: Array<IntArray>): Int {
    val water = 1
    val land = 0
    val maxX = grid[0].size
    val maxY = grid.size

    // up right down left
    val directions = arrayOf(0 to -1, 1 to 0, 0 to 1, -1 to 0)
    fun flipLand(x: Int, y: Int): Boolean {
        var onlyWater = true
        grid[y][x] = water
        for (d in directions) {
            val newX = x + d.first
            val newy = y + d.second
            if (newX in 0 until maxX && newy in 0 until maxY) {
                if (grid[newy][newX] == land) {
                    val res = flipLand(newX, newy)
                    onlyWater = res && onlyWater
                }
            } else {
                onlyWater = false
            }
        }

        return onlyWater
    }
    var ic = 0
    for ((rIdx, row) in grid.withIndex()) {
        for ((cIdx, col) in row.withIndex()) {
            if (col == water) {
                continue
            } else {
                if (flipLand(cIdx, rIdx)) ic++
            }
        }
    }

    return ic
}