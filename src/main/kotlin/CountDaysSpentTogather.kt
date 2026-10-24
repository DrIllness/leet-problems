/**
 * 2409. Count Days Spent Together
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * Alice and Bob are traveling to Rome for separate business meetings.
 *
 * You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the date.
 *
 * Return the total number of days that Alice and Bob are in Rome together.
 *
 * You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
 *
 *
 *
 * Example 1:
 *
 * Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
 * Output: 3
 * Explanation: Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
 * Example 2:
 *
 * Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
 * Output: 0
 * Explanation: There is no day when Alice and Bob are in Rome together, so we return 0.
 *
 *
 * Constraints:
 *
 * All dates are provided in the format "MM-DD".
 * Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
 * The given dates are valid dates of a non-leap year.
 *
 * */

fun main() {
    println(countDaysTogether("08-15", "08-18", "08-16", "08-19"))
    println(countDaysTogether(arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"))
    println(countDaysTogether(arriveAlice = "01-01", leaveAlice = "03-03", arriveBob = "02-02", leaveBob = "03-03"))
}

private fun countDaysTogether(arriveAlice: String, leaveAlice: String, arriveBob: String, leaveBob: String): Int {
    val numOfDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    fun absDayStart(m: Int, d: Int): Int {
        val fm = m - 2
        val days = if (fm < 0) 0 else {
            var s = 0
            for (i in 0..fm) {
                s += numOfDaysInMonth[i]
            }
            s
        }

        return days + d
    }
    val bobDaysStart = absDayStart(arriveBob.substring(0,2).toInt(), arriveBob.substring(3,5).toInt())
    val bobDaysEnd = absDayStart(leaveBob.substring(0,2).toInt(), leaveBob.substring(3,5).toInt())

    val aliceDaysStart = absDayStart(arriveAlice.substring(0,2).toInt(), arriveAlice.substring(3,5).toInt())
    val aliceDaysEnd = absDayStart(leaveAlice.substring(0,2).toInt(), leaveAlice.substring(3,5).toInt())

    if (bobDaysStart > aliceDaysEnd || aliceDaysStart > bobDaysEnd) return 0

    return minOf(bobDaysEnd, aliceDaysEnd) - maxOf(aliceDaysStart, bobDaysStart) + 1
}