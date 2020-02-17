package gui.rows;

import javax.swing.*;
import java.awt.*;


/**
 * La {@link Row} che permette all'utente di decidere
 * se comprimere o no i file generati dalla divisione
 */
public class CompressRow extends Row {

    /**
     * Viene selezionata se si vuole comprimere i file generati dalla divisione
     */
    private JCheckBox compressCheckBox;

    /**
     * Ritorna un riferimento alla check box per la scelta della compressione
     * @return Un riferimento a {@link #compressCheckBox}
     */
    public JCheckBox getCompressCheckBox() {
        return compressCheckBox;
    }

    /**
     * Costruisce una nuova CompressRow
     */
    public CompressRow() {
        super();

        compressCheckBox = new JCheckBox("Compressione");

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(compressCheckBox);
    }

    /**
     * Ritorna lo stato della {@link #compressCheckBox}
     * @return Vero o falso in base allo stato della {@link #compressCheckBox}
     */
    public Boolean getCompressSelection() {
        return compressCheckBox.isSelected();
    }

}
