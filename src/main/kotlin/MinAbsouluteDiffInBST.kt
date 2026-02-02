import kotlin.math.abs
import kotlin.math.min

/**
 * 530. Minimum Absolute Difference in BST
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 104].
 * 0 <= Node.val <= 105
 *
 * */

fun main() {
    val root = TreeNode(236)
    root.left = TreeNode(104)
    root.left?.right = TreeNode(227)
    root.right = TreeNode(701)
    root.right?.right = TreeNode(911)
    println(getMinimumDifference(root))
}

fun getMinimumDifference(root: TreeNode?): Int {
    if (root == null) return 0
    var prev: Int? = null
    var minDiff = Int.MAX_VALUE

    fun inOrder(node: TreeNode?) {
        if (node == null) return

        inOrder(node.left)

        prev?.let {
            val diff = node.`val` - it
            if (diff < minDiff) {
                minDiff = diff
            }
        }
        prev = node.`val`

        inOrder(node.right)
    }

    inOrder(root)

    return minDiff
}