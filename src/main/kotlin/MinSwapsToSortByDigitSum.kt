/**
 * 3551. Minimum Swaps to Sort by Digit Sum
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given an array nums of distinct positive integers. You need to sort the array in increasing order based on the sum of the digits of each number. If two numbers have the same digit sum, the smaller number appears first in the sorted order.
 *
 * Return the minimum number of swaps required to rearrange nums into this sorted order.
 *
 * A swap is defined as exchanging the values at two distinct positions in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [37,100]
 *
 * Output: 1
 *
 * Explanation:
 *
 * Compute the digit sum for each integer: [3 + 7 = 10, 1 + 0 + 0 = 1] → [10, 1]
 * Sort the integers based on digit sum: [100, 37]. Swap 37 with 100 to obtain the sorted order.
 * Thus, the minimum number of swaps required to rearrange nums is 1.
 * Example 2:
 *
 * Input: nums = [22,14,33,7]
 *
 * Output: 0
 *
 * Explanation:
 *
 * Compute the digit sum for each integer: [2 + 2 = 4, 1 + 4 = 5, 3 + 3 = 6, 7 = 7] → [4, 5, 6, 7]
 * Sort the integers based on digit sum: [22, 14, 33, 7]. The array is already sorted.
 * Thus, the minimum number of swaps required to rearrange nums is 0.
 * Example 3:
 *
 * Input: nums = [18,43,34,16]
 *
 * Output: 2
 *
 * Explanation:
 *
 * Compute the digit sum for each integer: [1 + 8 = 9, 4 + 3 = 7, 3 + 4 = 7, 1 + 6 = 7] → [9, 7, 7, 7]
 * Sort the integers based on digit sum: [16, 34, 43, 18]. Swap 18 with 16, and swap 43 with 34 to obtain the sorted order.
 * Thus, the minimum number of swaps required to rearrange nums is 2.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * nums consists of distinct positive integers.
 * */

fun main() {
    //println(minSwaps(intArrayOf(37,100)))
    //println(minSwaps(intArrayOf(22,14,33,7)))
    //println(minSwaps(intArrayOf(18,43,34,16)))
    //println(minSwaps(intArrayOf(321286771,347067663)))
    //println(minSwaps(intArrayOf(268835996,65052660,415128775)))
    println(minSwaps(intArrayOf(277993448,416038674,616955867,539372283)))
}

private fun minSwaps(nums: IntArray): Int {
    val sums = IntArray(82) { 0 } // max sum for 10^9 is 9 * 9 = 81, index 81 needs size 82
    val sumToNum = hashMapOf<Int, ArrayList<Int>>()
    //count
    for (n in nums) {
        var sumOfDigit = 0
        var num = n
        while (num != 0) {
            sumOfDigit += num % 10
            num /= 10
        }

        if (sums[sumOfDigit] > 0) {
            sumToNum[sumOfDigit]?.add(n)
        } else {
            sumToNum[sumOfDigit] = arrayListOf()
            sumToNum[sumOfDigit]?.add(n)
        }
        sums[sumOfDigit] += 1
    }

    for ((_, stnValue) in sumToNum) {
        stnValue.sort()
        stnValue.reverse()
    }

    val sortedResultArr = IntArray(nums.size)
    var i = 0
    for ((index, v) in sums.withIndex()) {
        if (v == 0) continue

        val sn = sumToNum[index]
        while (sn!!.isNotEmpty()) {
            val last = sn.removeLast()
            sortedResultArr[i++] = last
        }
    }
0
    // map each number in sorted arr to its target index
    val targetPos = HashMap<Int, Int>(nums.size)
    for (idx in sortedResultArr.indices) {
        targetPos[sortedResultArr[idx]] = idx
    }

    var swaps = 0
    val visited = BooleanArray(nums.size)

    for (idx in nums.indices) {
        if (visited[idx] || targetPos[nums[idx]] == idx) continue

        var cycleLen = 0
        var curr = idx
        while (!visited[curr]) {
            visited[curr] = true
            curr = targetPos[nums[curr]]!!
            cycleLen++
        }

        swaps += cycleLen - 1
    }

    return swaps
}