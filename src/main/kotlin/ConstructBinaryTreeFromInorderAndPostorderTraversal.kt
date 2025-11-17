/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
 * postorder is the postorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 * */

fun main() {
    val tree = buildTree(intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3))
    tree
}

private fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
    if (postorder.isEmpty()) return null

    val inorderIndex = hashMapOf<Int, Int>()
    inorder.forEachIndexed { i, v ->
        inorderIndex[v] = i
    }

    var postorderIndex = postorder.size - 1

    fun build(start: Int, end: Int): TreeNode? {
        if (start > end) return null

        val midVal = postorder[postorderIndex--]
        val root = TreeNode(midVal)
        val mid = inorderIndex[midVal]!!

        root.right = build(mid + 1, end)
        root.left = build(start, mid - 1)

        return root
    }

    return build(0, inorder.size - 1)
}