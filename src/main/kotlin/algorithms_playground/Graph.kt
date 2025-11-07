package algorithms_playground

fun main() {
    val graph = Graph<Int>()
    graph.addEdge(1, 2)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)

    for (i in bfs(graph, 1)) {
        print("$i ")
    }
    println()
    for (i in dfs(graph, 1)) {
        print("$i ")
    }

}

class Graph<T> {
    private val adjacencyList: MutableMap<T, MutableList<T>> = mutableMapOf()

    fun addVertex(vertex: T) {
        adjacencyList.putIfAbsent(vertex, mutableListOf())
    }

    fun addEdge(source: T, destination: T) {
        adjacencyList.putIfAbsent(source, mutableListOf())
        adjacencyList.putIfAbsent(destination, mutableListOf())

        adjacencyList[source]?.add(destination)
        adjacencyList[destination]?.add(source)
    }

    fun getNeighbours(vertex: T) = adjacencyList[vertex] ?: emptyList<T>()

    fun getVertices(): Set<T> = adjacencyList.keys

    override fun toString(): String =
        adjacencyList.entries.joinToString("\n") { (v, neighbors) -> "$v -> $neighbors" }

}

fun <T> bfs(graph: Graph<T>, start: T): List<T> {
    val visitedVertices: MutableSet<T> = mutableSetOf()
    val queue: ArrayDeque<T> = ArrayDeque()
    val order: MutableList<T> = mutableListOf()

    queue.add(start)
    visitedVertices.add(start)

    while (queue.isNotEmpty()) {
        val vertex = queue.removeFirst()
        order.add(vertex)

        for (neighbor in graph.getNeighbours(vertex)) {
            if (neighbor !in visitedVertices) {
                queue.add(neighbor)
                visitedVertices.add(neighbor)
            }
        }
    }

    return order
}

fun <T> dfs(graph: Graph<T>, start: T): List<T> {
    val visitedVertices: MutableSet<T> = mutableSetOf()
    val stack: ArrayDeque<T> = ArrayDeque()
    val order: MutableList<T> = mutableListOf()

    stack.add(start)
    while (stack.isNotEmpty()) {
        val vertex = stack.removeLast()
        if (vertex !in visitedVertices) {
            visitedVertices.add(vertex)
            order.add(vertex)

            for (neighbor in graph.getNeighbours(vertex).asReversed()) {
                if (neighbor !in visitedVertices) {
                    stack.add(neighbor)
                }
            }
        }
    }

    return order
}

fun <T> dfsR(graph: Graph<T>, start: T): List<T> {
    val visited = mutableSetOf<T>()
    val order = mutableListOf<T>()

    fun dfs(vertex: T) {
        visited.add(vertex)
        order.add(vertex)

        for (neighbor in graph.getNeighbours(vertex)) {
            if (neighbor !in visited) {
                dfs(neighbor)
            }
        }
    }

    dfs(start)
    return order
}