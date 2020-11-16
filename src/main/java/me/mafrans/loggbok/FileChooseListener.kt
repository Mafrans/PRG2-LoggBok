package me.mafrans.loggbok
import java.io.File
import javax.swing.JFileChooser

interface FileChooseListener {
    fun onFileChosen(file: File?, response: FileChooseResponse)
}

enum class FileChooseType(val id: Int) {
    SAVE(JFileChooser.SAVE_DIALOG),
    OPEN(JFileChooser.OPEN_DIALOG)
}

enum class FileChooseResponse(val id: Int) {
    APPROVE(JFileChooser.APPROVE_OPTION),
    CANCEL(JFileChooser.CANCEL_OPTION),
    ERROR(JFileChooser.ERROR_OPTION),

    ;

    companion object {
        @JvmStatic
        fun get(id: Int): FileChooseResponse? {
            return values().find { t -> t.id == id };
        }
    }
}