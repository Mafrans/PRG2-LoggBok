package me.mafrans.loggbok

class Book {
    lateinit var author: Author;
    lateinit var title: String;
    var volumes: Set<Volume> = HashSet();

    constructor(author: Author, title: String) {
        this.author = author;
        this.title = title;
    }
}