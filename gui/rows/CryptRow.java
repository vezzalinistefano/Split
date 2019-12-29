package gui.rows;

import gui.customcomponents.CustomJSeparator;
import gui.customcomponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il pannello riga che permette all'utente
 * di scegliere se crittografare o no i file e la relativa parola chiave
 */
public class CryptRow extends Row {

    private JCheckBox cryptCheckBox;
    private CustomJTextField txtKeyWord;

    public CryptRow() {
        super();

        cryptCheckBox = new JCheckBox("Crittografia");
        txtKeyWord = new CustomJTextField(128, 24);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(cryptCheckBox);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        CustomJSeparator separator2 = new CustomJSeparator();

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(new JLabel("Parola chiave  "));

        this.add(txtKeyWord);
    }
}
