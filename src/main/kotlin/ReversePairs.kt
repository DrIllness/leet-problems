/**
 * 493. Reverse Pairs
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where:
 *
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -231 <= nums[i] <= 231 - 1
 *
 * */

fun main() {
    println(reversePairs(intArrayOf(1,3,2,3,1)))
    println(reversePairs(intArrayOf(2,4,3,5,1)))
}

private fun reversePairs(nums: IntArray): Int {
    val buffer = IntArray(nums.size)
    return sortWithCount(0, (nums.size - 1), nums, buffer)
}

private fun sortWithCount(start: Int, end: Int, arr: IntArray, buffer: IntArray): Int {
    if (start >= end) return 0
    var count = 0
    val mid = start + (end - start) / 2
    count += sortWithCount(start, mid, arr, buffer)
    count += sortWithCount(mid + 1, end, arr, buffer)
    count += mergeWithCount(start, mid, end, arr, buffer)

    return count
}

private fun mergeWithCount(start: Int, mid: Int, end: Int, arr: IntArray, buffer: IntArray): Int {
    var swaps = 0
    var j = mid + 1
    for (i in start..mid) {
        while (j <= end && arr[i].toLong() > 2L * arr[j]) {
            j++
        }
        swaps += j - (mid + 1)
    }

    var tmpIdx = start
    var i = start
    j = mid + 1
    while (i <= mid && j <= end) {
        if (arr[i] <= arr[j]) {
            buffer[tmpIdx++] = arr[i++]
        } else {
            buffer[tmpIdx++] = arr[j++]
        }
    }
    while (i <= mid) { buffer[tmpIdx++] = arr[i++] }
    while (j <= end) { buffer[tmpIdx++] = arr[j++] }

    tmpIdx = start
    while (tmpIdx <= end) {
        arr[tmpIdx] = buffer[tmpIdx]
        tmpIdx++
    }

    return swaps
}