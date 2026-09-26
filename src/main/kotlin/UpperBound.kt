/**
 * Given a sorted array arr[] and a number target, the task is to find the upper bound of the target in this given array.
 * The upper bound of a number is defined as the smallest index in the sorted array where the element is greater than the given number.
 *
 * Note: If all the elements in the given array are smaller than or equal to the target, the upper bound will be the length of the array.
 *
 * Examples :
 *
 * Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 9
 * Output: 3
 * Explanation: 3 is the smallest index in arr[], at which element (arr[3] = 10) is larger than 9.
 * Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 11
 * Output: 6
 * Explanation: 6 is the smallest index in arr[], at which element (arr[6] = 25) is larger than 11.
 * Input: arr[] = [2, 3, 7, 10, 11, 11, 25], target = 100
 * Output: 7
 * Explanation: As no element in arr[] is greater than 100, return the length of array.
 * Constraints:
 * 1 ≤ arr.size() ≤ 106
 * 1 ≤ arr[i] ≤ 106
 * 1 ≤ target ≤ 106
 *
 */

private fun main() {
    val testCases = listOf(
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 9, 3),
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 11, 6),
        TestCase(intArrayOf(2, 3, 7, 10, 11, 11, 25), 100, 7),
    )

    for ((arr, target, expected) in testCases) {
        println("Target: $target | Expected: $expected | Actual: ${upperBound(arr, target)}")
    }
}

fun upperBound(nums: IntArray, target: Int): Int {
    var res = nums.size
    var low = 0
    var high = nums.size - 1
    while (low <= high) {
        val mid = low + (high - low) / 2

        if (nums[mid] > target) {
            res = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return res
}