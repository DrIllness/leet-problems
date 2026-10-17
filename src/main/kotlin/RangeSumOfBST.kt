/**938. Range Sum of BST
Easy
Topics
premium lock icon
Companies
Given the root node of a binary search tree and two integers low and high,
return the sum of values of all nodes with a value in the inclusive range [low, high].



Example 1:


Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
Example 2:


Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.


Constraints:

The number of nodes in the tree is in the range [1, 2 * 104].
1 <= Node.val <= 105
1 <= low <= high <= 105
All Node.val are unique.*/

fun main() {
    val root = TreeNode(10)
    root.apply {
        right = TreeNode(15)
        right?.right = TreeNode(18)
        right?.left = TreeNode(13)

        left = TreeNode(5)
        left?.right = TreeNode(7)
        left?.right?.left = TreeNode(6)
        left?.left = TreeNode(3)
        left?.left?.left = TreeNode(1)
    }

    println(rangeSumBST2(root, 6, 10))
}

// general, for binary tree
private fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
    if (root == null) return 0
    var nodeVal = 0
    val left = rangeSumBST(root.left, low, high)
    if (root.`val` in low..high) {
        nodeVal = root.`val`
    }
    val right = rangeSumBST(root.right, low, high)

    return left + nodeVal + right
}


private fun rangeSumBST2(root: TreeNode?, low: Int, high: Int): Int {
    var sum = 0
    fun inorder(node: TreeNode?) {
        if (node == null) return
        if (node.`val` in low..high) {
            val num = node.`val`
            sum += num
            inorder(node.left)
            inorder(node.right)
        } else if (node.`val` <= low) {
            inorder(node.right)
        } else {
            inorder(node.left)
        }
        return
    }
    inorder(root)
    return sum
}