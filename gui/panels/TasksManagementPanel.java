package gui.panels;

import gui.rows.TasksManagementRow;
import logic.DivideTask;
import logic.MergeTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TasksManagementPanel extends JPanel {

    private TasksManagementRow tasksManagementRow;

    public TasksManagementPanel(ActionListener startListener) {
        super();

        tasksManagementRow = new TasksManagementRow(startListener);

        this.add(Box.createVerticalStrut(20));
        this.add(tasksManagementRow);
        this.add(Box.createVerticalStrut(20));
    }

    public void startTasks(ArrayList<Task> tasks) {
        Thread thread;
        for (Task t : tasks) {
            thread = new Thread(t);
            thread.start();
        }
    }
}
