package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il pannello riga contenente il bottone che aggiunge
 * il Job alla coda
 */
public class AddDivideTaskRow extends Row {

    private JButton addJobBtn;

    public AddDivideTaskRow(ActionListener onClick) {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");
        addJobBtn.addActionListener(onClick);

        this.add(addJobBtn);
    }
}
