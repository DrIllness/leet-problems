import kotlin.math.abs

/**
 * 228. Summary Ranges
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * You are given a sorted unique integer array nums.
 *
 * A range [a,b] is the set of all integers from a to b (inclusive).
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 *
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 20
 * -231 <= nums[i] <= 231 - 1
 * All the values of nums are unique.
 * nums is sorted in ascending order.
 *
 * */

fun main() {
    println(summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)))
    println(summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9)))
    println(summaryRanges(intArrayOf(-2147483648,-2147483647,2147483647)))
    println(summaryRanges(intArrayOf(-123612687,123612688)))
}

fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) return emptyList()
    if (nums.size == 1) return listOf("${nums[0]}")

    val result = mutableListOf<String>()

    var rangeStart: Int = nums[0]
    var rangeEnd: Int = nums[0]
    var i = 1
    while (i < nums.size) {
        if ((nums[i] - 1) == rangeEnd) {
            rangeEnd = nums[i]
        } else {
            if (rangeEnd != rangeStart) {
                result.add("$rangeStart->$rangeEnd")
            } else {
                result.add("$rangeStart")
            }
            rangeStart = nums[i]
            rangeEnd = nums[i]
        }

        i++
    }
    if (rangeEnd != rangeStart) {
        result.add("$rangeStart->$rangeEnd")
    } else {
        result.add("$rangeStart")
    }

    return result
}