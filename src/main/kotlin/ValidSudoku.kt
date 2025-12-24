/**
 * 36. Valid Sudoku
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * */

fun main() {
    println(
        isValidSudoku(
            arrayOf(
                charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )
        )
    )

    println(
        isValidSudoku(
            arrayOf(
                charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )
        )
    )

    println(
        isValidSudoku(
            arrayOf(
                charArrayOf('.', '.', '4', '.', '.', '.', '6', '3', '.'),
                charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.'),
                charArrayOf('5', '.', '.', '.', '.', '.', '.', '9', '.'),
                charArrayOf('.', '.', '.', '5', '6', '.', '.', '.', '.'),
                charArrayOf('4', '.', '3', '.', '.', '.', '.', '.', '1'),
                charArrayOf('.', '.', '.', '7', '.', '.', '.', '.', '.'),
                charArrayOf('.', '.', '.', '5', '.', '.', '.', '.', '.'),
                charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.'),
                charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.')
            )
        )
    )
}

fun isValidSudoku(board: Array<CharArray>): Boolean {
    val setOfNumsHorizontal = hashSetOf<Char>()
    val setOfNumsVertical = hashSetOf<Char>()
    for (i in 0..8) {
        for (j in 0..8) {
            if (board[i][j] != '.') {
                if (setOfNumsHorizontal.contains(board[i][j]))
                    return false
                else
                    setOfNumsHorizontal.add(board[i][j])
            }
            if (board[j][i] != '.') {
                if (setOfNumsVertical.contains(board[j][i]))
                    return false
                else
                    setOfNumsVertical.add(board[j][i])
            }
        }
        setOfNumsVertical.clear()
        setOfNumsHorizontal.clear()
    }


    val setOfNumsSquare = hashSetOf<Char>()
    var offsetHor = 0
    var offsetVert = 0

    for (k in 0..8) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i + offsetVert][j + offsetHor] != '.') {
                    if (setOfNumsSquare.contains(board[i + offsetVert][j + offsetHor]))
                        return false
                    else
                        setOfNumsSquare.add(board[i + offsetVert][j + offsetHor])
                }
            }
        }
        setOfNumsSquare.clear()
        offsetHor += 3
        if (offsetHor % 9 == 0) {
            offsetHor = 0
            offsetVert += 3
        }
    }

    return true
}