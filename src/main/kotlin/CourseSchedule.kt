/**
 *207. Course Schedule
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * */

fun main() {
    println(canFinish(
        2,
        arrayOf(intArrayOf(1, 0))
    ))

    println(canFinish(
        2,
        arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
    ))

    println(canFinish(
        2,
        arrayOf(intArrayOf(0, 1))
    ))

}

fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val inDegreeCounter = IntArray(numCourses) { 0 }
    val outDegreeMap = hashMapOf<Int, HashSet<Int>>()
    val passingQueue = ArrayDeque<Int>()

    for (p in prerequisites) {
        inDegreeCounter[p[0]] += 1
        if (outDegreeMap[p[1]] == null)
            outDegreeMap[p[1]] = hashSetOf()

        outDegreeMap[p[1]]?.add(p[0])
    }
    var counter = 0
    inDegreeCounter.forEachIndexed { index, count ->
        if (count == 0) {
            passingQueue.addLast(index)
            counter++
        }
    }

    while (passingQueue.isEmpty().not()) {
        val courseToPass = passingQueue.removeFirst()
        outDegreeMap[courseToPass]?.let { outDegree ->
            for (d in outDegree) {
                inDegreeCounter[d] -= 1
                if (inDegreeCounter[d] == 0) {
                    passingQueue.addLast(d)
                    counter++
                }
            }
        }
    }

    return counter >= numCourses
}