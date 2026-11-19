/**
 * Given an array of integers arr[]. You have to find the Inversion Count of the array. Inversion count is the number of pairs of elements (i, j) such that i < j and arr[i] > arr[j].
 *
 * Examples:
 *
 * Input: arr[] = [2, 4, 1, 3, 5]
 * Output: 3
 * Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
 * Input: arr[] = [2, 3, 4, 5, 6]
 * Output: 0
 * Explanation: As the sequence is already sorted so there is no inversion count.
 * Input: arr[] = [10, 10, 10]
 * Output: 0
 * Explanation: As all the elements of array are same, so there is no inversion count.
 * Constraints:
 *
 * 1 ≤ arr.size() ≤ 105
 * 1 ≤ arr[i] ≤ 104
 * */

fun main() {
    val testCases = listOf(
        intArrayOf(5) to 0,
        intArrayOf(2, 1) to 1,
        intArrayOf(5, 4, 3, 2, 1) to 10,
        intArrayOf(3, 1, 2, 3, 1) to 5,
        intArrayOf(1, 3, 2, 3, 1) to 4,
        intArrayOf(10000, 1, 10000, 1) to 3,
        intArrayOf(2, 3, 4, 5, 1) to 4,
        intArrayOf(5, 1, 2, 3, 4) to 4
    )

    for ((arr, expected) in testCases) {
        val actual = inversionCount(arr.copyOf())
        check(actual == expected) { "Failed for ${arr.contentToString()}: expected $expected, got $actual" }
    }
    println("All test cases passed!")
}

private fun inversionCount(nums: IntArray): Int {
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
    var tmpIdx = start
    var i = start
    var j = mid + 1
    var swaps = 0
    while (i <= mid && j <= end) {
        if (arr[i] <= arr[j]) {
            buffer[tmpIdx++] = arr[i++]
        } else {
            swaps += mid + 1 - i
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