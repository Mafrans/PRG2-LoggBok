package me.mafrans.loggbok

import org.fusesource.jansi.Ansi

class Chapter(var index: Int, var title: String, var book: Book) {
    var pages: Set<Page> = HashSet();

    fun print() {
        val ansi = Ansi.ansi()
        ansi.fgBrightGreen().a("Chapter ${Util.toRoman(index)}: $title").newline()
        ansi.fgGreen().a("${pages.size} pages")
        ansi.reset()

        println(ansi)
    }

    fun getPage(index: Int): Page? {
        return pages.find { e -> e.index == index };
    }
}