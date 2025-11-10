import com.sun.source.tree.Tree

/**
 * 637. Average of Levels in Binary Tree
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Example 2:
 *
 *
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 *
 * */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun main() {
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right?.left = TreeNode(15)
    root.right?.right = TreeNode(7)

    println(averageOfLevels(root).printArr())
}

fun averageOfLevels(root: TreeNode?): DoubleArray {
    val result = mutableListOf<Double>()
    val queue = ArrayDeque<TreeNode?>()

    queue.add(root)
    while (queue.isNotEmpty()) {
        val layerSize = queue.size
        var sum = 0.0

        for (i in 0 until layerSize) {
            queue.removeFirst()?.let { node ->
                sum += node.`val`.toDouble()
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }
        }

        result.add(sum / layerSize.toDouble())
    }

    return result.toDoubleArray()
}

private fun DoubleArray.printArr() {
    print("[ ")
    for (i in this) {
        print("$i ")
    }
    print("]")
    println()
}
