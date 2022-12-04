fun main() {
    fun createRanges(pairs : String) : Pair<List<Int>, List<Int>> {
        return Pair(
            pairs.split(",").let {
                IntRange(
                    it[0].split("-")[0].toInt(),
                    it[0].split("-")[1].toInt()
                ).toList()
            },
            pairs.split(",").let {
                IntRange(
                    it[1].split("-")[0].toInt(),
                    it[1].split("-")[1].toInt()
                ).toList()
            }
        )
    }

    fun fullyContains(pairs : String) : Boolean {
        val ranges = createRanges(pairs)
        val range1 = ranges.first
        val range2 = ranges.second
        return range1.union(range2).size == range1.size || range2.union(range1).size == range2.size
    }

    fun isOverlappingRanges(pairs : String) : Boolean {
        val ranges = createRanges(pairs)
        val range1 = ranges.first
        val range2 = ranges.second
        return range1.size + range2.size != range1.union(range2).size
    }

    fun part1(input : List<String>) : Int =
        input.count { fullyContains(it) }


    fun part2(input : List<String>) : Int =
        input.count { isOverlappingRanges(it) }


    val input = readInput("Input04")
    println(part1(input)) // 599
    println(part2(input)) // 928
}