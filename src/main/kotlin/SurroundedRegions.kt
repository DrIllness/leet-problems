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
    //solve(input)
    println("after")
    input.printArr()

    val input2 = arrayOf(
        charArrayOf('O','O'),
        charArrayOf('O','O')
    )

    println("before")
    input2.printArr()
    //solve(input2)
    println("after")
    input2.printArr()

    val input3 = arrayOf(
        charArrayOf('X','O','X','O','X','O'),
        charArrayOf('O','X','O','X','O','X'),
        charArrayOf('X','O','X','O','X','O'),
        charArrayOf('O','X','O','X','O','X')
    )
    solve(input3)
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

private fun Array<CharArray>.printArr() {
    for (i in this) {
        for (j in i) {
            print(" $j")
        }
        print("\n")
    }
}