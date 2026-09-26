/**
 * 875. Koko Eating Bananas
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 *
 * */

data class KokoTestCase(val piles: IntArray, val h: Int, val expected: Int)

fun main() {
    val testCases = listOf(
        KokoTestCase(intArrayOf(3, 6, 7, 11), 8, 4),
        KokoTestCase(intArrayOf(30, 11, 23, 4, 20), 5, 30),
        KokoTestCase(intArrayOf(30, 11, 23, 4, 20), 6, 23),
        // Edge cases
        KokoTestCase(intArrayOf(1_000_000_000), 2, 500_000_000), // Single pile, large value
        KokoTestCase(intArrayOf(1, 1, 1, 1), 4, 1),                // h == piles.size
        KokoTestCase(intArrayOf(805306368,805306368,805306368), 1000000000, 3)                // h == piles.size
    )

    for ((piles, h, expected) in testCases) {
        println("h: $h | expected: $expected vert{} piles:${piles.contentToString()} | actual result: ${minEatingSpeed(piles, h)}")
    }
}

fun minEatingSpeed(piles: IntArray, h: Int): Int {
    var high = Int.MAX_VALUE - 1
    var low = 1

    fun isValidCandidate(candidate: Int): Boolean {
        var hoursNeeded = 0
        for (i in piles.indices) {
            if (hoursNeeded > (Int.MAX_VALUE - (piles[i] / candidate) - 1)) {
                return false
            } else {
                hoursNeeded += (piles[i] / candidate)
                if (piles[i] % candidate > 0) hoursNeeded++
            }
        }

        return hoursNeeded in 1..h
    }

    var res = Int.MAX_VALUE
    while (low <= high) {
        val mid = low + (high - low) / 2
        if (isValidCandidate(mid)) {
            res = mid
            high = mid - 1
        } else {
            low = mid + 1
        }
    }

    return res
}