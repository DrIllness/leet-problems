/**
 * 719. Find K-th Smallest Pair Distance
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * The distance of a pair of integers a and b is defined as the absolute difference between a and b.
 *
 * Given an integer array nums and an integer k, return the kth smallest distance among
 * all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,1], k = 1
 * Output: 0
 * Explanation: Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Example 2:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 0
 * Example 3:
 *
 * Input: nums = [1,6,1], k = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 104
 * 0 <= nums[i] <= 106
 * 1 <= k <= n * (n - 1) / 2
 * */

fun main() {
    println(smallestDistancePair(intArrayOf(62,100,4), 2))
    println(smallestDistancePair(intArrayOf(1,3,1), 1))
    println(smallestDistancePair(intArrayOf(1,1,1), 2))
    println(smallestDistancePair(intArrayOf(1,6,1), 3))
}

private fun smallestDistancePair(nums: IntArray, k: Int): Int {
    nums.sort()

    fun calcLessOrEqual(cand: Int): Int {
        var i = 0
        var j = 1
        var count = 0
        while (i < j && j < nums.size) {
            if (nums[j] - nums[i] <= cand) {
                count += j - i
                j++
                continue
            } else {
                i++
                if (i == j) {
                    j++
                }
            }
        }

        return count
    }

    var low = 0
    var high = nums[nums.size - 1] - nums[0]
    var res = -1
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (calcLessOrEqual(mid) >= k) {
            res = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return res
}