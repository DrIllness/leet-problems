/**
 * 101. Symmetric Tree
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Could you solve it both recursively and iteratively?
 * */

fun main() {
    val symRoot = TreeNode(1)
    symRoot.apply {
        left = TreeNode(2)
        right = TreeNode(2)
        left!!.left = TreeNode(3)
        left!!.right = TreeNode(4)
        left!!.left!!.left = TreeNode(5)
        left!!.left!!.right = TreeNode(6)
        left!!.right!!.left = TreeNode(7)
        left!!.right!!.right = TreeNode(8)
        right!!.right = TreeNode(3)
        right!!.left = TreeNode(4)
        right!!.left!!.left = TreeNode(8)
        right!!.left!!.right = TreeNode(7)
        right!!.right!!.left = TreeNode(6)
        right!!.right!!.right = TreeNode(5)
    }

    val nonsymRoot = TreeNode(1)
    nonsymRoot.apply {
        left = TreeNode(2)
        right = TreeNode(2)
        left!!.left = null
        left!!.right = TreeNode(4)
        right!!.left = null
        right!!.right = TreeNode(4)
    }
    println(isSymmetric(symRoot))
    println(isSymmetric(nonsymRoot))

    println(isSymmetricLoop(symRoot))
    println(isSymmetricLoop(nonsymRoot))
}

fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return true

    fun isSym(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        return (left?.`val` == right?.`val`) && isSym(left?.left, right?.right) && isSym(left?.right, right?.left)
    }

    return isSym(root.left, root.right)
}


fun isSymmetricLoop(root: TreeNode?): Boolean {
    if (root == null) return true
    val queueRight = ArrayDeque<TreeNode?>()
    val queueLeft = ArrayDeque<TreeNode?>()

    queueLeft.add(root.left)
    queueRight.add(root.right)

    while (queueLeft.isNotEmpty() && queueRight.isNotEmpty()) {
        val left = queueLeft.removeFirst()
        val right = queueRight.removeFirst()

        if (left?.`val` != right?.`val`) return false

        left?.let {
            queueLeft.add(it.left)
            queueLeft.add(it.right)
        }

        right?.let {
            queueRight.add(it.right)
            queueRight.add(it.left)
        }

    }

    return true
}