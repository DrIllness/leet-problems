/**
 * 92. Reverse Linked List II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 *
 * Follow up: Could you do it in one pass?
 * */

fun main() {
    val root = ListNode(1)
    root.next = ListNode(2)
    root.next?.next = ListNode(3)
    root.next?.next?.next = ListNode(4)
    root.next?.next?.next?.next = ListNode(5)

    root.print()
    val reversed = reverseBetween(root, 2, 4)
    println()
    reversed?.print()
    println()

    val root2 = ListNode(3)
    root2.next = ListNode(5)

    root2.print()
    val reversed2 = reverseBetween(root2, 1, 2)
    println()
    reversed2?.print()
}

fun ListNode?.print() {
    var n = this
    while (n != null) {
        print("${n.`val`} ")
        n = n.next
    }
}

fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || left == right) return head

    val dummyHead: ListNode? = ListNode(Int.MIN_VALUE)
    dummyHead?.next = head

    var node = dummyHead
    var i = 0

    var lastNodeOfPrefix: ListNode? = null

    while (node != null) {
        if (i == left - 1) {
            lastNodeOfPrefix = node

            var newTail = node.next
            var newHead: ListNode? = null

            var prev: ListNode? = null
            var next: ListNode? = null
            var curr = node

            while (i <= right) {
                next = curr?.next
                curr?.next = prev

                prev = curr
                curr = next
                i++
            }
            newHead = prev
            lastNodeOfPrefix.next = newHead

            newTail?.next = curr
            break
        }

        i++
        node = node.next
    }

    return dummyHead?.next
}

fun reverseBetween2(head: ListNode?, left: Int, right: Int): ListNode? {
    if (head == null || left == right) return head

    val dummyHead: ListNode? = ListNode(Int.MIN_VALUE)
    dummyHead?.next = head

    var node = dummyHead
    var i = 0

    var prev: ListNode? = null
    var next: ListNode? = null
    var stack = ArrayDeque<ListNode?>()
    while (i <= right + 1) {
        if (i == left - 1) {
            prev = node
        } else if (i == right + 1) {
            next = node
        } else if (i in left..right) {
            stack.addLast(node)
        }

        node = node?.next
        i++
    }

    var n = prev
    while (stack.isNotEmpty()) {
        n?.next = stack.removeLast()
        n = n?.next
    }

    n?.next = next


    return dummyHead?.next
}