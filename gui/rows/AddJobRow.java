package gui.rows;

import javax.swing.*;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddJobRow extends Row{

    private JButton addJobBtn;

    public AddJobRow() {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");

        this.add(addJobBtn);
    }
}
