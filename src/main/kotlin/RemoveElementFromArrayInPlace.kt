/**
 *  * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 *
 * https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
 * */

fun main(args: Array<String>) {
    test()
}

fun test() {
    val setOfInput = linkedSetOf<Pair<Int, IntArray>>()
    setOfInput.add(2 to intArrayOf(0,1,2,2,3,0,4,2))
    setOfInput.add(3 to intArrayOf(3, 2, 2, 3))
    setOfInput.add(2 to intArrayOf(3, 2, 2, 3))
    setOfInput.add(2 to intArrayOf(1))
    setOfInput.add(3 to intArrayOf(2))
    setOfInput.add(1 to intArrayOf(1))
    setOfInput.add(5 to intArrayOf(3, 3))
    setOfInput.add(3 to intArrayOf(3, 3))

    for (i in setOfInput) {
        print("source array = [")
        for (i in i.second) {
            print("$i ")
        }
        print("], number to be removed = ${i.first}\n")
        val res = removeElement2(i.second, i.first)
        var j = 0
        print("Result == [")
        while (j < res) {
            print("${i.second[j]} ")
            j++
        }
        println("], size of resulting array == $res")
        println("======================")
    }
}


fun removeElement(nums: IntArray, `val`: Int): Int {
    if (nums.isEmpty())
        return 0

    var i = 0
    var j = nums.size - 1
    if (i == j) {
        if (nums[i] != `val`) return 1
        else return 0
    }

    var counter = 0
    while (i <= j) {
        if (j == 0 || i == nums.size) break
        while (i < nums.size && nums[i] != `val`) {
            i++
            counter++
        }
        while (j > 0 && nums[j] == `val`) j--

        if (i < j) {
            nums[i++] = nums[j]
            nums[j--] = `val`
            counter++
        }
    }

    return counter
}

fun removeElement2(nums: IntArray, `val`: Int): Int {
    var k = 0

    for (i in 0 until nums.size) {
        if (nums[i] != `val`) {
            nums[k] = nums[i]
            k++
        }
    }

    return k
}