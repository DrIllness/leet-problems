package algorithms_playground

import TreeNode
import kotlin.math.max

fun buildTree(level: Int = 1, value: Int = 1, maxLevel: Int = 11): TreeNode? {
    if (level > maxLevel) return null
    return TreeNode(value).apply {
        left = buildTree(level + 1, 2 * value, maxLevel)
        right = buildTree(level + 1, 2 * value + 1, maxLevel)
    }
}

val tree11Levels = buildTree()

// counts number of edges
fun main() {
    val tree: TreeNode = run {
        val zero = TreeNode(0)
        val one = TreeNode(1)
        val five = TreeNode(5)
        val seven = TreeNode(7)
        val eight = TreeNode(8)
        val nine = TreeNode(9)

        seven.left = one
        one.left = zero
        one.right = five
        seven.right = nine
        nine.left = eight

        seven
    }

    val bigTree = TreeNode(10).apply {
        left = TreeNode(5).apply {
            left = TreeNode(2).apply {
                left = TreeNode(1)
                right = TreeNode(3)
            }
            right = TreeNode(7).apply {
                left = TreeNode(6)
                right = TreeNode(8)
            }
        }
        right = TreeNode(15).apply {
            left = TreeNode(12)
            right = TreeNode(20)
        }
    }

    println(treeHeight2(tree))
    println(treeHeight2(bigTree))
    tree11Levels?.let {
        println(treeHeight2(it))
    }
}

fun treeHeight(treeNode: TreeNode): Int {
    if (treeNode.left == null && treeNode.right == null) return 0

    fun height(treeNode: TreeNode): Int {
        val left = treeNode.left?.let { left ->
            1 + height(left)
        } ?: 0
        val right = treeNode.right?.let { right ->
            1 + height(right)
        } ?: 0

        return max(right, left)
    }

    return height(treeNode)
}

fun treeHeight2(treeNode: TreeNode?): Int {
    if (treeNode == null) return -1
    return max(1 + treeHeight2(treeNode.right), 1 + treeHeight2(treeNode.left))
}