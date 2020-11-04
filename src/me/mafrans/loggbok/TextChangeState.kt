package me.mafrans.loggbok

import kotlin.math.max

class TextChangeState(val oldText: String, val newText: String) {
    fun generateChanges(): Collection<LineChange> {
        val changes = HashSet<LineChange>()
        val oldLines = this.oldText.lines()
        val newLines = this.newText.lines()

        val limit = max(newLines.size, oldLines.size)
        for (i in 0 until limit) {
            val newLine = if (newLines.size > i) newLines[i] else null
            val oldLine = if (oldLines.size > i) oldLines[i] else null

            if (newLine != oldLine) {
                changes.add(LineChange(i, oldLine, newLine))
            }
        }

        return changes
    }
}

class LineChange(index: Int, oldText: String?, newText: String?) {
    val index: Int = index
    val oldText: String? = oldText
    val newText: String? = newText
}