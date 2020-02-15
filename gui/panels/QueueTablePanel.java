package gui.panels;

import gui.queueTable.QueueTableModel;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueueTablePanel extends JPanel {
    private JTable queueTable;
    private QueueTableModel queueTableModel;
    public QueueTablePanel(ArrayList<Task> tasksQueue) {

        this.setLayout(new GridLayout());
        queueTableModel = new QueueTableModel(tasksQueue);
        queueTable = new JTable(queueTableModel);

        JScrollPane scrollPane = new JScrollPane(queueTable);

        this.add(scrollPane);
    }

    public void updateTableModel() {
        queueTableModel.update();
    }

    public int getSelectedRow() {
        return queueTable.getSelectedRow();
    }

}
