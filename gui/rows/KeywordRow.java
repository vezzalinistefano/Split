package gui.rows;

import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * La {@link Row} che permette di inserire la password per decriptare i file da
 * ricomporre
 */
public class KeywordRow extends Row {

    /**
     * Dedicata all'inserimento della password
     */
    private CustomJTextField txtKeyWord;

    /**
     * Costruisce una nuova KeywordRow
     */
    public KeywordRow() {
        super();

        txtKeyWord = new CustomJTextField(128, 24);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(new JLabel("Parola chiave  "));

        this.add(txtKeyWord);
    }

    /**
     * Ritorna la password scelta per decriptare i file
     * @return La password scelta per decriptare i file
     */
    public String getKeyword() {
        return txtKeyWord.getText();
    }
}
