/**
 * 199. Binary Tree Right Side View

 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,5,null,4]
 *
 * Output: [1,3,4]
 *
 * Explanation:
 *
 *
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4,null,null,null,5]
 *
 * Output: [1,3,4,5]
 *
 * Explanation:
 *
 *
 *
 * Example 3:
 *
 * Input: root = [1,null,3]
 *
 * Output: [1,3]
 *
 * Example 4:
 *
 * Input: root = []
 *
 * Output: []
 *
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * */

fun main() {
    val root = TreeNode(1)
    root.apply {
        right = TreeNode(3)
        right?.right = TreeNode(4)
        left = TreeNode(2)
        left?.right = TreeNode(5)
        rightSideView2(root).printList()
    }

    val root2 = TreeNode(1)
    root2.apply {
        left = TreeNode(2)
        left?.left = TreeNode(4)
        left?.left?.left = TreeNode(5)
        right = TreeNode(3)
        rightSideView2(this).printList()
    }
}

fun rightSideView(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    val queue = ArrayDeque<TreeNode?>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val layerSize = queue.size

        for (i in 0 until layerSize) {
            val node = queue.removeFirst()

            if (node != null) {
                if (i == 0) result.add(node.`val`)

                node.right?.let {
                    queue.add(it)
                }
                node.left?.let {
                    queue.add(it)
                }
            }
        }
    }

    return result
}

fun rightSideView2(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()

    fun dfs(node: TreeNode?, depth: Int) {
        if (node == null) return
        if (depth == result.size) result.add(node.`val`)
        dfs(node.right, depth + 1)
        dfs(node.left, depth + 1)
    }

    dfs(root, 0)

    return result
}

fun <E> List<E>.printList() {
    print("[")
    this.forEachIndexed { index, e ->
        if (index != this.size - 1)
            print("$e ")
        else
            print("$e")
    }
    print("]")
    println()
}
