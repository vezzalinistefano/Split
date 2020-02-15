package gui.rows;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che estende {@link Row} e implementa il pannello contenente la {@link JCheckBox} che
 * permette all'utente se utilizzare o no la modalità di divisione di default
 */
public class DefaultRow extends Row {

    /**
     * Permette all'utente di selezionare la modalità di default di divisione
     */
    private JCheckBox defaultChkBox;

    public JCheckBox getDefaultChkBox() {
        return defaultChkBox;
    }

    /**
     * Compone la DefaultRow
     */
    public DefaultRow() {
        super();

        this.defaultChkBox = new JCheckBox("Default");

        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.add(defaultChkBox);
        this.defaultChkBox.setSelected(true);
    }
}
