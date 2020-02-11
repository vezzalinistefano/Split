package gui.rows;

import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe che implementa il pannello riga che permette all'utente di selezionare i file
 * su cui operare
 */
public class ChooseFileRow extends Row {
    private JButton fileBtn;
    private JFileChooser fileChooser;
    private CustomJTextField txtFileSelected;

    public ChooseFileRow() {
        super();

        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Stefano\\Documents\\TestAPP"));

        fileBtn = new JButton("Scegli file");
        fileBtn.addActionListener(e -> {
            fileChooser.showOpenDialog(this);
            this.showFilesSelected();
        });


        txtFileSelected = new CustomJTextField(200, 24);
        txtFileSelected.setEditable(false);

        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(fileBtn);
        this.add(Box.createRigidArea(new Dimension(5, 0)));
        this.add(txtFileSelected);
    }

    private void showFilesSelected() {
        StringBuilder filesSelected = new StringBuilder();
        for (File f : fileChooser.getSelectedFiles()) {
            filesSelected.append(f.getName());
        }
        txtFileSelected.setText(filesSelected.toString());
    }

    public void cleanSelectedFiles() {
        fileChooser.setSelectedFiles(null);
        txtFileSelected.setText("");
    }

    public File[] getFilesSelected() {
        return fileChooser.getSelectedFiles();
    }

    public void updateFileSelected(String fileName) {
        File f = new File(fileName);
        File owner = f.getParentFile();
        if (owner.exists()) {
            this.fileChooser.setCurrentDirectory(owner);
        }
        if (f.exists()) {
            File[] files = new File[1];
            files[0] = f;
            this.fileChooser.setSelectedFiles(files);
        }

        this.txtFileSelected.setText(f.getName());
    }
}
