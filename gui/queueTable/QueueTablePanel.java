package gui.queueTable;

import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueueTablePanel extends JPanel {
    private QueueTableModel queueTableModel;
    public QueueTablePanel(ArrayList<Task> tasksQueue) {

        queueTableModel = new QueueTableModel(tasksQueue);
        JTable queueTable = new JTable(queueTableModel);

        JScrollPane scrollPane = new JScrollPane(queueTable);

        this.add(scrollPane);
    }

    public void updateTableModel(ArrayList<Task> tasksQueue) {
        queueTableModel.update(tasksQueue);
    }

}
