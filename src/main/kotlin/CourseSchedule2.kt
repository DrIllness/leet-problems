/**
 * 210. Course Schedule II
 * Medium
 * Topics
 * premium lock icon
 * Companies
 * Hint
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * */

fun main() {
    findOrder(
        2,
        arrayOf(intArrayOf(1, 0))
    ).printArr()
    findOrder(
        2,
        arrayOf(intArrayOf(0, 1))
    ).printArr()

    findOrder(
        4,
        arrayOf(
            intArrayOf(1, 0),
            intArrayOf(2, 0),
            intArrayOf(3, 1),
            intArrayOf(3, 2)
        )
    ).printArr()

}

fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val inDegreeCounter = IntArray(numCourses) { 0 }
    val outDegreeMap = hashMapOf<Int, HashSet<Int>>()

    for (p in prerequisites) {
        inDegreeCounter[p[0]] += 1
        if (outDegreeMap[p[1]] == null)
            outDegreeMap[p[1]] = hashSetOf()

        outDegreeMap[p[1]]?.add(p[0])
    }
    val order = arrayListOf<Int>()

    var i = 0

    while (i < inDegreeCounter.size) {
        if (inDegreeCounter[i] == 0) {
            order.add(i)
            if (order.size == numCourses)
                return order.toIntArray()

            inDegreeCounter[i] = -1
            outDegreeMap[i]?.let { outDegree ->
                for (d in outDegree) {
                    inDegreeCounter[d] -= 1
                }
            }
            i = 0
        } else {
            i++
        }
    }

    return intArrayOf()
}