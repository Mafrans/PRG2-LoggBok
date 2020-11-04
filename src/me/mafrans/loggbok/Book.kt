package me.mafrans.loggbok

import org.fusesource.jansi.Ansi

class Book(var author: Author, var title: String) {
    var chapters: Set<Chapter> = HashSet();

    fun print() {
        val ansi = Ansi.ansi();
        ansi.fgYellow().a("    _______    ").newline();
        ansi.fgYellow().a("   /      /,   ").newline();
        ansi.fgYellow().a("  /      //    ").fgBrightGreen().a("\"$title\"").newline();
        ansi.fgYellow().a(" /______//     ").fgGreen().a("By ${author.name} (${chapters.size} chapters)").newline();
        ansi.fgYellow().a("(______(/      ")
        ansi.reset()

        println(ansi)
    }

    fun getChapter(index: Int): Chapter? {
        return chapters.find { e -> e.index == index };
    }
}