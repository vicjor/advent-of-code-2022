import java.lang.Error

fun main() {
    fun part1(commands : List<String>) : Int {
        val root = Directory(null,"/")
        val fileSystem = FileSystem(root)
        fileSystem.runScript(commands)
        return fileSystem.getFileSystemSize()
    }
    fun part2(commands : List<String>) : Int {
        val root = Directory(null,"/")
        val fileSystem = FileSystem(root)
        fileSystem.runScript(commands)

        val totalDiskSpace = 70000000
        val requiredDiskSpace = 30000000
        val freeSpace = totalDiskSpace - root.getTotalSize()
        val minToDelete = requiredDiskSpace - freeSpace
        val allDirectories = root.getAllDirectoriesBelow()

        return allDirectories.minOf {
            if (it.getTotalSize() > minToDelete) {
                it.getTotalSize()
            }
            else totalDiskSpace + 1
        }
    }

    val input = readInput("Input07").drop(1)
    println(part1(input)) // 1513699
    println(part2(input))

}

class FileSystem(val root: Directory) {

    var cwd = root

    fun runScript(commands: List<String>) {
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
            cwd.addDirectory(Directory(cwd, input.substringAfter(" ")))
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
        return root.getAllDirectoriesBelow()
            .filter { it.getTotalSize() < 100000 }
            .sumOf { it.getTotalSize() }
    }
}

class Directory (val parent: Directory?, val dir : String) {
    var directories = mutableListOf<Directory>()
    var files = mutableListOf<File>()
    fun addDirectory(child : Directory) {
        directories.add(child)
    }

    fun addFile(file : File) {
        files.add(file)
    }

    fun cd(dir : String) : Directory {
        if (dir == ".." && parent != null) {
            return parent
        }
        val child = directories.find { it.dir == dir }
        if (child != null) {
            return child
        }
        throw Error("Invalid directory: " + dir)

    }

    fun getTotalSize() : Int {
        return files.sumOf { it.size } +
                directories.sumOf { it.getTotalSize() }
    }

    fun getAllDirectoriesBelow() : List<Directory> {
        return this.directories +
                this.directories.flatMap { it.getAllDirectoriesBelow() }
    }

    override fun toString(): String {
        return dir
    }
}

data class File(val name : String, val size : Int)