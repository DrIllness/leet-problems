/**
 * 130. Surrounded Regions
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 *
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 *
 *
 *
 * Example 1:
 *
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * Explanation:
 *
 *
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 *
 * Example 2:
 *
 * Input: board = [["X"]]
 *
 * Output: [["X"]]
 *
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 *
 * */

fun main() {
    val input = arrayOf(
        charArrayOf('X','X','X','X'),
        charArrayOf('X','O','O','X'),
        charArrayOf('X','X','O','X'),
        charArrayOf('X','O','X','X')
    )

    println("before")
    input.printArr()
    //solve2(input)
    println("after")
    input.printArr()

    val input2 = arrayOf(
        charArrayOf('O','O'),
        charArrayOf('O','O')
    )

    println("before")
    input2.printArr()
    //solve2(input2)
    println("after")
    input2.printArr()

    val input3 = arrayOf(
        charArrayOf('X','O','X','O','X','O'),
        charArrayOf('O','X','O','X','O','X'),
        charArrayOf('X','O','X','O','X','O'),
        charArrayOf('O','X','O','X','O','X')
    )
    solve2(input3)
    input3.printArr()

}

fun solve(board: Array<CharArray>) {
    if (board.size <= 1 ) return
    val potentiallySurrounded = hashSetOf<Pair<Int, Int>>()

    fun isSurrounded(row: Int, column: Int): Boolean {
        if (row < 0 || column < 0 || row >= board.size || column >= board[0].size)
            return false

        if (board[row][column] == 'X') return true

        board[row][column] = 'X'
        potentiallySurrounded.add(row to column)

        return (isSurrounded(row - 1, column) && isSurrounded(row
                    + 1, column) && isSurrounded(row, column + 1) && isSurrounded(row, column - 1))
    }

    fun restore() {
        for (i in potentiallySurrounded) {
            board[i.first][i.second] = 'O'
        }
    }

    var i = 0
    while (i < board.size) {
        var j = 0
        while (j < board[0].size) {
            if (isSurrounded(i, j).not()) {
                restore()
            }
            potentiallySurrounded.clear()
            j++
        }
        i++
    }

}

private fun solve2(board: Array<CharArray>) {
    if (board.size <= 1 ) return

    fun findSafeNeighbour(row: Int, column: Int) {
        if (row < 0 || column < 0 || row >= board.size || column >= board[0].size || board[row][column] != 'O')
            return

        board[row][column] = 'S'

        findSafeNeighbour(row - 1, column)
        findSafeNeighbour(row + 1, column)
        findSafeNeighbour(row, column + 1)
        findSafeNeighbour(row, column - 1)

    }

    val rows = board.size
    val cols = board[0].size
    for (i in 0 until rows) {
        findSafeNeighbour(i, 0)
        findSafeNeighbour(i, cols - 1)
    }
    for (j in 0 until cols) {
        findSafeNeighbour(0, j)
        findSafeNeighbour( rows - 1, j)
    }

    var i = 0
    var j = 0
    while (i < board.size) {
        while (j < board[0].size) {
            when(board[i][j]) {
                'S' -> board[i][j]= 'O'
                else -> board[i][j] = 'X'
            }
            j++
        }
        j = 0
        i++
    }

}

private fun Array<CharArray>.printArr() {
    for (i in this) {
        for (j in i) {
            print(" $j")
        }
        print("\n")
    }
}