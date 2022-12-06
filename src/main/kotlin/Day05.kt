import java.io.File

fun main() {

    fun getFinalString(stacks : MutableList<ArrayDeque<Char>>) : String {
        var finalString = ""
        stacks.forEach {
            finalString += it.removeLast()
        }
        return finalString
    }

    fun part1(input : String) : String {
        val cargo = Cargo()
        input
            .split("\n\n")[1]
            .split("\n")
            .dropLast(1)
            .forEach {
                cargo.move(it)
            }
        return getFinalString(cargo.crates)
    }

    fun part2(input : String) : String {
        val cargo = Cargo()
        input
            .split("\n\n")[1]
            .split("\n")
            .dropLast(1)
            .forEach {
                cargo.move2(it)
            }
        return getFinalString(cargo.crates)
    }


    val input = File("src/main/resources/", "Input05.txt").readText()
    println(part1(input)) // BWNCQRMDB
    println(part2(input)) // NHWZCBNBF
}

class Cargo {
    var crates = mutableListOf(
        ArrayDeque(listOf('B', 'Q', 'C')),
        ArrayDeque(listOf('R', 'Q', 'W', 'Z')),
        ArrayDeque(listOf('B', 'M', 'R', 'L', 'V')),
        ArrayDeque(listOf('C', 'Z', 'H', 'V', 'T', 'W')),
        ArrayDeque(listOf('D', 'Z', 'H', 'B', 'N', 'V', 'G')),
        ArrayDeque(listOf('H', 'N', 'P', 'C', 'J', 'F', 'V', 'Q')),
        ArrayDeque(listOf('D', 'G', 'T', 'R', 'W', 'Z', 'S')),
        ArrayDeque(listOf('C', 'G', 'M', 'N', 'B', 'W', 'Z', 'P')),
        ArrayDeque(listOf('N', 'J', 'B', 'M', 'W', 'Q', 'F', 'P')),
    )

    fun move(step : String) {
        val quantity = step.split(" ")[1].toInt()
        val from = step.split(" ")[3].toInt()
        val to = step.split(" ")[5].toInt()
        for (i in 1 ..quantity) {
            this.crates[to-1].add(this.crates[from-1].removeLast())
        }
    }

    fun move2(step : String) {
        val quantity = step.split(" ")[1].toInt()
        val from = step.split(" ")[3].toInt() - 1
        val to = step.split(" ")[5].toInt() - 1
        var temp = mutableListOf<Char>()

        for (i in 1 ..quantity) {
            temp.add(this.crates[from].removeLast())
        }
        for (i in 1..quantity) {
            this.crates[to].add(temp.removeLast())
        }
    }

}