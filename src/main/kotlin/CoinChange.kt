/**
 * 322. Coin Change
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 *
 * */

fun main() {
    println(coinChange(intArrayOf(1, 2, 5), 11))
    println(coinChange(intArrayOf(2), 3))
    println(coinChange(intArrayOf(1), 0))
    println(coinChange(intArrayOf(3, 25, 10, 2), 14))
    println(coinChange(intArrayOf(186,419,83,408), 6249))

}

fun coinChange(coins: IntArray, amount: Int): Int {
    val max = amount + 1
    val dp = IntArray(amount + 1) { max }
    dp[0] = 0
    for (i in 1..amount) {
        for (c in coins) {
            if (c <= i) {
                dp[i] = minOf(dp[i], dp[i - c] + 1)
            }
        }
    }

    return if (dp[amount] == max) -1 else dp[amount]
}