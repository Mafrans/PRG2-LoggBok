package me.mafrans.loggbok

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.event.TableModelListener
import javax.swing.table.AbstractTableModel
import javax.swing.table.TableColumn
import javax.swing.table.TableModel

class MVCController(var model: MVCModel, val view: MVCViewForm) {
    fun start() {
        view.pack();
        view.setVisible(true);
        view.addFileChooseListener(view.mainPanel, object : FileChooseListener {
            override fun onFileChosen(file: File?, response: FileChooseResponse) {
                if(file == null) {
                    error("That file cannot be loaded")
                    return
                }
                model = MVCModel.loadFromFile(file)
                view.fileSelectLabel.text = file.absolutePath;

                updateTable()
            }
        });

        view.addFileSaveListener(view.mainPanel, object : FileChooseListener {
            override fun onFileChosen(file: File?, response: FileChooseResponse) {
                if(response == FileChooseResponse.CANCEL) {
                    return
                }
                if(file == null || response == FileChooseResponse.ERROR) {
                    error("Something went wrong when saving")
                }
                model.saveToFile(file)
                view.fileSelectLabel.text = file.absolutePath;

                updateTable()
            }
        });

        view.addNewRowListener {
            model.entries += LogEntry(System.getProperty("user.name"), "")
            updateTable()
        }
    }

    fun updateTable() {
        val dataModel = object: AbstractTableModel() {
            override fun getRowCount(): Int {
                return model.entries.size
            }

            override fun getColumnCount(): Int {
                return 3
            }

            override fun getColumnName(column: Int): String {
                if (rowCount > 0) {
                    return model.entries[0].getColumn(column).name;
                }
                return "";
            }

            override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
                return model.entries[rowIndex].getColumn(columnIndex).value;
            }

            override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
                if (aValue != null) {
                    model.entries[rowIndex].setColumn(columnIndex, aValue);
                }
                super.setValueAt(aValue, rowIndex, columnIndex)
            }

            override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
                return model.entries[rowIndex].getColumn(columnIndex).editable;
            }
        };

        view.entryTable.model = dataModel;
    }
}