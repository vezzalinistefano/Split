package gui.rows;

import gui.customComponents.CustomJSeparator;
import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * La {@link Row} che permette all'utente
 * di scegliere se crittografare o no i file e la relativa parola chiave
 */
public class CryptRow extends Row implements ItemListener {

    /**
     * Viene selezionata se si vuole criptare i file generati dalla divisione
     */
    private JCheckBox cryptCheckBox;

    /**
     * Contiene la password per decriptare i file
     */
    private CustomJTextField txtKeyWord;

    private JLabel lbl;

    /**
     * Ritorna un riferimento alla check box per la scelta della criptazione
     * @return Un riferimento a {@link #cryptCheckBox}
     */
    public JCheckBox getCryptCheckBox() {
        return cryptCheckBox;
    }

    /**
     * Costruisce una nuova CryptRow
     */
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

    /**
     * Permette la modifica di {@link #txtKeyWord} solo nel caso in cui {@link #cryptCheckBox}
     * sia selezionata
     * @param itemEvent Cattura il cambio di stato della {@link #cryptCheckBox}
     */
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(cryptCheckBox.isSelected()) {
            txtKeyWord.setEditable(true);
        } else {
            txtKeyWord.setEditable(false);
        }
    }

    /**
     * Ritorna lo stato della {@link #cryptCheckBox}
     * @return Vero o falso in base allo stato della {@link #cryptCheckBox}
     */
    public boolean getCryptSelection() {
        return cryptCheckBox.isSelected();
    }

    /**
     * Ritorna la password scelta per criptare i file
     * @return La password scelta per criptare i file
     */
    public String getKeyword() {
        return txtKeyWord.getText();
    }

    /**
     * Rende ogni componente della CryptRow modificabile/non modificabile
     * @param status True se i componenti della CryptRow vogliono essere resi modificabili,
     *               False altrimenti
     */
    public void setStatus(boolean status) {
        this.cryptCheckBox.setEnabled(status);
        this.lbl.setEnabled(status);
        this.txtKeyWord.setEnabled(status);
    }
}
