package me.mafrans.loggbok

import java.io.File

fun main() {
    val model = MVCModel();
    model.entries += LogEntry("Malte", "Test 1")
    model.entries += LogEntry("Malte", "Test 2")
    model.entries += LogEntry("Malte", "Test 3")
    model.entries += LogEntry("Malte", "Test 4")
    model.entries += LogEntry("Malte", "Test 5")
    model.entries += LogEntry("Malte", "Test 6")

    model.saveToFile(File("./test.log"))

    val controller = MVCController(MVCModel(), MVCViewForm());
    controller.start();
}
