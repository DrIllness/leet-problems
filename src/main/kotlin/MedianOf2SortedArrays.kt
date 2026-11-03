/**
 * 4. Median of Two Sorted Arrays
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * Constraints:
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * */

fun main() {
    println(findMedianSortedArrays(
        intArrayOf(1,3),
        intArrayOf(2),
    ))
    println(
        findMedianSortedArrays(
            intArrayOf(1,2),
            intArrayOf(3,4),
        )
    )

    println(
        findMedianSortedArrays(
            intArrayOf(),
            intArrayOf(1, 2, 3,4),
        )
    )

    println(
        findMedianSortedArrays(
            intArrayOf(1, 2, 3,4),
            intArrayOf(),
        )
    )
}

private fun findMedianSortedArrays1(nums1: IntArray, nums2: IntArray): Double {
    val totalSize = nums1.size + nums2.size

    val mediana = totalSize / 2
    val isOdd = totalSize % 2 == 1

    var left = -1
    var right = nums1.size - 1

    val shrink = -1
    val expand = 1
    val perfect = 0

    fun checkSlice(aIdx: Int): Int {
        val bIdx = mediana - aIdx
        val safeA = if (aIdx >= nums1.size) Int.MAX_VALUE else if (aIdx < 0) Int.MIN_VALUE else nums1[aIdx]
        val safeB = if (bIdx >= nums2.size) Int.MAX_VALUE else if (bIdx < 0) Int.MIN_VALUE else nums2[bIdx]
        val safeARight = if (aIdx + 1 >= nums1.size) Int.MAX_VALUE else nums1[aIdx + 1]
        val safeBRight = if (bIdx + 1 >= nums2.size) Int.MAX_VALUE else nums2[bIdx + 1]

        if (safeA > safeB) {
            if (safeA > safeBRight) {
                return shrink
            } else {
                return perfect
            }
        } else {
            if (safeARight < safeB) {
                return expand
            } else {
                return perfect
            }
        }
    }

    while (left <= right) {
        val mid = left + (right - left) / 2
        when (checkSlice(mid)) {
            expand -> left = mid + 1
            shrink -> right = mid - 1
            perfect -> {
                val sliceB = mediana - mid

                if (mid < 0) {
                    if (isOdd) {
                        return nums2[mediana].toDouble()
                    } else {
                        return (nums2[mediana] + nums2[mediana - 1]).toDouble().div(2.0)
                    }
                } else if (sliceB < 0) {
                    if (isOdd) {
                        return nums1[mediana].toDouble()
                    } else {
                        return (nums1[mediana] + nums1[mediana - 1]).toDouble().div(2.0)
                    }
                } else {
                    if (isOdd) {
                        return minOf(nums1[mid], nums2[sliceB]).toDouble()
                    } else {
                        return (nums1[mid] + nums2[sliceB]).toDouble().div(2.0)
                    }
                }

            }
        }
    }

    return -1.0
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.size > nums2.size) {
        return findMedianSortedArrays(nums2, nums1)
    }

    val totalSize = nums1.size + nums2.size
    val mediana = (totalSize + 1) / 2
    val isOdd = totalSize % 2 == 1

    var left = 0
    var right = nums1.size

    while (left <= right) {
        val mid = left + (right - left) / 2

        val sliceB = mediana - mid
        val safeALeft = if (mid <= 0) Int.MIN_VALUE else nums1[mid - 1]
        val safeBLeft = if (sliceB <= 0) Int.MIN_VALUE else nums2[sliceB - 1]
        val safeARight = if (mid >= nums1.size) Int.MAX_VALUE else nums1[mid]
        val safeBRight = if (sliceB >= nums2.size) Int.MAX_VALUE else nums2[sliceB]

        if (safeALeft > safeBRight) {
            right = mid - 1
        } else if (safeBLeft > safeARight) {
            left = mid + 1
        } else {
            val maxLeft = maxOf(safeALeft, safeBLeft)
            val minRight = minOf(safeARight, safeBRight)

            return if (isOdd) {
                maxLeft.toDouble()
            } else {
                (maxLeft + minRight) / 2.0
            }
        }
    }

    return -1.0
}