import java.io.File
fun main () {

    fun part1(input : String) : Int {
        var count = 4
        var start = -1
        input.windowed(size = 4, step = 1) {
            if (it.toSet().size == 4 && start == -1) {
                start = count
            }
            count += 1
        }
        return start
    }

    fun part2(input : String) : Int {
        var count = 14
        var start = -1
        input.windowed(size = 14, step = 1) {
            if (it.toSet().size == 14 && start == -1) {
                start = count
            }
            count += 1
        }
        return start
    }

    fun rfr(input: String) : Int {
        input.windowed(size = 4, step = 1) {

            }
    }

    val input = File("src/main/resources","Input06.txt").readText()
    println(part1(input)) // 1134
    println(part2(input)) // 2263
    println(rfr(input))
}