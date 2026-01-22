/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = charArrayOf(
 *   charArrayOf('1','1','1','1','0'),
 *   charArrayOf('1','1','0','1','0'),
 *   charArrayOf('1','1','0','0','0'),
 *   charArrayOf('0','0','0','0','0')
 * )
 * Output: 1
 * Example 2:
 *
 * Input: grid = charArrayOf(
 *   charArrayOf('1','1','0','0','0'),
 *   charArrayOf('1','1','0','0','0'),
 *   charArrayOf('0','0','1','0','0'),
 *   charArrayOf('0','0','0','1','1')
 * )
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == gridcharArrayOf(i).length
 * 1 <= m, n <= 300
 * gridcharArrayOf(i)charArrayOf(j) is '0' or '1'.
 *
 * */

fun main() {
    val input = arrayOf(
        charArrayOf('1','1','1','1','0'),
        charArrayOf('1','1','0','1','0'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','0','0','0')
    )

    println(numIslands(input))
    
}

fun numIslands(grid: Array<CharArray>): Int {
    val rows = grid.size
    val cols = grid[0].size
    var islands = 0

    fun dfs(r: Int, c: Int) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == '0') {
            return
        }

        grid[r][c] = '0'

        dfs(r + 1, c)
        dfs(r - 1, c)
        dfs(r, c + 1)
        dfs(r, c - 1)
    }

    for (r in 0 until rows) {
        for (c in 0 until cols) {
            if (grid[r][c] == '1') {
                islands++
                dfs(r, c)
            }
        }
    }

    return islands
}