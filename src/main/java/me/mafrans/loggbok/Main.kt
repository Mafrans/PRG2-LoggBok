package me.mafrans.loggbok

import java.io.File

fun main() {
    val model = Model()
    model.entries += LogEntry(Author("Malte"), "Hurra, ett meddelande!")
    model.entries += LogEntry(Author("Marcus"), "OwO")
    model.entries += LogEntry(Author("Malte"), "Hurra, ett meddelande till!")

    val file = File("./test.mod")
    model.saveToFile(file)

    val model2 = Model.loadFromFile(file)
    println(model2.entries)
}
