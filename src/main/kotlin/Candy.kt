import kotlin.math.max

/**
 * 135. Candy
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 *
 * */

fun main() {
    val input = intArrayOf(1, 0, 2)
    println(candy(input))
}

fun candy(ratings: IntArray): Int {
    if (ratings.size == 1) return 1

    val candies = IntArray(ratings.size) { 1 }

    var i = 1
    while (i <= candies.size - 1) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1
        }

        i++
    }

    i = candies.size - 2
    while (i >= 0) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = max(candies[i], candies[i + 1] + 1)
        }
        i--
    }

    var totalCandies = 0
    for (c in candies) {
        totalCandies += c
    }
    return totalCandies
}