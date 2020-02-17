package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * La {@link Row} posizionata in fondo alla pagina che permette di far partire i {@link logic.Task} in coda
 * oppure eliminarli/modificarli
 */
public class TasksManagementRow extends Row {

    /**
     * Permette di eseguire i task in coda
     */
    private JButton startBtn;

    /**
     * Permette di modificare un task in coda
     */
    private JButton modifyBtn;

    /**
     * Permette di eliminare un task in coda
     */
    private JButton deleteBtn;

    /**
     * Riferimento al bottone che fa eseguire i task in coda
     *
     * @return Riferimento a {@link #startBtn}
     */
    public JButton getStartBtn() {
        return startBtn;
    }

    /**
     * Riferimento al bottone che fa modificare un task in coda
     *
     * @return Riferimento a {@link #modifyBtn}
     */
    public JButton getModifyBtn() {
        return modifyBtn;
    }

    /**
     * Riferimento al bottone che fa eliminare un task in coda
     *
     * @return Riferimento a {@link #deleteBtn}
     */
    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    /**
     * Costruisce una nuova TaskManagementRow
     *
     * @param onClick Cattura il click su uno dei tre bottoni
     */
    public TasksManagementRow(ActionListener onClick) {
        super();

        startBtn = new JButton("Start");
        startBtn.addActionListener(onClick);

        modifyBtn = new JButton("Modifica");
        modifyBtn.addActionListener(onClick);

        deleteBtn = new JButton("Elimina");
        deleteBtn.addActionListener(onClick);

        this.add(startBtn);
        this.add(Box.createHorizontalStrut(15));
        this.add(modifyBtn);
        this.add(Box.createHorizontalStrut(15));
        this.add(deleteBtn);
    }
}
