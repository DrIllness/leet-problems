import kotlin.math.abs

/**
 * Given an integer array arr[], which denotes the positions of stalls.
 * All the positions are distinct. There are k aggressive cows.
 *
 * Assign the cows to the stalls such that the minimum distance between any two cows is maximized.
 *
 * Examples:
 *
 * Input: arr[] = [1, 2, 4, 8, 9], k = 3
 * Output: 3
 * Explanation: The first cow can be placed at arr[0], the second at arr[2], and the third at arr[3].
 * The minimum distance between any two cows is 3 (between arr[0] and arr[2]), which is the maximum possible among
 * all valid arrangements.
 * Input: arr[] = [10, 1, 2, 7, 5], k = 3
 * Output: 4
 * Explanation: The first cow can be placed at arr[0], the second at arr[1], and the third at arr[4]. In this arrangement,
 * the minimum distance between any two cows is 4 (between arr[1] and arr[4]), which is the maximum possible among all valid arrangements.
 * Constraints:
 *
 * arr.size() ≤ 106
 * 0 ≤ arr[i] ≤ 108
 * 2 ≤ k ≤ arr.size()
 *
 *
 * */

fun main() {
    println(aggressiveCows(intArrayOf(1, 2, 4, 8, 9), 3))
    //println(aggressiveCows(intArrayOf(10, 1, 2, 7, 5), 4))
}

// max min vs min max - what makes more sense
private fun aggressiveCows(arr: IntArray, k: Int): Int {
    arr.sort()
    var right = arr.last() - arr.first()
    var left = 1
    var maxMinDistance = right

    fun canPlacedKCows(d: Int): Boolean {
        var cowPlaced = 1

        var i = 0
        var j = i + 1
        while (j < arr.size) {
            if (arr[j] - arr[i] >= d) {
                cowPlaced++
                i = j
            }
            j++
        }
        return cowPlaced >= k
    }

    while (left <= right) {
        val mid = left + (right - left) / 2
        if (canPlacedKCows(mid)) {
            maxMinDistance = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }


    return maxMinDistance
}
