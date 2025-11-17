/**
 * 226. Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Example 2:
 *
 *
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * */

fun main() {
    val root = TreeNode(4)
    root.apply {
        left = TreeNode(2)
        left!!.left = TreeNode(1)
        left!!.right = TreeNode(3)
        right = TreeNode(7)
        right!!.left = TreeNode(6)
        right!!.right = TreeNode(9)
    }

    invertTree(root)
    root
}

fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return null

    invertTree(root.left)
    invertTree(root.right)

    val swap = root.left
    root.left = root.right
    root.right = swap

    return root
}

