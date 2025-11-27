/**
 * 66. Plus One

 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
 * The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 * Example 2:
 *
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].
 * Example 3:
 *
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 *
 *
 * Constraints:
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits does not contain any leading 0's.
 * */

fun main() {
    plusOne(intArrayOf(4, 3, 2, 9)).printArr()
    plusOne(intArrayOf(1, 2, 9)).printArr()
    plusOne(intArrayOf(1, 2, 9)).printArr()
    plusOne(intArrayOf(1, 2, 9, 9, 9, 9)).printArr()
    plusOne(intArrayOf(9)).printArr()
}

fun plusOne1(digits: IntArray): IntArray {
    var carryOver = 1
    var i = digits.size - 1
    while (i >= 0 && carryOver > 0) {
        digits[i] += carryOver
        if (digits[i] == 10) {
            digits[i] = 0
        } else {
            carryOver = 0
        }
        i--
    }
    return if (i == -1 && carryOver > 0) {
        val newArr = IntArray(digits.size + 1)
        newArr[0] = 1
        newArr
    } else {
        return digits
    }
}

fun plusOne(digits: IntArray): IntArray {

    fun sumWithCarryOver(index: Int, digits: IntArray): Int {
        digits[index] += 1

        if (digits[index] == 10) {
            digits[index] = 0
            return if (index == 0) 1
            else
                sumWithCarryOver(index - 1, digits)
        }

        return 0
    }

    return if (sumWithCarryOver(digits.lastIndex, digits) > 0) {
        val newArr = IntArray(digits.size + 1)
        newArr[0] = 1
        newArr
    } else {
        digits
    }
}
