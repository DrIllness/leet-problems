/**
 * 3411. Maximum Subarray With Equal Products
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an array of positive integers nums.
 *
 * An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
 *
 * prod(arr) is the product of all elements of arr.
 * gcd(arr) is the GCD of all elements of arr.
 * lcm(arr) is the LCM of all elements of arr.
 * Return the length of the longest product equivalent subarray of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,1,1,1]
 *
 * Output: 5
 *
 * Explanation:
 *
 * The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1, 1, 1]) = 2, gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.
 *
 * Example 2:
 *
 * Input: nums = [2,3,4,5,6]
 *
 * Output: 3
 *
 * Explanation:
 *
 * The longest product equivalent subarray is [3, 4, 5].
 *
 * Example 3:
 *
 * Input: nums = [1,2,3,1,4,5,1]
 *
 * Output: 5
 *
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10
 *
 * Seen this question in a real interview before?
 * 1/6
 * Yes
 * No
 * Accepted
 * 57,287/104.6K
 * Acceptance Rate
 * 54.8%
 *
 * */

fun main() {
    println(maxLength(intArrayOf(2,3,4,5,6)))
    println(maxLength(intArrayOf(1,2,1,2,1,1,1)))
    println(maxLength(intArrayOf(1,2,3,1,4,5,1)))
}

fun maxLength(nums: IntArray): Int {
    var maxLen = 1
    val maxLimit = 25200 // max LCM(1..10) = 2520, max GCD = 10

    for (i in nums.indices) {
        var prod = 1
        var g = 0
        var l = 1

        for (j in i until nums.size) {
            val x = nums[j]
            prod *= x
            if (prod > maxLimit) break

            g = gcd(g, x)
            l = (l / gcd(l, x)) * x

            if (prod == l * g) {
                maxLen = maxOf(maxLen, j - i + 1)
            }
        }
    }
    return maxLen
}

private fun gcd(a: Int, b: Int): Int {
    var x = a
    var y = b
    while (y != 0) {
        val temp = x % y
        x = y
        y = temp
    }
    return x
}