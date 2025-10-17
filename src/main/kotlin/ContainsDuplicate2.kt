import kotlin.math.abs

/**
 *
 * 219. Contains Duplicate II
 *
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
 * such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * **/

fun main() {
    println(containsNearbyDuplicate(intArrayOf(1,2,3,1), 3))
    println(containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1))
    println(containsNearbyDuplicate(intArrayOf(1,2,3,1,2,3), 2))
}

fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val map = hashMapOf<Int, Int>()
    var i = 0
    while (i < nums.size) {
        val prev = map[nums[i]]
        if (prev != null) {
            if (abs(prev - i) <= k) return true
        }

        map[nums[i]] = i
        i++
    }

    return false
}
