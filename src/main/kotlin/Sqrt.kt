import kotlin.math.round

/**
 * 69. Sqrt(x)
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 *
 * Example 1:
 *
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 * Example 2:
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
 *
 *
 * Constraints:
 *
 * 0 <= x <= 231 - 1
 * */

fun main() {
    //println(mySqrt(4))
    //println(mySqrt(8))
    //println(mySqrt(Int.MAX_VALUE - 1))
    //println(mySqrt(2147395599))
    //println(mySqrt(1))
    println(mySqrt(5))
}

fun mySqrt(x: Int): Int {
    if (x == 1 || x == 0) return x

    var left = 0
    var right = x
    var ans = -1
    while (left <= right) {
        val mid = (left + (right - left) / 2)
        val midSquare = mid.toLong() * mid.toLong()
        if (midSquare == x.toLong()) {
            return mid
        } else if (midSquare < x.toLong()) {
            ans = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    return ans
}