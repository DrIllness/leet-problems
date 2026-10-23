/**
 * 3644. Maximum K to Sort a Permutation
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [0..n - 1].
 *
 * You may swap elements at indices i and j only if nums[i] AND nums[j] == k,
 * where AND denotes the bitwise AND operation and k is a non-negative integer.
 *
 * Return the maximum value of k such that the array can be sorted in non-decreasing order using any number of such swaps.
 * If nums is already sorted, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,3,2,1]
 *
 * Output: 1
 *
 * Explanation:
 *
 * Choose k = 1. Swapping nums[1] = 3 and nums[3] = 1 is allowed since nums[1] AND nums[3] == 1, resulting in a sorted permutation: [0, 1, 2, 3].
 *
 * Example 2:
 *
 * Input: nums = [0,1,3,2]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Choose k = 2. Swapping nums[2] = 3 and nums[3] = 2 is allowed since nums[2] AND nums[3] == 2, resulting in a sorted permutation: [0, 1, 2, 3].
 *
 * Example 3:
 *
 * Input: nums = [3,2,1,0]
 *
 * Output: 0
 *
 * Explanation:
 *
 * Only k = 0 allows sorting since no greater k allows the required swaps where nums[i] AND nums[j] == k.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n == nums.length <= 105
 * 0 <= nums[i] <= n - 1
 * nums is a permutation of integers from 0 to n - 1.
 *
 * */

fun main() {
    println(sortPermutation(intArrayOf(0,3,2,1)))
    println(sortPermutation(intArrayOf(0,1,3,2)))
    println(sortPermutation(intArrayOf(3,2,1,0)))
    println(sortPermutation(intArrayOf(0,1,3,2,4,6,5)))
}

private fun sortPermutation2(nums: IntArray): Int {
    if (nums.size <= 1) return 0
    if (nums[0] != 0) return 0

    val misplacedNumToIdx = hashMapOf<Int, Int>()
    for (n in 0 until nums.size) {
        if (nums[n] != n) {
            misplacedNumToIdx[nums[n]] = n
        }
    }
    if (misplacedNumToIdx.isEmpty()) return 0

    val visited = BooleanArray(nums.size) { false }
    var k = Int.MAX_VALUE
    for ((n, idx) in nums.withIndex()) {
        if (n == idx) {
            visited[idx] = true
            continue
        }

        var andRes = n
        var nextIdx = n
        while (!visited[nextIdx]) {
            val nextN = nums[nextIdx]
            andRes = andRes and nextN

            visited[nextIdx] = true
            nextIdx = nextN
        }

        k = andRes and k
    }

    return k
}

private fun sortPermutation(nums: IntArray): Int {
    if (nums.size <= 1) return 0

    var k = Int.MAX_VALUE
    var isSorted = true
    for (n in 0 until nums.size) {
        if (nums[n] != n) {
            isSorted = false
            k = k and nums[n]
        }
    }
    return if (isSorted) 0 else k
}