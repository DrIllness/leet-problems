/***
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * Example 3:
 *
 * Input: nums = [1,0,1,2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 */

fun main() {
    println(longestConsecutiveSet(intArrayOf(100, 4, 200, 1, 3, 2)))
    //println(longestConsecutive(intArrayOf(0,3,7,2,5,8,4,6,0,1)))
    //println(longestConsecutive(intArrayOf(1,0,1,2)))
}

fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    var map = hashMapOf<Int, Int>()
    var longestSequence = 1
    var i = 0
    while (i < nums.size) {
        if (map.containsKey(nums[i])) {
            i++
            continue
        }

        val num = nums[i]
        val right = map[num + 1] ?: 0
        val left = map[num - 1] ?: 0
        val total = right + left + 1 // 1 for start of sequence

        map[num] = total
        if (right > 0) map[num + right] = total
        if (left > 0) map[num - left] = total
        if (total > longestSequence) longestSequence = total

        i++
    }

    return longestSequence
}


private fun longestConsecutiveSet(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val set = HashSet<Int>(nums.size * 2)
    for (x in nums) set.add(x)

    var best = 0
    for (x in set) {
        if (!set.contains(x - 1)) { // important for skipping sub-sequnces
            var y = x
            var len = 1
            while (set.contains(y + 1)) {
                y++
                len++
            }
            if (len > best) best = len
        }
    }
    return best
}