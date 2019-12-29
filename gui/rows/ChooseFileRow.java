package gui.rows;

import gui.customcomponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il pannello riga che permette all'utente di selezionare i file
 * su cui operare
 */
public class ChooseFileRow extends Row {
    private JButton fileBtn;
    private CustomJTextField txtFileSelected;

    public ChooseFileRow() {
        super();

        fileBtn = new JButton("Scegli file");

        txtFileSelected = new CustomJTextField(200,24);
        txtFileSelected.setEditable(false);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(fileBtn);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(txtFileSelected);

    }
}
