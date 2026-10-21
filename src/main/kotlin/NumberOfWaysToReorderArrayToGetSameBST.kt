/**
 *
 * 1569. Number of Ways to Reorder Array to Get Same BST
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search
 * tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways
 * to reorder nums so that the constructed BST is identical to that formed from the original array nums.
 *
 * For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child.
 * The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
 * Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [2,1,3]
 * Output: 1
 * Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums
 * which will yield the same BST.
 * Example 2:
 *
 *
 * Input: nums = [3,4,5,1,2]
 * Output: 5
 * Explanation: The following 5 arrays will yield the same BST:
 * [3,1,2,4,5]
 * [3,1,4,2,5]
 * [3,1,4,5,2]
 * [3,4,1,2,5]
 * [3,4,1,5,2]
 * Example 3:
 *
 *
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: There are no other orderings of nums that will yield the same BST.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= nums.length
 * All integers in nums are distinct.
 *
 * */

fun main() {
    println(numOfWays(intArrayOf(2,1,3)))
    println(numOfWays(intArrayOf(3,4,5,1,2)))
    println(numOfWays(intArrayOf(1,2,3)))
}

private fun numOfWays(nums: IntArray): Int {
    val n = nums.size
    val mod = 1_000_000_007L
    val comb = Array(n + 1) { LongArray(n + 1) }
    for (i in 0..n) {
        comb[i][0] = 1L
        for (j in 1..i) {
            comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % mod
        }
    }

    fun dfs(list: List<Int>): Long {
        if (list.size <= 2) return 1L

        val root = list[0]
        val left = ArrayList<Int>()
        val right = ArrayList<Int>()

        for (i in 1 until list.size) {
            val v = list[i]
            if (v < root) left.add(v) else right.add(v)
        }

        val leftWays = dfs(left)
        val rightWays = dfs(right)
        val interleaveWays = comb[left.size + right.size][left.size]

        val subWays = (leftWays * rightWays) % mod
        return (subWays * interleaveWays) % mod
    }

    val totalWays = dfs(nums.toList())
    return ((totalWays - 1 + mod) % mod).toInt()
}