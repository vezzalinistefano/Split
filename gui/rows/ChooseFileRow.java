package gui.rows;

import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Stefano\\Music\\MusicBee\\Music"));

        fileBtn = new JButton("Scegli file");
        fileBtn.addActionListener(e -> {
            fileChooser.showOpenDialog(this);
            this.showFilesSelected();
        });


        txtFileSelected = new CustomJTextField(200,24);
        txtFileSelected.setEditable(false);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(fileBtn);
        this.add(Box.createRigidArea(new Dimension(5,0)));
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
        fileChooser.setSelectedFile(null);
        txtFileSelected.setText("");
    }

    public File[] getFilesSelected() {
        return fileChooser.getSelectedFiles();
    }
}
