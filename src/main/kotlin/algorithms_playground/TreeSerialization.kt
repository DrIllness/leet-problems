package algorithms_playground

import TreeNode
import printList

fun main() {
    val root = TreeNode(7)
    root.left = TreeNode(1)
    root.right = TreeNode(9)
    root.right?.left = TreeNode(8)
    root.left?.right = TreeNode(5)
    root.left?.left = TreeNode(0)

    printTree(root)

    val serList = serialize(root)
    serList.printList()

    val newRoot = deserialize(serList)
    printTree(newRoot)

    val failedTree = TreeNode(5)
    failedTree.left = TreeNode(4)
    failedTree.right = TreeNode(6)
    failedTree.right?.left = TreeNode(3)
    failedTree.right?.right = TreeNode(7)
    printTree(failedTree)
    println(isValidBST(failedTree))
}


private fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) return true

    val isCurrValid = (root.right?.`val` ?: Int.MAX_VALUE) > root.`val` &&
            (root.left?.`val` ?: Int.MIN_VALUE) < root.`val`

    return isCurrValid && isValidBST(root.left) && isValidBST(root.right)
}

private fun serialize(treeNode: TreeNode): ArrayList<Int?> {
    val list = ArrayList<Int?>()

    fun preorderTraverse(node: TreeNode?) {
        node?.let {
            list.add(it.`val`)
        }
        node?.left?.let {
            preorderTraverse(it)
        } ?: list.add(null)

        node?.right?.let {
            preorderTraverse(it)
        } ?: list.add(null)
    }

    preorderTraverse(treeNode)
    return list
}

private fun deserialize(list: MutableList<Int?>): TreeNode? {
    list.reverse()

    fun genTree(list: MutableList<Int?>): TreeNode? {
        var node: TreeNode? = null
        list.removeLastOrNull()?.let {
            node = TreeNode(it)
            node?.left = genTree(list)
            node?.right = genTree(list)
        }
        return node
    }

    return genTree(list)
}

private fun printTree(
    node: TreeNode?,
    prefix: String = "",
    isLeft: Boolean = true
) {
    if (node == null) return

    printTree(
        node = node.right,
        prefix = prefix + if (isLeft) "│   " else "    ",
        isLeft = false
    )

    println(prefix + (if (isLeft) "└── " else "┌── ") + "${node.`val`}")

    printTree(
        node = node.left,
        prefix = prefix + if (isLeft) "    " else "│   ",
        isLeft = true
    )
}