/**
 * 3886. Sum of Sortable Integers
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an integer array nums of length n.
 *
 * An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially performing the following operations:
 *
 * Partition nums into consecutive subarrays of length k.
 * Cyclically rotate each subarray independently any number of times to the left or to the right.
 * Return an integer denoting the sum of all possible sortable integers k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,2]
 *
 * Output: 3
 *
 * Explanation:
 *
 * For n = 3, possible divisors are 1 and 3.
 * For k = 1: each subarray has one element. No rotation can sort the array.
 * For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
 * Only k = 3 is sortable. Hence, the answer is 3.
 * Example 2:
 *
 * Input: nums = [7,6,5]
 *
 * Output: 0
 *
 * Explanation:
 *
 * For n = 3, possible divisors are 1 and 3.
 * For k = 1: each subarray has one element. No rotation can sort the array.
 * For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
 * No k is sortable. Hence, the answer is 0.
 * Example 3:
 *
 * Input: nums = [5,8]
 *
 * Output: 3
 *
 * Explanation:
 *
 * For n = 2, possible divisors are 1 and 2.
 * Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.
 *
 *
 * Constraints:
 *
 * 1 <= n == nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * */

fun main() {
    println(sortableIntegers(intArrayOf(3,1,2)))
    println(sortableIntegers(intArrayOf(7,6,5)))
    println(sortableIntegers(intArrayOf(5,8)))
}

fun sortableIntegers(nums: IntArray): Int {
    val n = nums.size

    val prefDrop = IntArray(n)
    for (i in 0 until n - 1) {
        prefDrop[i + 1] = prefDrop[i] + if (nums[i] > nums[i + 1]) 1 else 0
    }

    val prefMax = IntArray(n)
    prefMax[0] = nums[0]
    for (i in 1 until n) {
        prefMax[i] = maxOf(prefMax[i - 1], nums[i])
    }

    val suffMin = IntArray(n)
    suffMin[n - 1] = nums[n - 1]
    for (i in n - 2 downTo 0) {
        suffMin[i] = minOf(suffMin[i + 1], nums[i])
    }

    fun isValid(k: Int): Boolean {
        var boundary = k
        while (boundary < n) {
            if (prefMax[boundary - 1] > suffMin[boundary]) {
                return false
            }
            boundary += k
        }

        var l = 0
        while (l < n) {
            val r = l + k - 1
            val internalDrops = prefDrop[r] - prefDrop[l]
            val circularDrop = if (nums[r] > nums[l]) 1 else 0

            if (internalDrops + circularDrop > 1) {
                return false
            }
            l += k
        }

        return true
    }

    var sum = 0
    for (k in 1..n) {
        if (n % k == 0 && isValid(k)) {
            sum += k
        }
    }

    return sum
}