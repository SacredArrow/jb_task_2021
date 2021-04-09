import org.junit.Test

class FirstTest {

    @Test
    fun simpleTest() {
        val graph = buildGraph(mutableListOf("andrey", "andreyka", "andrysha"))
        val result = graph.topologicalSort()
        assert(result.indexOf('e') < result.indexOf('y'))
    }

    @Test(expected = ImpossibleOrderException::class)
    fun substringTest() {
        val graph = buildGraph(mutableListOf("andreyka", "andrey"))
        graph.topologicalSort()
    }

    @Test(expected = ImpossibleOrderException::class)
    fun cycleTest() {
        val graph = buildGraph(mutableListOf("ab", "aa", "ba"))
        graph.topologicalSort()
    }

    @Test
    fun simpleTest2() {
        val graph = buildGraph(mutableListOf("gennadii", "ivan", "andrei", "alexandr"))
        val result = graph.topologicalSort()
        assert(result.indexOf('g') < result.indexOf('i'))
        assert(result.indexOf('i') < result.indexOf('a'))
        assert(result.indexOf('n') < result.indexOf('l'))
    }
}