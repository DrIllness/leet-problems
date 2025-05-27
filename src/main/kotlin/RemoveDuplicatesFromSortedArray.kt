/**
 *
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially.
 * The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 * Custom Judge:
 *
 * The judge will test your solution with the following code:
 *
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 *
 * int k = removeDuplicates(nums); // Calls your implementation
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * */

fun main(args: Array<String>) {
    test()
}

private fun test() {
    val inputToExpectedOutput = arrayListOf<Pair<Int, IntArray>>()
    inputToExpectedOutput.add(5 to intArrayOf(1, 2, 2, 2, 3, 4,5,5))
    inputToExpectedOutput.add(2 to intArrayOf(1, 1, 2))
    inputToExpectedOutput.add(2 to intArrayOf(1, 2))
    inputToExpectedOutput.add(5 to intArrayOf(0,0,1,1,1,2,2,3,3,4))
    inputToExpectedOutput.add(1 to intArrayOf(1))
    inputToExpectedOutput.add(1 to intArrayOf(1, 1))
    var res: Int
    inputToExpectedOutput.forEach { pair ->
        res = removeDuplicates(pair.second)
        for (i in 0 until  res) {
            println(pair.second[i])
        }
        println("=======")

    }

}

fun removeDuplicates(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    if (nums.size == 1) return 1

    var i = 0
    var j = 1
    var currValue = nums[i]
    while (i <= nums.indices.last) {
        if (nums[i] > currValue) {
            currValue = nums[i]
            nums[j] = currValue
            j++
            i++
        } else
            i++
    }

    return j
}

fun removeDuplicates2(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    if (nums.size == 1) return 1

    var i = 0
    var j = 0
    while (i <= nums.indices.last) {
        if (nums[i] > nums[j]) {
            nums[++j] = nums[i]
        }
        i++
    }

    j++

    return j
}