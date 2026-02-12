/**
 * 19. Remove Nth Node From End of List
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * Follow up: Could you do this in one pass?
 * */

fun main() {
    val root = ListNode(1)
    root.next = ListNode(2)
    root.next?.next = ListNode(3)
    root.next?.next?.next = ListNode(4)
    root.next?.next?.next?.next = ListNode(5)

    root.print()
    val new = removeNthFromEnd(root, 5)
    println()
    new.print()
}

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    if (head == null || n == 0 ) return head

    val dummyHead = ListNode(Int.MIN_VALUE)
    dummyHead.next = head

    var node = dummyHead.next
    var i = 0
    while (node != null) {
        node = node.next
        i++
    }

    val posForRemoval = i - n

    node = dummyHead
    i = 0
    while (i < posForRemoval) {
        node = node?.next
        i++
    }

    node?.next = node?.next?.next

    return dummyHead.next
}
