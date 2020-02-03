package gui.rows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddMergeTaskRow extends Row {

    private JButton addTaskBtn;

    //TODO: action listener sul bottone
    public AddMergeTaskRow(ActionListener addTaskClick) {
        super();

        addTaskBtn = new JButton("Aggiungi alla coda");
        addTaskBtn.addActionListener(addTaskClick);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(addTaskBtn);
    }
}
