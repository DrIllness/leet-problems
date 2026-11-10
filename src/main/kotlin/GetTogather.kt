import kotlin.math.abs

/**
 * A. Get together
 * time limit per test1 second
 * memory limit per test256 megabytes
 * There are n  people on a straight line, they need to gather at one point. Each person knows his current position xi
 *  and his speed vi
 *  Help them find out in what minimum time they can gather at one point.
 *
 * Input
 * The first line contains integer n(1≤n≤105), next n
 *  lines contain pairs of integers xi
 *  and vi
 *  (−109≤xi≤10^9
 * , 1≤vi≤109
 * ).
 *
 * Output
 * Print one number, the minimum time it takes people to gather at one point. The answer will be considered correct if the relative or absolute error does not exceed 10−6
 * .*/

fun getMinimumTimeToGather(people: List<Pair<Int, Int>>): Double {
    var l = 0.0
    var r = 2e9 // basically 1e9 * 2
    var time = r
    val diff = 1e-7

    fun canMeetIn(t: Double): Boolean {
        // build ranges
        val ranges = mutableListOf<Pair<Double, Double>>()
        for (p in people) {
            val distance = p.second * t
            val left = p.first - distance
            val right = p.first + distance
            ranges.add(left to right)
        }

        // check for overlapping
        var range = -Double.MAX_VALUE to Double.MAX_VALUE
        for (r in ranges) {
            range = maxOf(r.first, range.first) to minOf(r.second, range.second)
            if (range.first > range.second)
                return false
        }
        return true
    }

    while (r - l > diff) {
        val mid = (l + (r - l).div(2.0))
        if (canMeetIn(mid)) {
            time = minOf(mid, time)
            r = mid
        } else {
            l = mid
        }
    }

    return time
}

fun main() {
    val testCases = listOf(
        // Case 1: Standard two people
        listOf(
            0 to 1,
            10 to 1
        ),
        // Case 2: Different speeds and positions
        listOf(
            0 to 2,
            6 to 1,
            12 to 3
        ),
        // Case 3: Already at the same point
        listOf(
            100 to 5,
            100 to 10,
            100 to 1
        ),
        // Case 4: Single person
        listOf(
            -500_000_000 to 100
        ),
        // Case 5: Negative positions with asymmetric speeds
       listOf(
            -10 to 1,
            10 to 3
        ),
        // Case 6: Large distance / edge constraints
        listOf(
            -1_000_000_000 to 1,
            1_000_000_000 to 1
        ),
        listOf(
            -1 to 5,
            10 to 3,
            4 to 2,
            7 to 10,
            8 to 1
        )
    )

    for ((index, test) in testCases.withIndex()) {
        val people = test
        val result = getMinimumTimeToGather(people)
        println("Test #${index + 1}: %.6f".format(result))
    }
}