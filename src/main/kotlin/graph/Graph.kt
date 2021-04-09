package graph

import kotlin.system.exitProcess

class Graph {
    private val nodes = mutableMapOf<Char, Node>()
    private val order = mutableListOf<Char>()

    private fun getNode(letter: Char): Node {
        lateinit var node : Node
        if (letter in nodes.keys) {
            node = nodes[letter]!!
        } else {
            node = Node(letter)
            nodes[letter] = node
        }
        return node
    }

    fun addEdge(from : Char, to : Char) {
        val nodeA = getNode(from)
        val nodeB = getNode(to)
        nodeA.addAdjacent(nodeB)
    }

    fun addMissingLetters() {
        var c = 'a'
        while (c <= 'z') {
            getNode(c)
            c++
        }
    }

    fun topologicalSort() : List<Char> {
        for ((_, node) in nodes) { // Mark all nodes as not visited (needed if sorting is run few times
            node.color = NodeColor.WHITE
        }
        order.clear()

        for ((_, node) in nodes) {
            if (node.color == NodeColor.WHITE) {
                dfs(node)
            }
        }
        return order
    }

    private fun dfs(node: Node) {
        node.color = NodeColor.GRAY
        for (node2 in node.getAdjacent()) {
            if (node2.color == NodeColor.WHITE) {
                dfs(node2)
            } else if (node2.color == NodeColor.GRAY) {
                println("Impossible")
                exitProcess(0)
            }
        }
        node.color = NodeColor.BLACK
        order.add(node.letter)
    }
}