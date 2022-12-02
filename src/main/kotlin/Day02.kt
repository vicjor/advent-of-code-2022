fun main() {
    val input = readInput("Input02")
    val scoreMap = mapOf(
        "A X" to 4,
        "A Y" to 8,
        "A Z" to 3,
        "B X" to 1,
        "B Y" to 5,
        "B Z" to 9,
        "C X" to 7,
        "C Y" to 2,
        "C Z" to 6
    )
    val scoreMap2 = mapOf(
        "A X" to 3,
        "A Y" to 4,
        "A Z" to 8,
        "B X" to 1,
        "B Y" to 5,
        "B Z" to 9,
        "C X" to 2,
        "C Y" to 6,
        "C Z" to 7
    )

    fun part1(input : List<String>) : Int =
        input.sumOf { scoreMap[it] ?: 0}


    fun part2(input : List<String>) : Int =
         input.sumOf { scoreMap2[it] ?: 0 }


    println(part1(input)) // 17189
    println(part2(input)) // 13490
}