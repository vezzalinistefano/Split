package gui.rows;

import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * {@link Row} che permette all'utente di selezionare i file
 * su cui operare.
 */
public class ChooseFileRow extends Row {

    /**
     * Bottone che apre il {@link #fileChooser}
     */
    private JButton fileBtn;

    /**
     * Permette di selezionare i file su cui operare
     */
    private JFileChooser fileChooser;

    /**
     * Mostra quali file sono stati selezionati
     */
    private CustomJTextField txtFileSelected;

    /**
     * Costruisce una nuova ChooseFileRow.
     */
    public ChooseFileRow() {
        super();

        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File("."));

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

    /**
     * Mostra i file selezionati nella {@link #txtFileSelected}
     */
    private void showFilesSelected() {
        int i = 1;
        int size = fileChooser.getSelectedFiles().length;

        StringBuilder filesSelected = new StringBuilder();
        for (File f : fileChooser.getSelectedFiles()) {
            filesSelected.append(f.getName());
            if (i < size)
                filesSelected.append(", ");
            i++;
        }
        txtFileSelected.setText(filesSelected.toString());
    }

    /**
     * Pulisce la {@link #txtFileSelected}
     */
    public void cleanSelectedFiles() {
        fileChooser.setSelectedFiles(null);
        txtFileSelected.setText("");
    }

    /**
     * Ritorna i file selezionati
     * @return Un array contenente i file selezionati
     */
    public File[] getFilesSelected() {
        return fileChooser.getSelectedFiles();
    }

    /**
     * Aggiorna la {@link #txtFileSelected} e imposta {@link #fileChooser} su un file
     * a piacere
     * @param fileName
     */
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
