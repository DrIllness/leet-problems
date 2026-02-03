/**
 *155. Min Stack
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * */

fun main() {
    val minStack = MinStack()
    minStack.push(0);
    minStack.push(1);
    minStack.push(0);
    println(minStack.getMin());
    minStack.pop();
    println(minStack.getMin());
    minStack.pop();
    println(minStack.getMin());
    minStack.pop();
    minStack.push(-2);
    minStack.push(-1);
    minStack.push(-2);
    println(minStack.getMin());
    minStack.pop();
    minStack.top();
    println(minStack.getMin());
    minStack.pop();
    println(minStack.getMin());
    minStack.pop();
}

class MinStack() {
    private val arr = ArrayDeque<Int>()
    private var minElem = ArrayDeque<Int>()

    fun push(`val`: Int) {
        if (minElem.isEmpty() || minElem.last() >= `val`) minElem.addLast(`val`)
        arr.addLast(`val`)
    }

    fun pop() {
        val last = arr.removeLast()
        if (last == minElem.last()) minElem.removeLast()
    }

    fun top(): Int {
        return arr.last()
    }

    fun getMin(): Int {
        return minElem.last()
    }

}