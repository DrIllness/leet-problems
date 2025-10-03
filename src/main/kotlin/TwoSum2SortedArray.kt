/**
 * 167. Two Sum II - Input Array Is Sorted
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1]
 * and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer
 * array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 *
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 *
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 *
 *
 * Constraints:
 *
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 *
 * */

fun main() {
    twoSum2(intArrayOf(2,7,11,15, 16, 18, 22), 23).printArr()
    //twoSum(intArrayOf(2,3,4), 6).printArr()
    //twoSum(intArrayOf(-1,0), -1).printArr()
}

fun twoSum(numbers: IntArray, target: Int): IntArray {
    if (numbers.size == 2) return intArrayOf(1, 2)

    val answer = IntArray(2) { 0 }
    var slowPointer = 0
    var fastPointer: Int
    while (slowPointer < numbers.size && answer[0] == 0) {
        fastPointer  = slowPointer + 1
        while (fastPointer < numbers.size) {
            if (numbers[slowPointer] + numbers[fastPointer] == target) {
                answer[0] = slowPointer + 1
                answer[1] = fastPointer + 1
                break
            }
            fastPointer++
        }
        slowPointer++
    }

    return answer
}

fun twoSum2(numbers: IntArray, target: Int): IntArray {
    if (numbers.size == 2) return intArrayOf(1, 2)

    val answer = IntArray(2) { 0 }
    var bigPointer = numbers.size - 1
    var smallPointer = 0
    while (smallPointer < bigPointer) {
        if (numbers[smallPointer] + numbers[bigPointer] > target) {
            bigPointer--
        } else if (numbers[smallPointer] + numbers[bigPointer] < target) {
            smallPointer++
        } else {
            answer[0] = smallPointer + 1
            answer[1] = bigPointer + 1
            break
        }
    }

    return answer
}