package me.mafrans.loggbok

class Volume(index: Int, book: Book) {
    var index: Int = index;
    var pages: Set<Page> = HashSet();
    val book: Book = book;
}