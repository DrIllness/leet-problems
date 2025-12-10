/**
 * 108. Convert Sorted Array to Binary Search Tree
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 *
 * Example 1:
 *
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 *
 * */

fun main() {
    val res = sortedArrayToBST(intArrayOf(-10,-3,0,5,9))
    res
}

fun sortedArrayToBST(nums: IntArray): TreeNode? {

    fun helper(start: Int, end: Int): TreeNode? {
        val mid = ((start + end) / 2)
        if (start > end) return null
        val node = TreeNode(nums[mid])
        node.left = helper(start, mid - 1)
        node.right = helper(mid + 1, end)
        return node
    }

    return helper(0, nums.size - 1)
}