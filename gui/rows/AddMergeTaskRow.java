package gui.rows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddMergeTaskRow extends Row {

    private JButton addJobBtn;

    //TODO: action listener sul bottone
    public AddMergeTaskRow(ActionListener addTaskClick) {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(addJobBtn);
    }
}
