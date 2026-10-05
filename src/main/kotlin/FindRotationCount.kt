/**
 * Find Rotation Count
 * Difficulty: EasyAccuracy: 23.16%Submissions: 380K+Points: 2Average Time: 20m
 * Given an increasing sorted rotated array arr[] of distinct integers.
 * The array is right-rotated k times. Find the value of k.
 *
 * Examples:
 *
 * Input: arr[] = [5, 1, 2, 3, 4]
 * Output: 1
 * Explanation: The given array is [5, 1, 2, 3, 4]. The original sorted array is [1, 2, 3, 4, 5]. We can see that the array was rotated 1 times to the right.
 * Input: arr = [1, 2, 3, 4, 5]
 * Output: 0
 * Explanation: The given array is not rotated.
 * Input: arr = [6, 9, 2, 4]
 * Output: 2
 * Explanation: The original array is [2, 4, 6, 9] and we get the above array after two rotations.
 * Constraints:
 *
 * 1 ≤ arr.size() ≤ 105
 * 1 ≤ arr[i] ≤ 107
 *
 * */

fun main() {
    println(findKRotation(intArrayOf(5, 1, 2, 3, 4)))
    println(findKRotation(intArrayOf(1, 2, 3, 4, 5)))
    println(findKRotation(intArrayOf(6, 9, 2, 4)))
}

private fun findKRotation(arr: IntArray): Int {
    var left = 0
    var right = arr.size - 1
    var min = Int.MAX_VALUE
    var minIndex = 0
    while (left <= right) {
        val mid = (left + (right - left) / 2)
        if (arr[mid] < min) {
            min = arr[mid]
            minIndex = mid
        }

        if (arr[mid] < arr[right]) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return minIndex
}