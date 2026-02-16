/**
 * 86. Partition List
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 *
 * */

fun main() {
    var li = ListNode(1)
    li.next = ListNode(4)
    li.next?.next = ListNode(3)
    li.next?.next?.next = ListNode(2)
    li.next?.next?.next?.next = ListNode(5)
    li.next?.next?.next?.next?.next = ListNode(2)

    partition(li, 3).print()

}

fun partition(head: ListNode?, x: Int): ListNode? {
    if (head?.next == null) return head
    val dummyHead = ListNode(Int.MIN_VALUE)
    val dummyHead2 = ListNode(Int.MIN_VALUE)

    var node = head
    var nodeDummy: ListNode? = dummyHead
    var nodeDummy2: ListNode? = dummyHead2
    while (node != null) {
        if (node.`val` < x) {
            nodeDummy?.next = node
            nodeDummy = nodeDummy?.next
        } else {
            nodeDummy2?.next = node
            nodeDummy2 = nodeDummy2?.next
        }
        node = node.next
    }

    nodeDummy2?.next = null
    nodeDummy?.next = dummyHead2.next

    return dummyHead.next
}