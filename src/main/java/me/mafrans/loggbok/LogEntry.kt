package me.mafrans.loggbok

import kotlinx.serialization.Serializable
import java.lang.reflect.Field
import java.text.SimpleDateFormat
import java.util.*

@Serializable
class LogEntry(val author: String, var text: String) {
    val date = FormattedDate(Date().time);

    fun getColumns(): Array<LogEntryColumn> {
        return arrayOf(
                LogEntryColumn("Date", this, javaClass.getDeclaredField("date"), false),
                LogEntryColumn("Author", this, javaClass.getDeclaredField("author"), true),
                LogEntryColumn("Text", this, javaClass.getDeclaredField("text"), true),
        )
    }

    override fun toString(): String {
        return "[${SimpleDateFormat("dd MMM yyyy, HH:mm").format(date)}] (${author}) : $text";
    }

    fun getColumn(column: Int): LogEntryColumn {
        return getColumns()[column];
    }

    fun setColumn(column: Int, value: Any) {
        getColumns()[column].field.set(this, value);
    }
}

class LogEntryColumn(val name: String, owner: Any, val field: Field, val editable: Boolean) {
    val value: Any;

    init {
        field.isAccessible = true;
        this.value = field[owner]
    }
}