/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as
 * descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 *
 * */

fun main() {
    val root = TreeNode(3)
    root.apply {
        left = TreeNode(5)
        left!!.left = TreeNode(6)
        left!!.right = TreeNode(2)
        left!!.right!!.right = TreeNode(4)
        left!!.right!!.left = TreeNode(7)

        right = TreeNode(1)
        right!!.left = TreeNode(0)
        right!!.right = TreeNode(8)
    }

    println(lowestCommonAncestor(root, root.left, root.left!!.right!!.left))
    println(lowestCommonAncestor(root, root.left, root.right!!.left))
    println(lowestCommonAncestor(root, root.left!!.right, root.left!!.right!!.left))
    println(lowestCommonAncestor(root, root.left!!.right, root.left!!.right!!.right))
    println(lowestCommonAncestor(root, root.left, root.left!!.right!!.left))
}

fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    var lwc = root

    fun hasTargetIn(node: TreeNode?): Boolean {
        if (node == null) return false
        if (node == p || node == q) return true

        return hasTargetIn(node.left) || hasTargetIn(node.right)
    }

    if (hasTargetIn(root?.left) && hasTargetIn(root?.right) || (root == p || root == q))
        return lwc
    else if (hasTargetIn(root?.right))
        return lowestCommonAncestor(root?.right, p, q)
    else
        return lowestCommonAncestor(root?.left, p, q)
}

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    fun findTarget(node: TreeNode?): TreeNode? {
        if (node == null) return null
        if (node == p || node == q) return node

        val left = findTarget(node.left)
        val right = findTarget(node.right)

        if (left != null && right != null) { return node }

        return left ?: right
    }

    return findTarget(root)
}