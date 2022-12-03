fun main() {
    fun part1(input : List<String>) : Int {
        val itemValues = mutableListOf<Int>()
        input.forEach {
            val comp1 = it.substring(0, it.length / 2).toSet()
            val comp2 = it.substring(it.length / 2, it.length).toSet()
            val overlap = comp1.intersect(comp2).first()
            if (overlap.isLowerCase()) {
                itemValues.add(overlap.code - 96)
            } else {
                itemValues.add(overlap.code - 38)
            }
        }
        return itemValues.sum()
    }

    fun part2(input : List<String>) : Int {
        val badgeValues = mutableListOf<Int>()
        input.chunked(3).forEach {
            val comp1 = it[0].toSet()
            val comp2 = it[1].toSet()
            val comp3 = it[2].toSet()
            val overlap = comp1.intersect(comp2).intersect(comp3).first()
            if (overlap.isLowerCase()) {
                badgeValues.add(overlap.code - 96)
            } else {
                badgeValues.add(overlap.code - 38)
            }
        }
        return badgeValues.sum()
    }

    val input = readInput("Input03")
    println(part1(input)) // 7990
    println(part2(input)) // 2602
}