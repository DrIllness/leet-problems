/**
 * https://codeforces.com/edu/course/2/lesson/6/5/practice/contest/285084/problem/A
 *
 *A. K-th Number in the Union of Segments
 *
 * Time limit per test: 2.0 seconds
 * Memory limit per test: 512 megabytes
 *
 * Given n segments on a number line [l_i, r_i] and an integer k.
 *
 * Consider all integers that belong to these segments.
 * If a number belongs to multiple segments, it is included as many times as there are segments containing it. All these numbers are gathered into a single array and sorted in non-decreasing order.
 *
 * Find the element at index k (0-indexed) in this sorted array.
 *
 * Input:
 * The first line contains two integers n and k:
 *
 * 1 <= n <= 50 000
 *
 * 0 <= k < sum_{i=1}^n (r_i - l_i + 1)
 *
 * Each of the following n lines contains two integers l_i and r_i:
 *
 * -10^9 <= l_i <= r_i <= 10^9
 *
 * Output:
 * Print a single integer — the value of the k-th element (0-indexed) in the sorted array.
 *
 * Examples:
 *
 * Example 1:
 * Input:
 * 3 3
 * 1 3
 * 2 4
 * 3 5
 *
 * Output:
 * 3
 *
 * Example 2:
 * Input:
 * 2 4
 * -5 -2
 * -3 1
 *
 * Output:
 * -3
 * */

fun main() {
// Example 1
    val segments1 = listOf(
        Pair(1, 3),
        Pair(5, 7)
    )
    val k1 = 4 // Expected: 6

// Example 2
    val segments2 = listOf(
        Pair(1, 4),
        Pair(3, 5)
    )
    val k2 = 3 // Expected: 3

// Example 3
    val segments3 = listOf(
        Pair(-1500000000, 1500000000)
    )
    val k3 = 1500000091 // Expected: 91

    println(kthNumberOfUnion(segments1, k1))
    println(kthNumberOfUnion(segments2, k2))
    println(kthNumberOfUnion(segments3, k3))
}

private fun kthNumberOfUnion2(segments: List<Pair<Int, Int>>, k: Int): Int {
    var left = -2_000_000_000L
    var right = 2_000_000_000L

    fun indexToCount(num: Long): Pair<Long, Long> {
        var lesserCount = 0L
        var numCount = 0L
        for (s in segments) {
            if (num >= s.first && num <= s.second) {
                lesserCount += num - s.first
                numCount++
            } else if (num > s.second) {
                lesserCount += s.second.toLong() - s.first.toLong() + 1L
            }
        }
        return lesserCount to numCount
    }

    var res = 0L
    while (left <= right) {
        val mid = left + (right - left) / 2
        val c = indexToCount(mid)

        if (c.first <= k) {
            if (c.second + c.first > k) {
                res = mid
                break
            } else {
                left = mid + 1
            }
        } else {
            right = mid - 1
        }
    }

    return res.toInt()
}

private fun kthNumberOfUnion(segments: List<Pair<Int, Int>>, k: Int): Int {
    var left = -2_000_000_000L
    var right = 2_000_000_000L

    fun countSmallerOrEquals(num: Long): Long {
        var count = 0L
        for (s in segments) {
            val left = s.first.toLong()
            val right = s.second.toLong()
            if (num in left..right) {
                count += num - left + 1L
            } else if (num > s.second) {
                count += right - left + 1L
            }
        }
        return count
    }

    var res = 0L
    while (left <= right) {
        val mid = left + (right - left) / 2
        if (countSmallerOrEquals(mid) >= k + 1L) {
            res = mid
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return res.toInt()
}