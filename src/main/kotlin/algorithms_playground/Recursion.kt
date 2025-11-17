package algorithms_playground

import TreeNode
import kotlin.math.max

fun main() {
    println(reverse("perdix"))
    println(fib(7))
    println(sumArrRec1(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0, 7))
    println(sumArrRec(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8), 0))
    println(findMax(intArrayOf(1, 56, 1, 3, 65, 12), 0, 0))

    val root = TreeNode(1)
    root.apply {
        right = TreeNode(2)
        right?.right = TreeNode(3)
        right?.left = TreeNode(4)
        right?.right?.right = TreeNode(5)
        right?.right?.right?.right = TreeNode(6)
        right?.right?.right?.right?.right = TreeNode(7)
    }

    println(maxDepth(root))
    println(sumOfBinaryTree(root))
}

private fun reverseStringRec(source: String): String {
    val arr = source.toCharArray()

    fun swapLastElems(arr: CharArray, start: Int, end: Int): CharArray {
        if (start > end) return arr

        val swap = arr[start]
        arr[start] = arr[end]
        arr[end] = swap
        return swapLastElems(arr, start + 1, end - 1)
    }

    return swapLastElems(arr, 0, arr.size - 1).concatToString()
}

private fun reverse(s: String): String {
    if (s.length <= 1) return s
    return reverse(s.substring(1)) + s[0]
}

private fun fib(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1

    return fib(n - 1) + fib(n - 2)
}

private fun sumArrRec1(arr: IntArray, start: Int, end: Int): Int {
    if (start > end) return 0
    return arr[start] + arr[end] + sumArrRec1(arr, start + 1, end - 1)
}

private fun sumArrRec(arr: IntArray, i: Int): Int {
    if (i == arr.size) return 0
    return arr[i] + sumArrRec(arr, i + 1)
}

private fun findMax(arr: IntArray, i: Int, max: Int): Int {
    if (i == arr.size) return max

    return findMax(arr, i + 1, if (arr[i] > max) arr[i] else max)
}

fun maxRec(arr: IntArray, i: Int = 0): Int {
    if (i == arr.size - 1) return arr[i]
    return max(arr[i], maxRec(arr, i + 1))
}

fun maxDepth(treeNode: TreeNode?): Int {
    if (treeNode == null) return 0

    val left = maxDepth(treeNode.left)
    val right = maxDepth(treeNode.right)

    return 1 + max(left, right)
}

fun sumOfBinaryTree(root: TreeNode?): Int {
    if (root == null) return 0
    return root.`val` + sumOfBinaryTree(root.left) + sumOfBinaryTree(root.right)
}
