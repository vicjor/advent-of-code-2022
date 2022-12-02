
fun main() {
    val input = readInput("Input01")

    fun getElvesTotalCalories(input : List<String>) : List<Int> {
        val elves = mutableListOf<Int>()
        var current = 0
        input.forEach {
            if (it.isEmpty()) {
                current = 0
            }
            else {
                current += it.toInt()
                elves.add(current)
            }
        }

        elves.sort()
        return elves
    }
    fun part1(input : List<String>) : Int =
        getElvesTotalCalories(input).takeLast(1).sum()


    fun part2(input: List<String>) : Int =
        getElvesTotalCalories(input).takeLast(3).sum()


    println(part1(input)) // 68787
    println(part2(input)) // 198041
}