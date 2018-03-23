package it.xaos.maze

class Grid(val rows: Int, val columns: Int) {
    private val cells = Array(rows, { i -> Array(columns, { j -> Cell(i, j)})})

    init{
        cells.forEach {
            it->
            it.forEach {
                cell->
                if(cell.row -1 >= 0)
                    cell.north = cells[cell.row-1][cell.column]
                if(cell.row +1 <= rows)
                    cell.south = cells[cell.row+1][cell.column]
                if(cell.column-1>=0)
                    cell.east = cells[cell.row][cell.column-1]
                if(cell.column+1 <= columns)
                    cell.west = cells[cell.row][cell.column+1]
            }
        }
    }

}

class Cell(val row: Int, val column: Int) {
    val links = mutableListOf<Cell>()

    lateinit var north: Cell
    lateinit var south: Cell
    lateinit var east: Cell
    lateinit var west: Cell

    fun link(cell: Cell, bidi: Boolean = true) {
        links.add(cell)
        cell.link(this, false)
    }

    fun unlink(cell: Cell) {
        links.remove(cell)
        cell.unlink(this)
    }

    fun isLinkedWith(cell: Cell) = links.contains(cell)

    fun neighbors() = listOf(north, south, east, west)
}
