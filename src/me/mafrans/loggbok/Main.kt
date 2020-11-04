package me.mafrans.loggbok

fun main() {
    val book = Book(Author("Malte Klüft"), "Test Book")
    val chapter = Chapter(1, "My Favorite Chapter", book)
    val page = Page(1, chapter)

    page.text = "My test text"
    chapter.pages += page
    book.chapters += chapter

    println()
    book.print()
    println()

    println("Which chapter do you wish to access?")

    var chapterIndex: Int? = readLine()?.toInt()
    val chp: Chapter? = book.getChapter(chapterIndex!!);
    if(chp == null) {
        println("No such chapter exists")
        return
    }

    println()
    chp.print()
    println()

    println("Which page do you wish to read?")

    var pageIndex: Int? = readLine()?.toInt()
    val pge: Page? = chp.getPage(pageIndex!!);
    if(pge == null) {
        println("No such chapter exists")
        return
    }

    println()
    println(pge.text)
    println()
}
