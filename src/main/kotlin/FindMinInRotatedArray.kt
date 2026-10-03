/**
 *153. Find Minimum in Rotated Sorted Array
 * Solved
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 *
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 * */

fun main() {
    println(findMin(intArrayOf(3,4,5,1,2)))
    println(findMin(intArrayOf(11,13,15,17)))
    println(findMin(intArrayOf(4,5,6,7,0,1,2)))
}

private fun findMin(nums: IntArray): Int {
    if (nums.isEmpty()) return -1
    if (nums.size == 1) return nums[0]

    var min = Int.MAX_VALUE
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        val mid = (left + (right - left) / 2)
        if (nums[left] <= nums[mid] && nums[mid] <= nums[right]) {
            min = minOf(nums[left], min)
            return min
        } else if (nums[left] > nums[mid] && nums[mid] < nums[right]) {
            min = minOf(nums[mid], min)
            right = mid - 1
        } else{
            min = minOf(nums[mid], min)
            left = mid + 1
        }
    }

    return min
}

private fun findMinR(nums: IntArray): Int {
    if (nums.isEmpty()) return -1

    if (nums.size == 1) return nums[0]

    return finMinRec(nums, 0, nums.size - 1)
}

private fun finMinRec(nums: IntArray, start: Int, end: Int): Int {
    if (nums[start] < nums[end]) return nums[start]
    if (end - 1 == start) return nums[end]

    val center = (end + start) / 2
    return if (nums[start] > nums[center]) {
        finMinRec(nums, start, center)
    } else {
        finMinRec(nums, center, end)
    }
}