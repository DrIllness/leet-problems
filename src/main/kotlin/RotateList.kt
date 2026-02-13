/**
 * 61. Rotate List
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * */

fun main() {
    var li2 = ListNode(1)
    li2.next = ListNode(2)
    li2.next?.next = ListNode(3)
    li2.next?.next?.next = ListNode(4)
    li2.next?.next?.next?.next = ListNode(5)

    li2.print()
    println()
    val newRoot = rotateRight(li2, 2)
    newRoot.print()
}

fun rotateRight(head: ListNode?, k: Int): ListNode? {
    if (head == null) return null
    var size = 0
    var node = head
    while (node?.next != null) {
        size++
        node = node.next
    }
    size++
    node?.next = head

    val shift = size - (k % size)
    var i = 0
    node = head
    while (i < shift - 1) {
        node = node?.next
        i++
    }
    val newHead = node?.next
    node?.next = null

    return newHead
}