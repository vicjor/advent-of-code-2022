fun main() {
    val inputLines = readInput("Input08").map { it.toList().map { x -> x.digitToInt() } }

    fun isEdge(i : Int, j : Int) : Boolean {
        if (i == 0 || i == inputLines.size - 1 || j == 0 || j == inputLines[i].size -1) {
            return true
        }
        return false
    }

    fun northVisible(i : Int, j : Int) : Boolean {
        val cur = inputLines[i][j]
        for (index in j-1 downTo  0) {
            if (inputLines[i][index] >= cur) {
                return false
            }
        }
        return true
    }

    fun southVisible(i : Int, j : Int) : Boolean {
        val cur = inputLines[i][j]
        for (index in j+1 until  inputLines.size) {
            if (inputLines[i][index] >= cur) {
                return false
            }
        }
        return true
    }

    fun eastVisible(i : Int, j : Int) : Boolean {
        val cur = inputLines[i][j]
        for (index in i+1 until  inputLines[i].size) {
            if (inputLines[index][j] >= cur) {
                return false
            }
        }
        return true
    }

    fun westVisible(i : Int, j : Int) : Boolean {
        val cur = inputLines[i][j]
        for (index in i-1 downTo   0) {
            if (inputLines[index][j] >= cur) {
                return false
            }
        }
        return true
    }

    fun isVisible(i : Int, j : Int) : Boolean {
        val visibility = listOf<Boolean>(
            northVisible(i, j),
            southVisible(i, j),
            eastVisible(i, j),
            westVisible(i, j)
        )
        return visibility.any { it }
    }

    fun northVisibleCount(i : Int, j : Int) : Int {
        var count = 0
        val cur = inputLines[i][j]
        for (index in j-1 downTo  0) {
            count += 1
            if (inputLines[i][index] >= cur) {
                return count
            }
        }
        return count
    }

    fun southVisibleCount(i : Int, j : Int) : Int {
        var count = 0
        val cur = inputLines[i][j]
        for (index in j+1 until  inputLines.size) {
            count += 1
            if (inputLines[i][index] >= cur) {
                return count
            }
        }
        return count
    }

    fun westVisibleCount(i : Int, j : Int) : Int {
        var count = 0
        val cur = inputLines[i][j]
        for (index in i-1 downTo   0) {
            count += 1
            if (inputLines[index][j] >= cur) {
                return count
            }
        }
        return count
    }

    fun eastVisibleCount(i : Int, j : Int) : Int {
        var count = 0
        val cur = inputLines[i][j]
        for (index in i+1 until  inputLines[i].size) {
            count += 1
            if (inputLines[index][j] >= cur) {
                return count
            }
        }
        return count
    }

    fun scenicScore(i : Int, j : Int) : Int {
        val north = northVisibleCount(i, j)
        val south = southVisibleCount(i, j)
        val west = westVisibleCount(i, j)
        val east = eastVisibleCount(i, j)
        return north * south * east * west
    }

    fun part1(input : List<List<Int>>) : Int {
        var count = 0
        input.forEachIndexed {
            i, x -> x.forEachIndexed {
                j, _ -> if (!isEdge(i,j) && isVisible(i, j)) {
                    count += 1
                }
            }
        }
        val edgeSize = (inputLines.size-1) * 2 + (inputLines[0].size-1) * 2
        return count + edgeSize
    }



    fun part2() : Int {
        var max = 0
        inputLines.forEachIndexed {
            i, x -> x.forEachIndexed {
                j, _ -> if (scenicScore(i, j) > max) {
                    max = scenicScore(i, j)
                }
            }
        }
        return max

    }
    println(part1(inputLines)) // 1792 too low
    println(part2()) // 1923584 too high



}