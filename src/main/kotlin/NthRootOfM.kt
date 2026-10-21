/**
 *
 * You are given 2 numbers n and m, the task is to find n√m (nth root of m). If the root is not integer then return -1.
 *
 * Examples :
 *
 * Input: n = 3, m = 8
 * Output: 2
 * Explanation: 23 = 8
 * Input: n = 3, m = 9
 * Output: -1
 * Explanation: 3rd root of 9 is not integer.
 * Input: n = 4, m = 16
 * Output: 2
 * Explanation: 24 = 16
 * Constraints:
 *
 * 1 ≤ n ≤ 9
 * 0 ≤ m ≤ 20
 * */

fun main() {
    println(nthRoot(3,8))
    println(nthRoot(3,9))
    println(nthRoot(4,16))
}

private fun nthRoot(n: Int, m: Int): Int {
    var left = 0L
    var right = m.toLong()

    fun calcPowOf(num: Long): Long {
        var loc = 1L
        for (i in 1..n) {
            loc *= num
        }
        return loc
    }

    var ans = -1L
    while (left <= right) {
        val mid = left + (right - left) / 2

        val p = calcPowOf(mid)
        if (p > m) {
            right = mid - 1
        } else if (p < m) {
            left = mid + 1
        } else {
            ans = mid
            return ans.toInt()
        }
    }

    return -1
}