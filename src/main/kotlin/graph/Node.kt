package graph

class Node(val letter: Char) {
    var color: NodeColor = NodeColor.WHITE
    private val adjacentNodes = mutableListOf<Node>()

    fun addAdjacent(node : Node) {
        adjacentNodes.add(node)
    }

    fun getAdjacent(): List<Node> {
        return adjacentNodes.toList()
    }

}