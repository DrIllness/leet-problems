/**
 * 12. Integer to Roman
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Seven different symbols represent Roman numerals with the following values:
 *
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 *
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
 * Given an integer, convert it to a Roman numeral.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 3749
 *
 * Output: "MMMDCCXLIX"
 *
 * Explanation:
 *
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 *  700 = DCC as 500 (D) + 100 (C) + 100 (C)
 *   40 = XL as 10 (X) less of 50 (L)
 *    9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 * Example 2:
 *
 * Input: num = 58
 *
 * Output: "LVIII"
 *
 * Explanation:
 *
 * 50 = L
 *  8 = VIII
 * Example 3:
 *
 * Input: num = 1994
 *
 * Output: "MCMXCIV"
 *
 * Explanation:
 *
 * 1000 = M
 *  900 = CM
 *   90 = XC
 *    4 = IV
 *
 *
 * Constraints:
 *
 * 1 <= num <= 3999
 *
 *
 * */

fun main() {
    println(intToRomanShort(58))
    println(intToRomanShort(1994))
    println(intToRomanShort(3))
}

fun intToRoman(num: Int): String {
    var newNum = num
    var output = ""

    // millennium
    var m = newNum / 1000
    while (m-- > 0) {
       output += "M"
    }
    // centuries
    newNum = num % 1000
    var c = newNum / 100
    if (c in 1..3) {
        for (i in 1 ..  c) {
            output += "C"
        }
    } else if (c == 4) {
        output += "CD"
    } else if (c == 5) {
        output += "D"
    } else if (c in 6..8) {
        output += "D"
        for (i in 6..c) {
            output += "C"
        }
    } else if (c == 9) {
        output += "CM"
    }

    // dec
    newNum = num % 100
    var d = newNum / 10
    if (d in 1..3) {
        for (i in 1 ..  d) {
            output += "X"
        }
    } else if (d == 4) {
        output += "XL"
    } else if (d == 5) {
        output += "L"
    } else if (d in 6..8) {
        output += "L"
        for (i in 6..d) {
            output += "X"
        }
    } else if (d == 9) {
        output += "XC"
    }

    // num
    newNum = num % 10
    var n = newNum
    if (n in 1..3) {
        for (i in 1..n) {
            output += "I"
        }
    } else if (n == 4) {
        output += "IV"
    } else if (n == 5) {
        output += "V"
    } else if (n in 6..8) {
        output += "V"
        for (i in 6..n) {
            output += "I"
        }
    } else if (n == 9) {
        output += "IX"
    }

    return output
}

fun intToRomanShort(num: Int): String {
    var romanNums = listOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )

    var newNum = num
    var output = ""

    for (pair in romanNums) {
        while (newNum >= pair.first) {
            newNum -= pair.first
            output += pair.second
        }
    }

    return output
}