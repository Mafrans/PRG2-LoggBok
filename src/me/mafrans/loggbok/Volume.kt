package me.mafrans.loggbok

class Volume(index: UShort, book: Book) {
    var index: UShort = index;
    var pages: Set<Page> = HashSet();
    val book: Book = book;
}