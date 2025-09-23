/**
 * 169. Majority Element
 * Solved
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * */


fun main(args: Array<String>) {
    val input = intArrayOf(1, 1, 1, 2, 2, 2, 1, 1, 1, 3, 2, 1, 1)
    val expectedOutput = 1
    println("expected output == $expectedOutput, actual output == ${majorityElement(input)}")
}

// simple hashmap solution, O(n) space, O(n) + O(n/2) time
/*fun majorityElement(nums: IntArray): Int {

    val elementsEncounter = hashMapOf<Int, Int>()
    nums.forEach {
        elementsEncounter[it] = elementsEncounter[it]?.inc() ?: 1
    }

    var majorElement = 0
    var majorElementTimes = 0
    elementsEncounter.forEach {
        if (it.value > majorElementTimes) {
            majorElement = it.key
            majorElementTimes = it.value
        }
    }

    return majorElement
}*/

// O(1) space, O(n) time
fun majorityElement(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var currentCandidate = nums[0]
    var currentCandidateScore = 1

    for (i in 1 until nums.size) {
        if (currentCandidate == nums[i]) currentCandidateScore++
        else
            currentCandidateScore--

        if (currentCandidateScore == 0) {
            currentCandidateScore = 1
            currentCandidate = nums[i]
        }
    }

    return currentCandidate
}
