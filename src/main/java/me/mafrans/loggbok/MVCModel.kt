package me.mafrans.loggbok

import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File

class MVCModel() {
    var entries: List<LogEntry> = ArrayList()

    constructor(entries: ArrayList<LogEntry>): this() {
        this.entries = entries
    }

    companion object {
        fun fromHexString(hex: String): MVCModel {
            return MVCModel(ProtoBuf.decodeFromHexString(hex))
        }

        fun loadFromFile(file: File): MVCModel {
            if(!file.exists()) {
                throw NoSuchFileException(file);
            }
            return fromHexString(Util.toHexString(file.readBytes())!!);
        }
    }

    fun toHexString(): String {
        return ProtoBuf.encodeToHexString(entries)
    }

    fun saveToFile(file: File) {
        if (!file.exists() && file.parentFile != null) {
            file.parentFile.mkdirs()
        }
        else {
            file.delete()
        }
        file.createNewFile()
        file.writeBytes(Util.toByteArray(this.toHexString())!!)
    }
}