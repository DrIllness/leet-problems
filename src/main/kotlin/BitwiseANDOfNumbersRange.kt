/**
 * 201. Bitwise AND of Numbers Range
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 *
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= left <= right <= 2^31 - 1
 * */

fun main() {
    println(rangeBitwiseAnd(5, 7))
    println(rangeBitwiseAnd(2, 7))
    println(rangeBitwiseAnd(0, 0))
    println(rangeBitwiseAnd(1, 2147483647))
}

fun rangeBitwiseAnd(left: Int, right: Int): Int {
    var m = right
    while (m > left) m = m and (m - 1)
    return m and left
}