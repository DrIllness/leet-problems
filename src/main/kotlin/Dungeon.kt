/**
 * Dungeon Problem StatementYou are trapped in a 2D dungeon and need to find the quickest way out!
 * The dungeon is composed of unit cubes which may or may not be filled with rock.
 * It takes one minute to move one unit north, south, east, or west. You cannot move diagonally,
 * and the maze is surrounded by solid rock on all sides.
 *
 * Question:
 * Is an escape possible?
 * If yes, how long will it take?
 *
 * */

val dungeon: Array<CharArray> = arrayOf(
    "S..#...".toCharArray(),
    ".#...#.".toCharArray(),
    ".#.....".toCharArray(),
    "..##...".toCharArray(),
    "#.#E...".toCharArray()
)

fun main() {
    println(shortestPath(dungeon))
}

fun shortestPath(dungeon: Array<CharArray>): Int {
    var startR = -1
    var startC = -1

    for (r in dungeon.indices) {
        for (c in dungeon[r].indices) {
            if (dungeon[r][c] == 'S') {
                startR = r
                startC = c
                break
            }
        }
        if (startR != -1) break
    }

    var steps = 0
    val movVectorC = intArrayOf(0, -1, 0, 1)
    val movVectorR = intArrayOf(-1, 0, 1, 0) // ^ < v >

    val queueC = ArrayDeque<Int>()
    val queueR = ArrayDeque<Int>()

    val visited = Array(dungeon.size) { BooleanArray(dungeon[0].size) { false } }

    queueC.addLast(startR)
    queueR.addLast(startC)
    visited[startR][startC] = true

    var currLayerSize = 1
    var nextLayerSize = 0

    while (queueC.isNotEmpty()) {
        for (i in currLayerSize downTo 1) {
            val c = queueC.removeFirst()
            val r = queueR.removeFirst()

            for (i in 0..3) {
                val newC = c + movVectorC[i]
                val newR = r + movVectorR[i]

                if (newC < 0 || newC >= dungeon[0].size || newR < 0 || newR >= dungeon.size || visited[newR][newC])
                    continue

                if (dungeon[newR][newC] == 'E')
                    return ++steps
                else if (dungeon[newR][newC] == '.') {
                    queueC.addLast(newC)
                    queueR.addLast(newR)
                    nextLayerSize++
                }

                visited[newR][newC] = true
            }
        }

        currLayerSize = nextLayerSize
        nextLayerSize = 0
        steps++
    }

    return if (steps > 0) steps else -1
}