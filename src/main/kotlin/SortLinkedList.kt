/**
 * 148. Sort List
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 * */
// TODO
fun main() {
    val node = ListNode(-1)
    node.next = ListNode(5)
    node.next?.next = ListNode(3)
    node.next?.next?.next = ListNode(4)
    node.next?.next?.next?.next = ListNode(0)

    printr(node)
    println("---")
    printr(sortList(node))
}

fun sortList(head: ListNode?): ListNode? {

    return null
}
