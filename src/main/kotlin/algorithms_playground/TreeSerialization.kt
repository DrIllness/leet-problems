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
    println(isValidBST3(root))
    println(isValidBST3(failedTree))
}

// incorrect impl
private fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) return true

    val isLeftValid = isValidBST(root.left)

    val isCurrValid = (root.right?.`val` ?: Int.MAX_VALUE) > root.`val` &&
            (root.left?.`val` ?: Int.MIN_VALUE) < root.`val`

    val isRightValid = isValidBST(root.right)

    return isCurrValid && isLeftValid && isRightValid
}

// todo
private fun isValidBST3(root: TreeNode?): Boolean {
    var prev: Int? = null

    fun isValid(node: TreeNode?): Boolean {
        if (node == null) return true
        if (!isValid(node.left)) return false

        if (prev != null && node.`val` <= prev!!) return false
        prev = node.`val`

        return isValid(node.right)
    }

    return isValid(root)
}

private fun isValidBST2(root: TreeNode?): Boolean {
    if (root == null) return true

    val tranversed = ArrayList<TreeNode>()

    fun r(n: TreeNode?) {
        if (n == null) return
        r(n.left)
        n.let { tranversed.add(n) }
        r(n.right)
    }
    r(root)

    var prev = tranversed[0]
    for (i in 1 until tranversed.size) {
        if (prev.`val` < tranversed[i].`val`) {
            prev = tranversed[i]
            continue
        } else {
            return false
        }
    }

    return true
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