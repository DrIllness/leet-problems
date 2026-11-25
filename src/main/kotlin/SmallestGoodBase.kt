import java.math.BigInteger

/**
 * 483. Smallest Good Base
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Given an integer n represented as a string, return the smallest good base of n.
 *
 * We call k >= 2 a good base of n, if all digits of n base k are 1's.
 *
 *
 *
 * Example 1:
 *
 * Input: n = "13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 * Example 2:
 *
 * Input: n = "4681"
 * Output: "8"
 * Explanation: 4681 base 8 is 11111.
 * Example 3:
 *
 * Input: n = "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 *
 *
 * Constraints:
 *
 * n is an integer in the range [3, 1018].
 * n does not contain any leading zeros.
 *
 * Seen this question in a real interview before?
 * 1/6
 * Yes
 * No
 * Accepted
 * 35,966/74.6K
 * Acceptance Rate
 * 48.2%
 *
 * */

fun main() {
    println(smallestGoodBase("13"))
    println(smallestGoodBase("4681"))
    println(smallestGoodBase("1000000000000000000"))
}

private fun smallestGoodBase(n: String): String {
    var bigN = n.toBigInteger()


    fun baseGivenPositions(positions: Int): BigInteger {
        var low = BigInteger.TWO
        var high = bigN

        while (low <= high) {
            val mid = low + (high - low) / BigInteger.TWO
            var res = BigInteger.ZERO
            for (i in 0 until positions) {
                res += mid.pow(i)
            }
            if (res > bigN) {
                high = mid - BigInteger.ONE
            } else if (res < bigN) {
                low = mid + BigInteger.ONE
            } else {
                return mid
            }
        }

        return BigInteger.ZERO
    }

    var base = bigN
    for (i in 60 downTo 2) {
        val base = baseGivenPositions(i)
        if (base > BigInteger.ZERO) {
            return base.toString()
        }
    }

    return base.toString()
}
