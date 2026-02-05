/**
 * 82. Remove Duplicates from Sorted List II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 *
 *
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 *
 * */


fun main() {
    /*val head = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(3)
    head.next?.next?.next?.next = ListNode(4)
    head.next?.next?.next?.next?.next = ListNode(4)
    head.next?.next?.next?.next?.next?.next = ListNode(5)

    var node: ListNode? = head
    while(node != null) {
        print("${node.`val`} ")
        node = node.next
    }
    println()
    deleteDuplicates(head)
    node = head
    while(node != null) {
        print("${node.`val`} ")
        node = node.next
    }
    println()*/

    val head2 = ListNode(1)
    head2.next = ListNode(1)
    head2.next?.next = ListNode(1)
    head2.next?.next?.next = ListNode(3)
    head2.next?.next?.next?.next = ListNode(4)
    head2.next?.next?.next?.next?.next = ListNode(4)
    head2.next?.next?.next?.next?.next?.next = ListNode(5)

    var node2: ListNode? = head2
    while(node2 != null) {
        print("${node2.`val`} ")
        node2 = node2.next
    }
    println()
    node2 = deleteDuplicates(head2)

    while(node2 != null) {
        print("${node2.`val`} ")
        node2 = node2.next
    }
}
fun deleteDuplicates(head: ListNode?): ListNode? {
    val dummy = ListNode(-111)
    dummy.next = head
    var prev = dummy
    var curr = head

    while (curr != null) {
        if (curr.next != null && curr.`val` == curr.next!!.`val`) {
            val duplicateVal = curr.`val`
            while (curr != null && curr.`val` == duplicateVal) {
                curr = curr.next
            }
            prev.next = curr
        } else {
            prev = prev.next!!
            curr = curr.next
        }
    }
    return dummy.next
}

fun deleteDuplicates2(head: ListNode?): ListNode? {
    if (head == null) return null
    if (head.next == null) return head

    fun deleteDuplicates(node: ListNode?): ListNode? {
        if (node == null) return null

        if (node.`val` == node.next?.`val`) {
            var curr = node
            while (curr != null && curr.`val` == node.`val`) {
                curr = curr.next
            }

            return deleteDuplicates(curr)
        } else {
            node.next = deleteDuplicates(node.next)
            return node
        }
    }

    return deleteDuplicates(head)
}