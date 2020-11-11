package me.mafrans.loggbok

import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.File

class Model() {
    var entries: List<LogEntry> = ArrayList()

    constructor(entries: ArrayList<LogEntry>): this() {
        this.entries = entries
    }

    companion object {
        fun fromHexString(hex: String): Model {
            return Model(ProtoBuf.decodeFromHexString(hex))
        }

        fun loadFromFile(file: File): Model {
            if(!file.exists()) {
                throw NoSuchFileException(file);
            }
            return Model.fromHexString(Util.toHexString(file.readBytes())!!);
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