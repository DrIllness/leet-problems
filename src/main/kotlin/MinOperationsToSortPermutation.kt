import kotlin.math.abs

/**
 * 3942. Minimum Operations to Sort a Permutation
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an integer array nums of length n, where nums is a permutation of the integers from 0 to n - 1.
 *
 * You may perform only the following operations:
 *
 * Reverse the entire array.
 * Rotate Left by One: Move the first element to the end of the array, and rest elements to left by one position.
 * Return an integer denoting the minimum number of operations required to sort the array in increasing order.
 * If it is not possible to sort the array using only the given operations, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,2,1]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Rotate Left by one: [2, 1, 0]
 * Reverse the array: [0, 1, 2]
 * The array becomes sorted in 2 operations, which is minimal
 *
 * Example 2:
 *
 * Input: nums = [1,0,2]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Reverse the array: [2, 0, 1]
 * Rotate Left by one: [0, 1, 2]
 * The array becomes sorted in 2 operations, which is minimal.
 *
 * Example 3:
 *
 * Input: nums = [2,0,1,3]
 *
 * Output: -1
 *
 * Explanation:
 *
 * It is impossible to reach [2, 0, 1, 3]. Thus, the answer is -1.
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
    println(minOperations(intArrayOf(0,2,1)))
    println(minOperations(intArrayOf(1,0,2)))
    println(minOperations(intArrayOf(2,0,1,3)))
    println(minOperations(intArrayOf(1,0)))
}

// doesnt work
private fun minOperations2(nums: IntArray): Int {
    val n = nums.size
    val diffs = IntArray(n) { 0 }
    val increasing = BooleanArray(n)

    var peaks = 0
    var directionChanges = 0
    var increasingCount = 0
    increasing[0] = true

    for (i in 1 until nums.size) {
        val d = abs(nums[i] - nums[i - 1])
        val isInc = nums[i] > nums[i - 1]
        diffs[i] = d
        increasing[i] = isInc
        if (d > 1) peaks++

        if (isInc) {
            increasingCount++
        }
        if (increasing[i] != increasing[i-1]) directionChanges++
    }


    if (peaks > 1 || directionChanges > 2) return -1
    if (peaks == 0 && nums[0] == 0) return 0
    if (peaks == 0 && increasingCount == 0) return 1

    var permutationCount = 0
    for (d in diffs) {
        if (d == 1) continue
        else permutationCount = d
    }


    return permutationCount
}

private fun minOperations(nums: IntArray): Int {
    val n = nums.size
    if (n <= 1) return 0

    var ascDrops = 0
    var descDrops = 0
    var zeroIdx = -1

    for (i in 0 until n) {
        if (nums[i] == 0) zeroIdx = i

        val next = nums[(i + 1) % n]
        if (nums[i] > next) ascDrops++
        if (nums[i] < next) descDrops++
    }

    val isAscendingRing = (ascDrops == 1)
    val isDescendingRing = (descDrops == 1)

    if (!isAscendingRing && !isDescendingRing) return -1

    return if (isAscendingRing) {
        minOf(zeroIdx, n - zeroIdx + 2)
    } else {
        minOf(zeroIdx + 2, n - zeroIdx)
    }
}