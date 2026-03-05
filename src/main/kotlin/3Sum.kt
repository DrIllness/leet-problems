import java.util.*


/**
 * 15. 3Sum
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * */

fun main() {
    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
    println(threeSum(intArrayOf(0, 1, 1)))
    println(threeSum(intArrayOf(0,0,0)))
    println(threeSum(intArrayOf(-100,-70,-60,110,120,130,160)))
    println(threeSum(intArrayOf(-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0)))
    println(threeSum(intArrayOf(0,0,0,0)))
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val ans: MutableList<List<Int>> = ArrayList()
    Arrays.sort(nums)

    for (i in 0 until nums.size - 2) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        var j = i + 1
        var k: Int = nums.size - 1
        while (j < k) {
            val sum = nums[i] + nums[j] + nums[k]
            if (sum == 0) {
                ans.add(listOf(nums[i], nums[j], nums[k]))

                while (j < k && nums[j] == nums[j + 1]) {
                    j++
                }
                while (j < k && nums[k] == nums[k - 1]) {
                    k--
                }

                j++
                k--
            } else if (sum < 0) {
                j++
            } else {
                k--
            }
        }
    }
    return ans
}