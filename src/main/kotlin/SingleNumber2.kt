/**
 * 137. Single Number II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 * */

fun main() {
    println(singleNumber2(intArrayOf(0,1,0,1,0,1,99)))
    println(singleNumber2(intArrayOf(-2,-2,1,1,4,1,4,4,-4,-2)))
    println(singleNumber2(intArrayOf(2,2,3,2)))
}

// 32*n + 1 = O(n), O space O(1)
fun singleNumber2(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]

    var i = 0
    var number = 0

    while (i < 32) {
        var n = 0
        var bitcount = 0
        while (n < nums.size) {
            bitcount += if ((nums[n] and (1 shl i)) != 0) 1 else 0
            n++
        }
        if (bitcount % 3 > 0) number = number or (1 shl i)
        i++
    }

    return number
}