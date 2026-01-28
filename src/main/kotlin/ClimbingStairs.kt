/**
 *
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *
 * Constraints:
 *
 * 1 <= n <= 45
 *
 * */

fun main() {
    println(climbStairs(3))
}

fun climbStairs(n: Int): Int {
    if (n == 1) return 1

    var prev = 1
    var curr = 1

    var i = 2
    while (i <= n) {
        val next = prev + curr
        prev = curr
        curr = next
        i++
    }

    return curr
}