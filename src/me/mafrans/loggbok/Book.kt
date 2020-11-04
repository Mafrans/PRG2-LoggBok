package me.mafrans.loggbok

class Book {
    var author: Author;
    var title: String;
    var volumes: Set<Volume> = HashSet();

    constructor(author: Author, title: String) {
        this.author = author;
        this.title = title;
    }
}