package me.mafrans.loggbok

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
class FormattedDate(val date: Long) {
    override fun toString(): String {
        return SimpleDateFormat("dd MMM yyyy, HH:mm").format(Date(date));
    }
}