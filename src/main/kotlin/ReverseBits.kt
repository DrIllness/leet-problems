/**
 * 190. Reverse Bits
 *
 * Reverse bits of a given 32 bits signed integer.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 43261596
 *
 * Output: 964176192
 *
 * Explanation:
 *
 * Integer	Binary
 * 43261596	0000_0010_1001_0100_0001_1110_1001_1100
 * 964176192	00111001011110000010100101000000
 * Example 2:
 *
 * Input: n = 2147483644
 *
 * Output: 1073741822
 *
 * Explanation:
 *
 * Integer	Binary
 * 2147483644	01111111111111111111111111111100
 * 1073741822	00111111111111111111111111111110
 *
 *
 * Constraints:
 *
 * 0 <= n <= 231 - 2
 * n is even.
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 * */

fun main() {
    println(reverseBits(50))
}

fun reverseBits(n: Int): Int {
    var reverse = 0
    val left = n and (0xFFFF shl 16)
    val right = n and 0xFFFF
    var i = 0
    var shiftStep = 31
    while (i < 16) {
        reverse = reverse or ((right shl shiftStep) and (1 shl (31-i)))
        reverse = reverse or ((left shr shiftStep) and (1 shl (i)))

        shiftStep -= 2
        i++
    }
    return reverse
}