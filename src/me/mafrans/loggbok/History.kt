package me.mafrans.loggbok

import java.util.*
import kotlin.collections.ArrayList

class History {
    companion object {
        fun makeID(): String {
            return String.format("#%06x", Random().nextInt(0xffffff + 1))
        }
    }

    val entries: List<HistoryEntry> = ArrayList();

    fun getBetween(fromIndex: Int, toIndex: Int): List<HistoryEntry> {
        return entries.subList(fromIndex, toIndex);
    }

    fun getBetween(fromDate: Date, toDate: Date): List<HistoryEntry> {
        return entries.filter { entry -> entry.date.after(fromDate) && entry.date.before(toDate) }
    }

    fun getFrom(author: Author): List<HistoryEntry> {
        return entries.filter { entry -> entry.author == author }
    }

    inline fun <reified T : HistoryEntry> getTypeOf(): List<HistoryEntry> {
        return entries.filterIsInstance<T>()
    }
}

abstract class HistoryEntry(val author: Author) {
    val date: Date = Date()
    abstract fun getMessage(): String
}

class HistoryPageCreatedEntry(author: Author, page: Page) : HistoryEntry(author) {
    val page: Page = page
    val id: String = History.makeID()

    override fun getMessage(): String {
        if(page.chapter != null) {
            return "$author created page ${page.index} in ${page.chapter.book.title}, Volume ${page.chapter.index}"
        }

        return "$author created page ${page.index}"
    }
}

class HistoryPageEditedEntry(author: Author, page: Page, changeState: TextChangeState) : HistoryEntry(author) {
    val id: String = History.makeID()
    val page: Page = page

    override fun getMessage(): String {
        if(page.chapter != null) {
            return "$author edited page ${page.index} in ${page.chapter.book.title}, Volume ${page.chapter.index}"
        }

        return "$author created page ${page.index}"
    }
}