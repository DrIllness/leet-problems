/**
 * 3105. Longest Strictly Increasing or Strictly Decreasing Subarray
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * You are given an array of integers nums. Return the length of the longest subarray of nums which is either strictly increasing or strictly decreasing.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,3,2]
 *
 * Output: 2
 *
 * Explanation:
 *
 * The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and [1,4].
 *
 * The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4], [3,2], and [4,3].
 *
 * Hence, we return 2.
 *
 * Example 2:
 *
 * Input: nums = [3,3,3,3]
 *
 * Output: 1
 *
 * Explanation:
 *
 * The strictly increasing subarrays of nums are [3], [3], [3], and [3].
 *
 * The strictly decreasing subarrays of nums are [3], [3], [3], and [3].
 *
 * Hence, we return 1.
 *
 * Example 3:
 *
 * Input: nums = [3,2,1]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The strictly increasing subarrays of nums are [3], [2], and [1].
 *
 * The strictly decreasing subarrays of nums are [3], [2], [1], [3,2], [2,1], and [3,2,1].
 *
 * Hence, we return 3.
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * */

fun main() {
    println(longestMonotonicSubarray(intArrayOf(1,4,3,3,2)))
    println(longestMonotonicSubarray(intArrayOf(3,3,3,3)))
    println(longestMonotonicSubarray(intArrayOf(3,2,1)))
    println(longestMonotonicSubarray(intArrayOf(1,1,5)))
}

fun longestMonotonicSubarray(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    if (nums.size == 1) return 1

    var max = 1
    var up = 1
    var down = 1
    for (i in 1 until nums.size) {
        up = if (nums[i] > nums[i - 1]) {
            up + 1
        } else { 1 }
        down = if (nums[i] < nums[i - 1]) {
            down + 1
        } else { 1 }

        max = maxOf(max, up, down)
    }

    return max
}