package gui.rows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddTaskRow extends Row {

    private JButton addJobBtn;

    public AddTaskRow() {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");

        this.add(addJobBtn);
    }
}
