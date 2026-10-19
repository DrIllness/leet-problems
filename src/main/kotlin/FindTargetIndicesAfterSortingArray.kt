/**
 * 2089. Find Target Indices After Sorting Array
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given a 0-indexed integer array nums and a target element target.
 *
 * A target index is an index i such that nums[i] == target.
 *
 * Return a list of the target indices of nums after sorting nums
 * in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 * Example 2:
 *
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 * Example 3:
 *
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 * */

fun main() {
    println(targetIndices(intArrayOf(1,2,5,2,3), 2))
    println(targetIndices(intArrayOf(1,2,5,2,3), 3))
    println(targetIndices(intArrayOf(1,2,5,2,3), 5))
}

private fun targetIndices2(nums: IntArray, target: Int): List<Int> {
    nums.sort()
    val targetIndices = mutableListOf<Int>()
    for ((index, value) in nums.withIndex()) {
        if (value == target) targetIndices.add(index)
    }
    return targetIndices
}

private fun targetIndices(nums: IntArray, target: Int): List<Int> {
    var smallerCount = 0
    var targetCount = 0
    for (value in nums) {
       if (value < target) {
            smallerCount++
        } else if (value > target) {
            continue
       } else {
           targetCount++
        }
    }
    val ans = MutableList(targetCount) { 0 }
    for (i in ans.indices) { ans[i] = smallerCount++ }
    return ans
}