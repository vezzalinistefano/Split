package gui.rows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * La {@link Row} che permette di aggiungere un nuovo {@link logic.MergeTask} in coda
 */
public class AddMergeTaskRow extends Row {

    /**
     * Bottone che avvia {@link gui.panels.MergeSettingPanel#AddMergeTask(ArrayList)}
     */
    private JButton addTaskBtn;

    /**
     * Costruisce l'AddMergeTaskRow
     * @param onClick {@link ActionListener} che cattura il click sul bottone
     */
    public AddMergeTaskRow(ActionListener onClick) {
        super();

        addTaskBtn = new JButton("Aggiungi alla coda");
        addTaskBtn.addActionListener(onClick);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(addTaskBtn);
    }
}
