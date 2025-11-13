/**
 * 100. Same Tree
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 *
 *
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in both trees is in the range [0, 100].
 * -104 <= Node.val <= 104
 * */

fun main() {
    val root = TreeNode(9)
    root.apply {
        right = TreeNode(3)
        right?.right = TreeNode(4)
        left = TreeNode(2)
        left?.right = TreeNode(5)
    }

    val root2 = TreeNode(9)
    root2.apply {
        right = TreeNode(3)
        right?.right = TreeNode(4)
        left = TreeNode(2)
        left?.right = TreeNode(5)
    }

    val root3 = TreeNode(9)
    root3.apply {
        right = TreeNode(3)
        right?.right = TreeNode(44)
        left = TreeNode(2)
        left?.right = TreeNode(11)
    }

    println(isSameTree(root, root2))
    println(isSameTree(root2, root3))
}

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    val queue1 = ArrayDeque<TreeNode?>()
    val queue2 = ArrayDeque<TreeNode?>()
    queue1.add(p)
    queue2.add(q)

    while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
        val node1 = queue1.removeFirst()
        val node2 = queue2.removeFirst()

        if (node1?.`val` != node2?.`val`) return false

        node1?.let { node ->
            queue1.add(node.right)
            queue1.add(node.left)
        }
        node2?.let { node ->
            queue2.add(node.right)
            queue2.add(node.left)
        }
    }


    return queue1.isEmpty() && queue2.isEmpty()
}