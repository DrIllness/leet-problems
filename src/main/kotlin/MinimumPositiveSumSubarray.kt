/**
 * 3364. Minimum Positive Sum Subarray
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an integer array nums and two integers l and r. Your task is to find the minimum sum of a
 * subarray whose size is between l and r (inclusive) and whose sum is greater than 0.
 *
 * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3, -2, 1, 4], l = 2, r = 3
 *
 * Output: 1
 *
 * Explanation:
 *
 * The subarrays of length between l = 2 and r = 3 where the sum is greater than 0 are:
 *
 * [3, -2] with a sum of 1
 * [1, 4] with a sum of 5
 * [3, -2, 1] with a sum of 2
 * [-2, 1, 4] with a sum of 3
 * Out of these, the subarray [3, -2] has a sum of 1, which is the smallest positive sum. Hence, the answer is 1.
 *
 * Example 2:
 *
 * Input: nums = [-2, 2, -3, 1], l = 2, r = 3
 *
 * Output: -1
 *
 * Explanation:
 *
 * There is no subarray of length between l and r that has a sum greater than 0. So, the answer is -1.
 *
 * Example 3:
 *
 * Input: nums = [1, 2, 3, 4], l = 2, r = 4
 *
 * Output: 3
 *
 * Explanation:
 *
 * The subarray [1, 2] has a length of 2 and the minimum sum greater than 0. So, the answer is 3.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= l <= r <= nums.length
 * -1000 <= nums[i] <= 1000
 *
 * */

fun main() {
    println(minimumSumSubarray(listOf(3, -2, 1, 4), 2, 3)) // 1
    println(minimumSumSubarray(listOf(-2, 2, -3, 1), 2, 3))
    println(minimumSumSubarray(listOf(1, 2, 3, 4), 2, 4))
    println(minimumSumSubarray(listOf(25, 14), 1, 1))
    println(minimumSumSubarray(listOf(17, 13), 1, 2))
}

private fun minimumSumSubarray(nums: List<Int>, l: Int, r: Int): Int {
    val pre = IntArray(nums.size + 1)
    pre[0] = 0
    for (i in 1 .. nums.size) {
        pre[i] = pre[i - 1] + nums[i - 1]
    }

    var minSum = -1
    for (p in l until pre.size) {
        for (s in l..r) {
            if (p - s >= 0) {
                val segmentSum = pre[p] - pre[p - s]
                if (segmentSum > 0) {
                    minSum = if (minSum == -1) {
                        segmentSum
                    } else {
                        minOf(minSum, segmentSum)
                    }
                }
            }
        }
    }

    return minSum
}