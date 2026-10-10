import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

/**
 * 2333. Minimum Sum of Squared Difference
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
 *
 * The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.
 *
 * You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times.
 * Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
 *
 * Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.
 *
 * Note: You are allowed to modify the array elements to become negative integers.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
 * Output: 579
 * Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0.
 * The sum of square difference will be: (1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579.
 * Example 2:
 *
 * Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
 * Output: 43
 * Explanation: One way to obtain the minimum sum of square difference is:
 * - Increase nums1[0] once.
 * - Increase nums2[2] once.
 * The minimum of the sum of square difference will be:
 * (2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
 * Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 0 <= nums1[i], nums2[i] <= 105
 * 0 <= k1, k2 <= 109
 *
 * */

fun main() {
    //println(minSumSquareDiff(intArrayOf(1,4,10,12), intArrayOf(5,8,6,9), 1, 1))
    //println(minSumSquareDiff(intArrayOf(1,2,3,4), intArrayOf(2,10,20,19), 0, 0))
    //println(minSumSquareDiff(intArrayOf(1,4,10,12), intArrayOf(5,8,6,9), 10, 5))
    println(minSumSquareDiff(intArrayOf(1,4,10,12), intArrayOf(5,8,6,9), 10, 5))
}

private fun minSumSquareDiff(nums1: IntArray, nums2: IntArray, k1: Int, k2: Int): Long {
    val diffCount = IntArray(10.0.pow(5.0).toInt() + 1) { 0 }
    var diffSum = 0L
    var maxDif = 0
    var budget = k1 + k2
    for (i in nums1.indices) {
        val d = abs(nums1[i] - nums2[i])
        diffCount[d] += 1

        diffSum += d
        maxDif = maxOf(d, maxDif)
    }

    if (diffSum <= budget) return 0

    while (budget > 0 && maxDif > 0) {
        val reduce = min(budget, diffCount[maxDif])
        budget -= reduce

        diffCount[maxDif] -= reduce
        diffCount[maxDif - 1] += reduce
        maxDif--
    }

    var res = 0L
    for (i in diffCount.indices) {
        res += (i.toLong() * i.toLong()) * diffCount[i]
    }
    return res
}