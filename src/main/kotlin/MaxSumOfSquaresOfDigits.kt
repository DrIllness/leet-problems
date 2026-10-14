import java.util.Arrays

/**
 * 3723. Maximize Sum of Squares of Digits
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * You are given two positive integers num and sum.
 *
 * A positive integer n is good if it satisfies both of the following:
 *
 * The number of digits in n is exactly num.
 * The sum of digits in n is exactly sum.
 * The score of a good integer n is the sum of the squares of digits in n.
 *
 * Return a string denoting the good integer n that achieves the maximum score.
 * If there are multiple possible integers, return the maximum one.
 * If no such integer exists, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 2, sum = 3
 *
 * Output: "30"
 *
 * Explanation:
 *
 * There are 3 good integers: 12, 21, and 30.
 *
 * The score of 12 is 12 + 22 = 5.
 * The score of 21 is 22 + 12 = 5.
 * The score of 30 is 32 + 02 = 9.
 * The maximum score is 9, which is achieved by the good integer 30. Therefore, the answer is "30".
 *
 * Example 2:
 *
 * Input: num = 2, sum = 17
 *
 * Output: "98"
 *
 * Explanation:
 *
 * There are 2 good integers: 89 and 98.
 *
 * The score of 89 is 82 + 92 = 145.
 * The score of 98 is 92 + 82 = 145.
 * The maximum score is 145. The maximum good integer that achieves this score is 98. Therefore, the answer is "98".
 *
 * Example 3:
 *
 * Input: num = 1, sum = 10
 *
 * Output: ""
 *
 * Explanation:
 *
 * There are no integers that have exactly 1 digit and whose digits sum to 10. Therefore, the answer is "".
 *
 *
 *
 * Constraints:
 *
 * 1 <= num <= 2 * 105
 * 1 <= sum <= 2 * 106
 *
 * */

fun main() {
    println(maxSumOfSquares(2, 3))
    println(maxSumOfSquares(1, 10))
    println(maxSumOfSquares(2, 17))
    println(maxSumOfSquares(10, 3))
}

private fun maxSumOfSquares3(num: Int, sum: Int): String {
    if (sum > 9 * num) return ""

    var ans = StringBuilder(num)
    var balance = sum
    var i = 0
    while (balance >= 0 && i < num) {
        if (balance == 0) {
            ans.append(0)
        } else if (balance / 10 > 0) {
            ans.append(9)
            balance -= 9
        } else if (balance % 10 > 0) {
            ans.append(balance % 10)
            balance -= balance % 10
        }
        i++
    }

    if (balance > 0) {
        return ""
    }

    return ans.toString()
}

private fun maxSumOfSquares(num: Int, sum: Int): String {
    if (sum > 9 * num) return ""

    var ans = StringBuilder(num)
    val d = sum / 9
    val m = sum % 9
    var dAmount = 0
    for (i in 1..d) {
        ans.append(9)
        dAmount++
    }
    if (m > 0) {
        dAmount++
        ans.append(m)
    }

    val zeroAmount = num - dAmount
    for (i in 1..zeroAmount) {
        ans.append(0)
    }

    return ans.toString()
}


private fun maxSumOfSquares2(num: Int, sum: Int): String {
    val string = "9".repeat(sum / 9) + (if (sum % 9 > 0) sum % 9 else "")
    return if (string.length <= num) string + "0".repeat(num - string.length) else ""
}

private fun maxSumOfSquares4(num: Int, sum: Int): String {
    if (sum > 9L * num) return ""

    val nines = sum / 9
    val rem = sum % 9
    val neededDigits = nines + (if (rem > 0) 1 else 0)
    if (neededDigits > num) return ""

    val chars = CharArray(num)
    if (nines > 0) {
        Arrays.fill(chars, 0, nines, '9')
    }
    if (rem > 0) {
        chars[nines] = ('0'.code + rem).toChar()
    }
    val zerosStart = neededDigits
    if (zerosStart < num) {
        Arrays.fill(chars, zerosStart, num, '0')
    }

    return String(chars)
}