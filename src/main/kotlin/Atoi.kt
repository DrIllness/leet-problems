fun main(args: Array<String>) {
    println(myAtoi("-1337c0d3"))
    println(myAtoi("    -52"))
    println(myAtoi("asda324"))
    println(myAtoi("21114234234234234234234"))
    println(myAtoi("0-1"))
    println(myAtoi("-91283472332"))
}

fun myAtoi(s: String): Int {
    var i = 0
    var res = 0
    while (i < s.length && s[i] == ' ') i++
    var pos = true
    if (i < s.length && (s[i] == '+' || s[i] == '-')) {
        pos = s[i] == '+'
        i++
    }
    while (i < s.length && s[i] in '0'..'9') {
        var digit = s[i] - '0'
        if (pos) {
            if (res > (Int.MAX_VALUE - digit) / 10) return Int.MAX_VALUE
            res = res * 10 + digit
        } else {
            if (res < (Int.MIN_VALUE + digit) / 10) return Int.MIN_VALUE
            res = res * 10 - digit
        }
        i++
    }
    return res
}