/**
 * 102. Binary Tree Level Order Traversal
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * */

fun main() {
    val root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)
    root.right?.left = TreeNode(15)
    root.right?.right = TreeNode(7)

    levelOrder(root).printList()
}

fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null) return emptyList()
    val res = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode?>()

    queue.addLast(root)

    while (queue.isNotEmpty()) {
        val layerSize = queue.size
        var layerList = mutableListOf<Int>()
        for (i in 0 until layerSize) {
            queue.removeFirst()?.let { node ->
                layerList.add(node.`val`)
                node.left?.let {
                    queue.addLast(it)
                }
                node.right?.let {
                    queue.addLast(it)
                }
            }

        }
        res.add(layerList)
    }

    return res
}

fun List<List<Int>>.printList() {
    var i = 0
    var j = 0
    while (i < this.size) {
        print("[")
        while (j < this[i].size) {
            print(" ${this[i][j]} ")
            j++
        }
        j = 0
        i++
        print("] ")
    }
}