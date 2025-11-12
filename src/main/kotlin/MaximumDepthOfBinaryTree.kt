/**
 * 104. Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 *
 * */

fun main() {
    val root = TreeNode(3)
    root.apply {
        left = TreeNode(9)
        right = TreeNode(20)
        right?.left = TreeNode(15)
        right?.right = TreeNode(7)
        println(maxDepth(root))
    }

    val root2 = TreeNode(1)
    root2.apply {
        right = TreeNode(2)
        println(maxDepth(root2))
    }
}

fun maxDepth(root: TreeNode?): Int {
    var maxDepth = 0
    fun dfsMaxDepth(node: TreeNode?, depth: Int) {
        node?.let {
            if (depth > maxDepth) maxDepth = depth
            dfsMaxDepth(node.left, depth + 1)
            dfsMaxDepth(node.right, depth + 1)
        }
    }

    dfsMaxDepth(root, 1)

    return maxDepth
}