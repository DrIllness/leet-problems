/**
 * 21. Merge Two Sorted Lists
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 *
 * */
fun main() {
    var li = ListNode(1)
    li.next = ListNode(2)
    li.next?.next = ListNode(4)
    li.next?.next?.next = ListNode(5)

    var li2 = ListNode(1)
    li2.next = ListNode(3)
    li2.next?.next = ListNode(4)
    li2.next?.next?.next = ListNode(6)

    val list = mergeTwoLists(li, li2)
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null) return list2
    if (list2 == null) return list1

    var newRoot: ListNode? = null
    var list1Node: ListNode? = list1
    var list2Node: ListNode? = list2

    if (list1Node!!.`val` > list2Node!!.`val`) {
        newRoot = list2
        list2Node = list2!!.next
    } else {
        newRoot = list1
        list1Node = list1.next
    }
    var new = newRoot
    list2.next
    while(list1Node != null || list2Node != null) {
        if (list1Node == null) {
            new?.next = list2Node
            new = list2Node
            list2Node = list2Node?.next
            continue
        }
        if (list2Node == null) {
            new?.next = list1Node
            new = list1Node
            list1Node = list1Node.next
            continue
        }
        if (list2Node.`val` > list1Node.`val`) {
            new?.next = list1Node
            new = list1Node
            list1Node = list1Node.next
        } else {
            new?.next = list2Node
            new = list2Node
            list2Node = list2Node.next
        }
    }

    return newRoot
}

