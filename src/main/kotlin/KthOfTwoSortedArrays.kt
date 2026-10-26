/**
 * K-th of Two Sorted Arrays
 * Difficulty: MediumAccuracy: 37.4%Submissions: 427K+Points: 4Average Time: 15m
 * Given two sorted arrays a[] and b[] and an element k, find the element that would be at the kth position
 * of the combined sorted array.
 *
 * Examples :
 *
 * Input: a[] = [2, 3, 6, 7, 9], b[] = [1, 4, 8, 10], k = 5
 * Output: 6
 * Explanation: The final combined sorted array would be [1, 2, 3, 4, 6, 7, 8, 9, 10]. The 5th element of this array is 6.
 *
 * Input: a[] = [1, 4, 8, 10, 12], b[] = [5, 7, 11, 15, 17], k = 6
 * Output: 10
 * Explanation: Combined sorted array is [1, 4, 5, 7, 8, 10, 11, 12, 15, 17]. The 6th element of this array is 10.
 * Constraints:
 *
 * 1 ≤ k ≤ size(a) + size(b)
 * 1 ≤ a.size(), b.size() ≤ 10^6
 * 0 ≤ a[i], b[i] ≤ 10^8
 *
 *
 * */

fun main() {

    println(
        kthElement(
            intArrayOf(2,   3,    6,   7,    9),
            intArrayOf(1,   4,    8,   10),
            5))
    println(kthElement(
        intArrayOf(1,  4,  8,  10,  12),
        intArrayOf(5,  7,  11, 15,  17),
        6)
    )

    println(kthElement(
        intArrayOf(111,  124,  138,  210,  212),
        intArrayOf(5,  7,  11, 15,  17),
        6)
    )



    println(kthElement(
        intArrayOf(1, 2),
        intArrayOf(5,  7,  11, 15,  17, 18),
        6)
    )
    println(kthElement(
        intArrayOf(5,  7,  11, 15,  17, 18),
        intArrayOf(22),
        6)
    )
    println(kthElement(
        intArrayOf(22),
        intArrayOf(5,  7,  11, 15,  17, 18),
        6)
    )

}

// initial draft, crashes
private fun kthElement1(a: IntArray, b: IntArray, k: Int): Int {
    var left = 0
    var right = k - 1 // max idx for each
    if (right == 0) {
        return minOf(a[0], b[0])
    }

    // check for boundries for a

    right = minOf(right, a.size - 1)
    fun size(aEnd: Int): Int {
        var aidx = aEnd
        var bidx = k - aEnd - 2
        if (a[aidx] < b[bidx]) {
            while (a[aidx] < b[bidx] && bidx > 0) {
                bidx--
            }
        } else {
            while (a[aidx] > b[bidx]) {
                bidx++
            }
        }

        return aidx + bidx + 2
    }

    var ans = -1
    while (left <= right) {
        val mid = left + (right - left) / 2
        val s = size(mid)
        if (s > k + 1) {
            right = mid - 1
        } else if (s < k + 1) {
            left = mid + 1
        } else {
            return maxOf(a[mid],b[k-mid-2])
        }
    }

    return ans
}

// works, submitted on geeks4geeks
private fun kthElement2(a: IntArray, b: IntArray, k: Int): Int {
    if (k == 1) {
        return if (b.isEmpty()) a[0]
        else if (a.isEmpty()) b[0]
        else minOf(a[0], b[0])
    }

    var kIdx = k - 1
    var left = 0
    var right = minOf(kIdx, a.size - 1)

    val MAKE_A_BIGGER = 1
    val MAKE_A_SMALLER = -1
    val FOUND_PERFECT_CUT = 0

    fun moveSlicePoint(aidx: Int): Int {
        val bidx = k - aidx - 2
        if (bidx >= b.size) {
            return MAKE_A_BIGGER
        } else if (aidx >= a.size) {
            return MAKE_A_SMALLER
        }

        if (aidx < 0 || bidx < 0) return FOUND_PERFECT_CUT

        if (a[aidx] < b[bidx]) {
            return if (aidx + 1 < a.size && a[aidx + 1] < b[bidx]) {
                MAKE_A_BIGGER
            } else
                FOUND_PERFECT_CUT
        } else {
            return if (bidx + 1 < b.size && b[bidx + 1] < a[aidx]) {
                MAKE_A_SMALLER
            } else {
                FOUND_PERFECT_CUT
            }
        }
    }

    while (left <= right) {
        val mid = left + (right - left) / 2
        val sliceDisbalance = moveSlicePoint(mid)
        if (sliceDisbalance == 0) {
            val bSlice = minOf(k - mid - 2, b.size - 1)
            val aSlice = minOf(mid, a.size - 1)
            if (bSlice < 0) {
                return a[aSlice]
            } else if (aSlice < 0) {
                return b[bSlice]
            } else return  maxOf(a[aSlice], b[bSlice])
        } else if (sliceDisbalance == MAKE_A_BIGGER) {
            left = mid + 1
        } else if (sliceDisbalance == MAKE_A_SMALLER) {
            right = mid - 1
            if (right < 0) {
                return b[k - 1]
            }
        }
    }

    return -1
}

// clean version
private fun kthElement(a: IntArray, b: IntArray, k: Int): Int {
    if (b.isEmpty()) return a[k-1]
    if (a.isEmpty()) return b[k-1]

    val expand = 1
    val shrink = -1
    val perfect = 0

    fun moveSlicePoint(aidx: Int): Int {
        val bidx = (k - 1) - (aidx + 1)

        val safeA = if (aidx > a.size - 1) {
            Int.MAX_VALUE
        } else if (aidx < 0)
            Int.MIN_VALUE
        else
            a[aidx]

        val safeB = if (bidx > b.size - 1) {
            Int.MAX_VALUE
        } else if (bidx < 0)
            Int.MIN_VALUE
        else
            b[bidx]

        return if (safeA < safeB) {
            if (aidx + 1 < a.size && a[aidx + 1] < safeB) {
                expand
            } else
                perfect
        } else {
            if (bidx + 1 < b.size && b[bidx + 1] < safeA) {
                shrink
            } else {
                perfect
            }
        }
    }
    var left = -1 // best line EVER
    var right = minOf(k, a.size)

    var mid = 0
    while (left <= right) {
        mid = left + (right - left) / 2
        val sliceDisbalance = moveSlicePoint(mid)
        when (sliceDisbalance) {
            expand -> { left = mid + 1 }
            shrink -> { right = mid - 1 }
            else -> { break }
        }
    }

    val bSlice = k - mid - 2

    return if (bSlice < 0) {
        a[mid]
    } else if (mid < 0) {
        b[bSlice]
    } else maxOf(a[mid], b[bSlice])
}