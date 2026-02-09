/**
 * 138. Copy List with Random Pointer
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * *
 * Example 1:
 *
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 *
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 *
 *
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 *
 * Constraints:
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 *
 * */

class NodeR(var `val`: Int) {
    var next: NodeR? = null
    var random: NodeR? = null

    override fun toString(): String {
        return "Node(${this.`val`}) ${super.toString()}"
    }
}

fun main() {
    val node1 = NodeR(7)
    val node2 = NodeR(13)
    val node3 = NodeR(11)
    val node4 = NodeR(10)
    val node5 = NodeR(1)

    node1.next = node2
    node1.random = null

    node2.next = node3
    node2.random = node1

    node3.next = node4
    node3.random = null

    node4.next = node5
    node4.random = node3

    node5.next = null
    node5.random = node1

    node1.printlist()
    println()
    val new = copyRandomList(node1)
    new?.printlist()

    println()
    val node10 = NodeR(-1)
    node10.random = node10
    node10.next = null
    node10.printlist()
    println()
    copyRandomList(node10)?.printlist()

}

fun NodeR.printlist() {
    var n: NodeR? = this
    while (n != null) {
        println("${n} next = ${n.next} random = ${n.random}")
        n = n.next
    }
}

fun copyRandomList(node: NodeR?): NodeR? {
    if (node == null) return null

    val oldToNew = hashMapOf<NodeR, NodeR>()
    val newRoot = NodeR(node.`val`)
    oldToNew[node] = newRoot.also { newr ->
        newr.random = node.random
    }

    var prevNew = newRoot
    var currOld = node.next
    while (currOld!= null) {
        val currNew = NodeR(currOld.`val`)
        oldToNew[currOld] = currNew.also { newr ->
            newr.random = currOld?.random
        }
        prevNew.next = currNew
        prevNew = currNew
        currOld = currOld.next
    }

    var curr: NodeR? = newRoot
    while(curr!=null) {
        curr.random = oldToNew[curr.random]
        curr = curr.next
    }

    return newRoot
}