import com.sun.source.tree.Tree

/**
 * 112. Path Sum
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There are two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * Example 3:
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * */

fun main() {
    val root = TreeNode(5)
    root.apply {
        root.left = TreeNode(4)
        root.left!!.left = TreeNode(11)
        root.left!!.left!!.right = TreeNode(2)
        root.left!!.left!!.left = TreeNode(7)

        root.right = TreeNode(8)
        root.right!!.left = TreeNode(13)
        root.right!!.right = TreeNode(4)
        root.right!!.right!!.right = TreeNode(1)
    }

    println(hasPathSum(root, 22))
}

fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    fun sum(root: TreeNode?, sum: Int): Boolean {
        if (root == null) return false
        return ((sum + root.`val`) == targetSum && (root.right == null) && (root.left == null)) || sum(
            root.right,
            sum + root.`val`
        ) || sum(root.left, sum + root.`val`)
    }

    return sum(root, 0)
}