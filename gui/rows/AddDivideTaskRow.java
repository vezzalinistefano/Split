package gui.rows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * {@link Row} che mostra il bottone per aggiungere un {@link logic.DivideTask} in coda
 */
public class AddDivideTaskRow extends Row {

    /**
     * {@link JButton} che cattura il click sul bottone per aggiungere un {@link logic.DivideTask} in coda
     */
    private JButton addJobBtn;

    /**
     * Compone l'AddDivideTaskRow
     * @param onClick
     */
    public AddDivideTaskRow(ActionListener onClick) {
        super();

        addJobBtn = new JButton("Aggiungi alla coda");
        addJobBtn.addActionListener(onClick);

        this.add(Box.createRigidArea(new Dimension(5,0)));
        this.add(addJobBtn);
    }
}
