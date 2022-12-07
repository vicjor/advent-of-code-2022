import java.lang.Error

fun main() {
    fun part1(commands : List<String>) : Int {
        val root = Directory(null,"/")
        val fileSystem = FileSystem(root, commands)
        fileSystem.runScript()
        return fileSystem.getFileSystemSize()
    }
    fun part2() {

    }

    val input = readInput("Test07").drop(1)
    println(part1(input)) // 727028 too low, 1972248 too high
}

class FileSystem(val root: Directory, val commands: List<String>) {

    var cwd = root

    fun runScript() {
        commands.forEach {
            println(it)
            if (it[0] == '$') {
                executeCommand(it)
            } else {
                addChild(it)
            }
        }
    }

    fun addChild(input : String) {
        if (input.startsWith("dir")) {
            cwd.addChild(Directory(cwd, input.substringAfter(" ")))
        } else {
            cwd.addFile(File(
                input.substringAfter(" "),
                input.substringBefore(" ").toInt())
            )
        }
    }

    fun executeCommand(input : String ) {
        if (input[0] != '$') {
            throw Error("Commands must start with '$'")
        }
        val command = input.split(" ")[1]
        if (command == "ls") {
            return
        }
        val dir = input.split(" ")[2]
        cwd = cwd.cd(dir)
    }

    fun getFileSystemSize() : Int {
        return root.getTotalSize(100000)
    }
}

class Directory (val parent: Directory?, val dir : String) {
    var children = mutableListOf<Directory>()
    var files = mutableListOf<File>()
    fun addChild(child : Directory) {
        children.add(child)
    }

    fun addFile(file : File) {
        files.add(file)
    }

    fun cd(dir : String) : Directory {
        if (dir == ".." && parent != null) {
            return parent
        }
        val child = children.find { it.dir == dir }
        if (child != null) {
            return child
        }
        throw Error("Invalid directory: " + dir)

    }

    fun getTotalSize(max : Int) : Int {
        var size = 0
        children.forEach { it ->
            size += if (it.getTotalSize(max) <= max) {
                it.getTotalSize(max)
            } else 0
            var fileSize = files.sumOf {  it.size }
            if (size + fileSize <= max) {
                size += fileSize
            }
        }
        return size
    }

    override fun toString(): String {
        return dir
    }
}

data class File(val name : String, val size : Int)