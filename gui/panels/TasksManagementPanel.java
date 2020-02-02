package gui.panels;

import gui.rows.TasksManagementRow;
import logic.DivideTask;
import logic.MergeTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
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
        for (Task t : tasks) {
            if (t instanceof DivideTask) {
                try {
                    ((DivideTask) t).performTask();
                } catch (IOException e) {
                    //TODO: gestione eccezione file split
                    System.out.println(e.getMessage());
                }
            } else if (t instanceof MergeTask) {
                    ((MergeTask)t).performTask();
            }
        }
    }
}
