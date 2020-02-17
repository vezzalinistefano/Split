package gui.panels;

import gui.queueTable.QueueTableModel;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Il {@link JPanel} che contiene la tabella per mostrare i {@link Task} in coda
 */
public class QueueTablePanel extends JPanel {

    /**
     * Mostra i {@link Task} in coda
     */
    private JTable queueTable;

    /**
     * Definisce la vista e il comportamento di {@link #queueTable}
     */
    private QueueTableModel queueTableModel;

    /**
     * Costruisce il QueueTablePanel
     * @param tasksQueue La coda dei task
     */
    public QueueTablePanel(ArrayList<Task> tasksQueue) {

        this.setLayout(new GridLayout());
        queueTableModel = new QueueTableModel(tasksQueue);
        queueTable = new JTable(queueTableModel);

        JScrollPane scrollPane = new JScrollPane(queueTable);

        this.add(scrollPane);
    }

    /**
     * Aggiorna la vista della tabella
     */
    public void updateTableModel() {
        queueTableModel.update();
    }

    /**
     * Ritorna l'indice della riga selezionata
     * @return L'indice della riga selezionata
     */
    public int getSelectedRow() {
        return queueTable.getSelectedRow();
    }

}
