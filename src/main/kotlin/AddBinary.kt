/**
 * 67. Add Binary
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 *
 * */

fun main() {
    println(addBinary("11", "1"))
    println(addBinary("1010", "1011"))
}

fun addBinary(a: String, b: String): String {
    var ai = a.length - 1
    var bi = b.length - 1
    var i = if (ai > bi) ai else bi
    var j = 0
    var result = StringBuilder()
    var overflow = 0
    while (j <= i) {

        var aNum = 0
        var bNum = 0
        if (ai >= 0) { aNum = if (a[ai] == '1') 1 else 0 }
        if (bi >= 0) { bNum = if (b[bi] == '1') 1 else 0 }
        var sum = aNum + bNum + overflow
        when(sum) {
            0 -> {
                result.append('0')
                overflow = 0
            }
            1 -> {
                result.append('1')
                overflow = 0
            }
            2 -> {
                result.append('0')
                overflow = 1
            }
            3 -> {
                result.append('1')
                overflow = 1
            }

        }

        j++
        ai--
        bi--
    }

    if (overflow != 0)
        result.append("1")

    return result.toString().reversed()
}