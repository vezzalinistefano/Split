package gui.rows;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il pannello riga che permette all'utente di decidere
 * se comprimere o no i file generati dalla divisione
 */
public class CompressRow extends Row {

    private JCheckBox compressCheckBox;

    public CompressRow() {
        super();

        compressCheckBox = new JCheckBox("Compressione");

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(compressCheckBox);
    }
}
