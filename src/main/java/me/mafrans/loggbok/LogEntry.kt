package me.mafrans.loggbok

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
class LogEntry(val author: Author, var text: String) {
    val date = Date().time;

    override fun toString(): String {
        return "[${SimpleDateFormat("dd MMM yyyy, HH:mm").format(date)}] (${author.name}) : $text";
    }
}