import graph.Graph

class ImpossibleOrderException(): Exception()

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val names = mutableListOf<String>()
    repeat(n) {
        names.add(readLine()!!)
    }
    lateinit var order : List<Char>
    try {
        val graph = buildGraph(names)
        order = graph.topologicalSort()
    } catch (e : ImpossibleOrderException) {
        return
    }
    for (letter in order) {
        print("$letter ")
    }



}

fun buildGraph(names: MutableList<String>) : Graph {
    val graph = Graph() // We will build a graph and calculate topological sorting
    var previous = names[0]
    for (name in names.drop(1)) { // Build a graph
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
            throw ImpossibleOrderException()
        }
        previous = name
    }
    // While constructing a graph, we only add letters that are in order. Now we should add the rest.
    graph.addMissingLetters()
    return graph
}