package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddMergeTaskRow extends Row {

    private JButton addJobBtn;

    public AddMergeTaskRow(ActionListener addTaskClick) {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");

        this.add(addJobBtn);
    }
}
