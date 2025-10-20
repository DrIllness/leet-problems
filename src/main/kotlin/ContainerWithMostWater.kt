/**
 * 11. Container With Most Water

 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 *
 * */

fun main() {
    println(maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
    println(maxArea(intArrayOf(1,1)))
    println(maxArea(intArrayOf(0,1)))
    println(maxArea(intArrayOf(0,0)))
    println(maxArea(intArrayOf(1,1,0)))
    println(maxArea(intArrayOf(1,8,100,2,100,4,8,3,7)))
}

fun maxArea(height: IntArray): Int {
    var maxArea = 0
    var i = 0
    var j = height.size - 1

    do {
        var maxLeft = height[i]
        var maxRight = height[j]
        maxArea = maxOf(maxArea, minOf(maxRight, maxLeft) * (j - i))
        if (maxLeft > maxRight) {
            do {
                maxRight = maxOf(maxRight, height[j])
                j--
            } while ((j > 1) && height[j] < maxRight)
        } else if (maxLeft < maxRight) {
            do {
                maxLeft = maxOf(maxLeft, height[i])
                i++
            } while ((i < height.size - 1) && height[i] < maxLeft)
        } else {
            i++
            j--
        }

    } while (i < j)

    return maxArea
}