/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, a
 * nd return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * Example 3:
 *
 * Input: intervals = [[4,7],[1,4]]
 * Output: [[1,7]]
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * */

fun main() {
    val arr1 = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))

    println()
    merge(arr1).print()
    println()

    val arr2 = arrayOf(intArrayOf(4, 7), intArrayOf(1, 4))

    println()
    merge(arr2).print()
    println()


    val arr3 = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))

    println()
    merge(arr3).print()
}

fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.size < 2) {
        return intervals
    }

    intervals.sortBy { it[0] }
    val dynamicList = mutableListOf<IntArray>()

    var i = 0
    while (i < intervals.size) {
        val iStart = intervals[i][0]
        var iEnd =  intervals[i][1]
        while (i < intervals.size && iEnd >= intervals[i][0]) {
            if (iEnd <= intervals[i][1])
                iEnd = intervals[i][1]

            i++
        }
        dynamicList.add(intArrayOf(iStart, iEnd))
    }

    return dynamicList.toTypedArray()
}

private fun Array<IntArray>.print() {
    for (i in this) {
        print("[ ")
        for (j in i) {
            print(" ")
            print(j)
            print(" ")
        }
        print(" ]")
    }
}