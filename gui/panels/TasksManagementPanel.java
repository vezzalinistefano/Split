package gui.panels;

import gui.SplitFrame;
import gui.rows.TasksManagementRow;
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
    }

    public void startTasks(ArrayList<Task> tasks) {
        SplitFrame.performed = true;
        ArrayList<Thread> thread = new ArrayList<>();

        // Inizializzo ogni thread
        for (Task t : tasks) {
            thread.add(new Thread(t));
        }

        // Faccio partire ogni thread
        for (Thread t:thread) {
            t.start();
        }
    }
}
