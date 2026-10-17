/**
 * 230. Kth Smallest Element in a BST
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed)
 * of all the values of the nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
 * and you need to find the kth smallest frequently, how would you optimize?
 * */

fun main() {
    val root = TreeNode(5)
    root.apply {
        right = TreeNode(6)

        left = TreeNode(3)
        left?.right = TreeNode(4)
        left?.left = TreeNode(2)
        left?.left?.left = TreeNode(1)
    }

    println(kthSmallest(root, 6))
}

private fun kthSmallest(root: TreeNode?, k: Int): Int {
    var kSmallest = 0
    var c = 0
    fun inorder(node: TreeNode?) {
        if (node == null) return

        node.left?.let {
            inorder(it)
        }
        c++
        if (c == k) {
            kSmallest = node.`val`
            return
        }
        node.right?.let {
            inorder(it)
        }

    }

    inorder(root)
    return kSmallest
}
