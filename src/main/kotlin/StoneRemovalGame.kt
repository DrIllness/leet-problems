/**
 *3360. Stone Removal Game
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
 *
 * Alice starts by removing exactly 10 stones on her first turn.
 * For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
 * The player who cannot make a move loses the game.
 *
 * Given a positive integer n, return true if Alice wins the game and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 *
 * Output: true
 *
 * Explanation:
 *
 * Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
 * Bob cannot remove 9 stones, so Alice wins.
 * Example 2:
 *
 * Input: n = 1
 *
 * Output: false
 *
 * Explanation:
 *
 * Alice cannot remove 10 stones, so Alice loses.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * */

fun main() {
    println(canAliceWin(12))
    println(canAliceWin(1))
    println(canAliceWin(50))
    println(canAliceWin(19))
}

fun canAliceWin(n: Int): Boolean {
    var isAliceTurn = true
    var stones = n
    var last = 10
    while (stones >= last) {
        stones -= last
        last--
        isAliceTurn = !isAliceTurn
    }

    return !isAliceTurn
}