package me.mafrans.loggbok;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.awt.event.*;

public class MVCViewForm {
    public JPanel mainPanel;
    public JButton saveButton;
    public JTable entryTable;
    public JPanel fileSelectPanel;
    public JLabel fileSelectLabel;
    private JButton newRowButton;

    public void addFileChooseListener(JComponent parent, FileChooseListener fileChooseListener) {
        System.out.println("Add Listener");

        fileSelectPanel.setFocusable(true);
        fileSelectPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileSelectPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Open Stuff");

                JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                chooser.addChoosableFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getName().endsWith(".log");
                    }

                    @Override
                    public String getDescription() {
                        return "Logbook .log file";
                    }
                });
                chooser.setDialogTitle("Select a log file");
                chooser.setDialogType(FileChooseType.OPEN.getId());
                FileChooseResponse returnType = FileChooseResponse.get(chooser.showOpenDialog(parent));

                switch(returnType) {
                    case APPROVE:
                        fileChooseListener.onFileChosen(chooser.getSelectedFile(), FileChooseResponse.APPROVE);
                    case CANCEL:
                        fileChooseListener.onFileChosen(null, FileChooseResponse.CANCEL);
                    case ERROR:
                        throw new UnsupportedOperationException("Something went wrong");
                }

                super.mouseClicked(e);
            }
        });
    }

    public void addFileSaveListener(JComponent parent, FileChooseListener fileChooseListener) {
        System.out.println("Add Listener");

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Save Stuff");

                JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
                chooser.setSelectedFile(new File(System.getProperty("user.home"), "New Logbook.log"));
                chooser.setDialogTitle("Select a log file");
                chooser.setDialogType(FileChooseType.SAVE.getId());
                FileChooseResponse returnType = FileChooseResponse.get(chooser.showSaveDialog(parent));

                switch(returnType) {
                    case APPROVE:
                        fileChooseListener.onFileChosen(chooser.getSelectedFile(), FileChooseResponse.APPROVE);
                    case CANCEL:
                        fileChooseListener.onFileChosen(null, FileChooseResponse.CANCEL);
                    case ERROR:
                        throw new UnsupportedOperationException("Something went wrong");
                }

                super.mouseClicked(e);
            }
        });
    }

    public void addNewRowListener(ActionListener listener) {
        newRowButton.addActionListener(listener);
    }

    private JFrame frame;
    public MVCViewForm() {
        frame = new JFrame("MVCViewForm");
    }

    public void pack() {
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void setVisible(boolean value) {
        frame.setVisible(value);
    }
}
