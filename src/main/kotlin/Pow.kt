import kotlin.math.abs

/**
 * 50. Pow(x, n)
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * Constraints:
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -104 <= xn <= 104
 * */

fun main() {
    //println(myPow(2.0, -2))
    //println(myPow(0.44528, 0))
    println(myPow(2.00000, -2147483648))
}


fun myPow(x: Double, n: Int): Double {
    val N = n.toLong()
    if (N < 0) {
        return 1.0 / fastPow(x, -N)
    }
    return fastPow(x, N)
}

private fun fastPow(x: Double, n: Long): Double {
    if (n == 0L) return 1.0

    val half = fastPow(x, n / 2)

    return if (n % 2 == 0L) {
        half * half
    } else {
        half * half * x
    }
}