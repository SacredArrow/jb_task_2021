import graph.Graph

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var previous = readLine()!!
    val graph = Graph() // We will build a graph and calculate topological sorting

    repeat(n - 1) { // Build a graph
        val name = readLine()!!
        val minLength = minOf(name.length, previous.length)
        var firstDifferencePos = -1
        for (i in 0 until minLength) { // Find first position that is different
            if (name[i] != previous[i]) {
                firstDifferencePos = i
                break
            }
        }
        if (firstDifferencePos != -1) { // If found, we add it to the graph
            graph.addEdge(name[firstDifferencePos], previous[firstDifferencePos])
        } else if (previous.length > name.length) {
            // If not found, previous should have <= length. Otherwise it can't be lexicographical order
            println("Impossible")
            return
        }
        previous = name
    }
    val order = graph.topologicalSort()
    for (letter in order) {
        print("$letter ")
    }



}