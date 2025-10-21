/**Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1],
 * then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21

https://leetcode.com/problems/reverse-integer/description/
 **/


fun main(args: Array<String>) {
    println(reverse(-123))
    println(reverse(-321))
    println(reverse(421333123))
}

fun reverse(x: Int): Int {
    var x = x
    var result = 0
    while (x != 0) {
        val tail = x % 10
        if ((result > Int.MAX_VALUE / 10) || (result < Int.MIN_VALUE / 10)) {
            return 0
        }
        result = result * 10 + tail
        x /= 10
    }
    return result
}