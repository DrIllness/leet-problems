/**
 * Given a sorted array arr[] (following 0-based indexing) and a number target, find the lower bound of the target in this given array.
 *
 * The lower bound of a number is defined as the smallest index in the sorted array where the element is greater than or
 * equal to the given number.
 * If all the elements in the given array are smaller than the target, the lower bound will be the length of the array.
 * Examples :
 *
 * Input:  arr[] = [2, 3, 7, 10, 11, 11, 25], target = 9
 * Output: 3
 * Explanation: 3 is the smallest index in arr[] where element (arr[3] = 10) is greater than or equal to 9.
 * Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 11
 * Output: 4
 * Explanation: 4 is the smallest index in arr[] where element (arr[4] = 11) is greater than or equal to 11.
 * Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 100
 * Output: 7
 * Explanation: As no element in arr[] is greater than 100, return the length of array.
 * Constraints:
 *
 * 1 ≤ arr.size(), arr[i], target ≤ 106
 *
 * */

data class TestCase(val arr: IntArray, val target: Int, val expected: Int)

private fun main() {
    val testCases = listOf(
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 9, 3),
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 11, 4),
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 100, 7),
        TestCase(intArrayOf(1, 2, 4, 4, 5), 0, 0) // Edge case: target smaller than all elements
    )

    for ((arr, target, expected) in testCases) {
        println("Target: $target | Expected: $expected | Actual: ${lowerBound(arr, target)}")
    }
}

private fun lowerBound(nums: IntArray, target: Int): Int {
    var res = nums.size
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = (low + high) / 2

        if (nums[mid] >= target) {
            res = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return res
}