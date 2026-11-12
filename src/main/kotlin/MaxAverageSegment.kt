import java.util.TreeSet

/**
 *
 * https://codeforces.com/edu/course/2/lesson/6/4/practice/contest/285069/problem/A
 *
 * Given an array of n
 *  and a number d
 * . Your task is to find a segment of length at least d
 * , on which the arithmetic mean of the elements is maximum possible.
 *
 * Input
 * The first line contains two integers n
 *  and d
 *  (1≤d≤n≤105
 * ). The next line contains n
 *  integers ai
 * , the elements of array (0≤ai≤100
 * ).
 *
 * Output
 * Print two integers, indices of the first and last elements of the required segment (elements are numbered starting from 1).
 * */
private data class MaxAverageTestCase(val a: IntArray, val d: Int, val expected: Pair<Int, Int>)
fun main() {
    val tests = listOf(
        // Codeforces sample: n = 6, d = 3
        MaxAverageTestCase(intArrayOf(1, 2, 3, 4, 5, 6), 3, expected = 4 to 6),

        // d == n (must pick entire array)
        MaxAverageTestCase(intArrayOf(4, 1, 7, 2, 3), 5, expected = 1 to 5),

        // Peak at the start: mean = 8.5
        MaxAverageTestCase(intArrayOf(9, 8, 1, 2, 1), 2, expected = 1 to 2),

        // Plateau in the middle: mean = 10.0
        MaxAverageTestCase(intArrayOf(2, 10, 10, 10, 2), 2, expected = 2 to 4), // (2 to 3) or (3 to 4) also valid

        // All elements identical
        MaxAverageTestCase(intArrayOf(5, 5, 5, 5), 2, expected = 1 to 2)
    )

    for ((index, test) in tests.withIndex()) {
        val result = maxAverageSegment(test.a, test.d)
        println("Test ${index + 1}: input(d=${test.d}, size=${test.a.size}) -> got $result, expected ${test.expected}")
    }
}

private fun maxAverageSegment(a: IntArray, d: Int): Pair<Int, Int> {
    var low = 0.0
    var high = 100.0
    var maxRange = 1 to d

    val avgDiffArr = DoubleArray(a.size) { 0.0 }
    val prefix = DoubleArray(a.size) { 0.0 }

    // find range
    fun isAvgPossible(avg: Double): Pair<Int, Int>? {
        // build diff arr
        for (i in a.indices) {
            avgDiffArr[i] = a[i] - avg
        }

        // check if can get this avg anyhow
        var isPossible = false
        for (diff in avgDiffArr) {
            if (diff >= 0.0) {
                isPossible = true
                break
            }
        }
        if (isPossible.not()) { return null }

        // build prefix
        prefix[0] = avgDiffArr[0]
        for (i in 1 until a.size) {
            prefix[i] = avgDiffArr[i] + prefix[i - 1]
        }

        val tree = TreeSet(Comparator<Pair<Double, Int>> { o1, o2 ->
            if (o1.first == o2.first) return@Comparator o1.second.compareTo(o2.second)
            return@Comparator o1.first.compareTo(o2.first)
        })

        // baseline before index 0
        tree.add(0.0 to -1)

        for (i in (d - 1) until a.size) {
            if (i - d >= 0) {
                tree.add(prefix[i - d] to (i - d))
            }

            val smallest = tree.first()
            if (prefix[i] - smallest.first >= 0.0) {
                return (smallest.second + 2) to (i + 1)
            }
        }

        return null
    }

    // bs for avg candidate (use ~1e-7 instead of 0.05 so close averages don't get skipped)
    while (high - low >= 1e-7) {
        val mid = low + (high - low) / 2.0
        val avgRange = isAvgPossible(mid)
        if (avgRange != null) {
            maxRange = avgRange
            low = mid
        } else {
            high = mid
        }
    }

    return maxRange
}