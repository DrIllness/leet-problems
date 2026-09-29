/**
 * A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
 *
 * It is ().
 * It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
 * It can be written as (A), where A is a valid parentheses string.
 * You are given an m x n matrix of parentheses grid.
 * A valid parentheses string path in the grid is a path satisfying all of the following conditions:
 *
 * The path starts from the upper left cell (0, 0).
 * The path ends at the bottom-right cell (m - 1, n - 1).
 * The path only ever moves down or right.
 * The resulting parentheses string formed by the path is valid.
 * Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * Output: true
 * Explanation: The above diagram shows two possible paths that form valid parentheses strings.
 * The first path shown results in the valid parentheses string "()(())".
 * The second path shown results in the valid parentheses string "((()))".
 * Note that there may be other valid parentheses string paths.
 * Example 2:
 *
 *
 * Input: grid = [[")",")"],["(","("]]
 * Output: false
 * Explanation: The two possible paths form the parentheses strings "))(" and ")((".
 * Since neither of them are valid parentheses strings, we return false.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] is either '(' or ')'.
 *
 * */

fun main() {
    println(
        hasValidPath2(
            arrayOf(
                charArrayOf('(', '(', '('),
                charArrayOf(')', '(', ')'),
                charArrayOf('(', '(', ')'),
                charArrayOf('(', '(', ')')
            )
        )
    )
}

// exceed memory/time limits
fun hasValidPath1(grid: Array<CharArray>): Boolean {
    if (grid[0][0] == ')') return false

    val combinations = mutableListOf<MutableList<Char>>()

    fun addNewCombo(list: MutableList<Char>) {
        combinations.add(list)
    }

    fun dfs(list: MutableList<Char>, y: Int, x: Int) {
        val canMoveRight = (x + 1 < grid[0].size)
        val canMovDown = (y + 1 < grid.size)
        if (canMovDown && canMoveRight) {
            // forking down
            //println("forking at x:$x y:$y")

            val newListForDown = list.toMutableList()
            addNewCombo(newListForDown)
            newListForDown.add(grid[y + 1][x])
            dfs(newListForDown, y + 1, x)

            // reusing list for right
            list.add(grid[y][x + 1])
            dfs(list, y, x + 1)

        } else if (canMoveRight) {
            //println("moving only right at x:$x y:$y")
            list.add(grid[y][x + 1])
            dfs(list, y, x + 1)
        } else if (canMovDown) {
            //println("moving only down at x:$x y:$y")
            list.add(grid[y + 1][x])
            dfs(list, y + 1, x)
        } else {
            //println("reaching end at x:$x y:$y")
            // reached the end
            return
        }
    }


    val list = mutableListOf<Char>()
    list.add(grid[0][0])
    dfs(list, 0, 0)

    fun isValidCombo(list: List<Char>): Boolean {
        val queue = ArrayDeque<Char>()
        for (c in list) {
            if (c == '(') {
                queue.addLast(')')
                continue
            } else {
                if (queue.isEmpty()) {
                    return false
                } else {
                    queue.removeLast()
                }
            }
        }
        // validate combo
        return queue.isEmpty()
    }

    for (c in combinations) {
        val isValid = isValidCombo(c)
        if (isValid) return true
    }
    return false
}

fun hasValidPath2(grid: Array<CharArray>): Boolean {
    if (grid[0][0] == ')') return false
    val ySize = grid.size
    val xSize = grid[0].size
    val maxBalance = (ySize + xSize) / 2
    val visited = Array(ySize) { Array(xSize) { BooleanArray(maxBalance + 1) } }

    var hasPath = false
    fun dfs(balance: Int, y: Int, x: Int) {
        val canMoveRight = (x+1 < grid[0].size)
        val canMovDown = (y+1 < grid.size)
        var newBalance = balance

        println("at x:$x y:$y, char:${grid[y][x]}")
        if (grid[y][x] == '(') newBalance++ else newBalance--
        if (newBalance < 0 || newBalance > maxBalance) return
        if (visited[y][x][newBalance]) return

        visited[y][x][newBalance] = true
        if (canMoveRight) { dfs(newBalance,y, x+1) }
        if (canMovDown) { dfs(newBalance,y+1, x) }

        if (!canMovDown && !canMoveRight) {
            println("reached end")
            if (newBalance == 0) hasPath = true
            return
        }
    }

    dfs(0, 0, 0)

    return hasPath
}