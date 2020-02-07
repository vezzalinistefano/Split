package gui.rows;

import gui.customComponents.CustomJSeparator;
import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classe che implementa il pannello riga che permette all'utente
 * di scegliere se crittografare o no i file e la relativa parola chiave
 */
public class CryptRow extends Row implements ItemListener {

    private JCheckBox cryptCheckBox;
    private CustomJTextField txtKeyWord;
    private JLabel lbl;

    public JCheckBox getCryptCheckBox() {
        return cryptCheckBox;
    }

    public CryptRow() {
        super();

        cryptCheckBox = new JCheckBox("Crittografia");
        cryptCheckBox.addItemListener(this);
        txtKeyWord = new CustomJTextField(128, 24);
        txtKeyWord.setEditable(false);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(cryptCheckBox);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        CustomJSeparator separator2 = new CustomJSeparator();
        this.add(separator2);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.lbl = new JLabel("Password");
        this.add(lbl);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(txtKeyWord);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(cryptCheckBox.isSelected()) {
            txtKeyWord.setEditable(true);
        } else {
            txtKeyWord.setEditable(false);
        }
    }

    public boolean getCryptSelection() {
        return cryptCheckBox.isSelected();
    }

    public String getKeyword() {
        return txtKeyWord.getText();
    }

    public void setStatus(boolean status) {
        this.cryptCheckBox.setEnabled(status);
        this.lbl.setEnabled(status);
        this.txtKeyWord.setEnabled(status);
    }
}
