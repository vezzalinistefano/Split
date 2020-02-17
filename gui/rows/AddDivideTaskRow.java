package gui.rows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * {@link Row} che mostra il bottone per aggiungere un {@link logic.DivideTask} in coda
 */
public class AddDivideTaskRow extends Row {

    /**
     * Bottone che avvia {@link gui.panels.DivideSettingPanel#AddDivideTask(ArrayList)}
     */
    private JButton addJobBtn;

    /**
     * Compone l'AddDivideTaskRow
     * @param onClick {@link ActionListener} che cattura il click sul bottone
     */
    public AddDivideTaskRow(ActionListener onClick) {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");
        addJobBtn.addActionListener(onClick);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(addJobBtn);
    }
}
