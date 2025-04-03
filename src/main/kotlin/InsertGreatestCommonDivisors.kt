/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.

 */

class ListNode(var `val`: Int) { var next: ListNode? = null }

fun main(args: Array<String>) {
    var li = ListNode(18)
    li.next = ListNode(6)
    li.next?.next = ListNode(10)
    li.next?.next?.next = ListNode(3)

    printr(insertGreatestCommonDivisors(li))
}

fun printr(node: ListNode?) {
    node?.`val`?.let {
        println(it)
    }

    node?.next?.let {
        printr(it)
    }
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun insertGreatestCommonDivisors(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    val gcd = gcd(head.`val`, head.next!!.`val`)
    val oldNext = head.next
    head.next = ListNode(gcd)
    head.next?.next = oldNext

    insertGreatestCommonDivisors(oldNext)

    return head
}
