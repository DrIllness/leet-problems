import kotlin.math.abs
import kotlin.math.pow

/**
 * 3727. Maximum Alternating Sum of Squares
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an integer array nums. You may rearrange the elements in any order.
 *
 * The alternating score of an array arr is defined as:
 *
 * score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
 * Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 *
 * Output: 12
 *
 * Explanation:
 *
 * A possible rearrangement for nums is [2,1,3], which gives the maximum alternating score among all possible rearrangements.
 *
 * The alternating score is calculated as:
 *
 * score = 22 - 12 + 32 = 4 - 1 + 9 = 12
 *
 * Example 2:
 *
 * Input: nums = [1,-1,2,-2,3,-3]
 *
 * Output: 16
 *
 * Explanation:
 *
 * A possible rearrangement for nums is [-3,-1,-2,1,3,2], which gives the maximum alternating score among all possible rearrangements.
 *
 * The alternating score is calculated as:
 *
 * score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -4 * 104 <= nums[i] <= 4 * 104
 *
 * */

fun main() {
    println(maxAlternatingSum3(intArrayOf(1, 2, 3)))
    println(maxAlternatingSum3(intArrayOf(1,-1,2,-2,3,-3)))
}

private fun maxAlternatingSum3(nums: IntArray): Long {
    for ((i, v) in nums.withIndex()) {
        val a = abs(v)
        nums[i] = a
    }

    nums.sort()

    var res = 0L
    var right = nums.size - 1
    var left = 0

    while (left <= right) {
        if (left == right) {
            res += nums[left] * nums[left]
            left++
            right--
            continue
        }
        res -= nums[left] * nums[left]
        res += nums[right] * nums[right]
        left++
        right--
    }

    return res
}

private fun maxAlternatingSum2(nums: IntArray): Long {
    var max = Int.MIN_VALUE
    val absMap = hashMapOf<Int, Int>()
    for ((_, v) in nums.withIndex()) {
        val a = abs(v)
        absMap[a] = (absMap[a]?.let { it + 1}) ?: 1
        max = maxOf(max, a)
    }

    var oddNumCount =  nums.size / 2
    var evenNumCount = if (nums.size % 2 == 1) oddNumCount + 1 else oddNumCount

    var res = 0L
    var j = max
    var i = 0

    while (evenNumCount > 0) {
        if (absMap[j]?.let { it > 0 } ?: false) {
            absMap[j] = absMap[j]!! - 1
            res += j.toLong() * j.toLong()
            evenNumCount--
        } else {
            j--
        }
    }

    while (oddNumCount > 0) {
        if (absMap[i]?.let { it > 0 } ?: false) {
            absMap[i] = absMap[i]!! - 1
            res -= i.toLong() * i.toLong()
            oddNumCount--
        } else {
            i++
        }
    }

    return res
}

private fun maxAlternatingSum(nums: IntArray): Long {
    var max = Int.MIN_VALUE
    val absArr = IntArray((4 * 10.0.pow(4.0)).toInt() + 1) { 0 }
    for ((_, v) in nums.withIndex()) {
        val a = abs(v)
        absArr[a] += 1
        max = maxOf(max, a)
    }

    var oddNumCount =  nums.size / 2
    var evenNumCount = if (nums.size % 2 == 1) oddNumCount + 1 else oddNumCount

    var res = 0L
    var j = max
    var i = 0

    while (evenNumCount > 0) {
        if (absArr[j] > 0) {
            absArr[j]--
            res += j.toLong() * j.toLong()
            evenNumCount--
        } else {
            j--
        }
    }

    while (oddNumCount > 0) {
        if (absArr[i] > 0) {
            absArr[i]--
            res -= i.toLong() * i.toLong()
            oddNumCount--
        } else {
            i++
        }
    }

    return res
}