fun main(args: Array<String>) {
    println("Program argume0nts: ${args.joinToString()}")
    val res = carsInGarage(3, arrayListOf(Pair(1, 3), Pair(2, 3), Pair(3,3))) // 0 1 2 3
    res.forEach {
        println(it)
    }
}

fun carsInGarage(seconds: Int, tickets: List<Pair<Int, Int>>): IntArray {
    if (seconds <= 0) return intArrayOf()
    val result = IntArray(seconds + 1)
    if (tickets.isEmpty()) {
        return result
    }

    val diff = IntArray(seconds + 2)
    for (pair in tickets) {
        if (pair.first < diff.size) {
            diff[pair.first] += 1
        }
        if ((pair.second + 1) < diff.size) {
            diff[pair.second + 1] -= 1
        }
    }

    var count = 0
    for (i in result.indices) {
        count += diff[i]
        result[i] = count
    }

    return result
}