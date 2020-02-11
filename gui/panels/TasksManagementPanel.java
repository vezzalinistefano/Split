package gui.panels;

import gui.SplitFrame;
import gui.queueTable.QueueTablePanel;
import gui.rows.TasksManagementRow;
import logic.DivideTask;
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

    public String getStartName() {
        return tasksManagementRow.getStartBtn().getText();
    }

    public String getModifyName() {
        return tasksManagementRow.getModifyBtn().getText();
    }

    public String getDeleteName() {
        return tasksManagementRow.getDeleteBtn().getText();
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

    public void modifyElement(ArrayList<Task> tasks, int index, DivideSettingPanel divideSettingPanel) {
        Task t = tasks.get(index);
        if(t instanceof DivideTask) {
            this.deleteElement(tasks, index);
            String fileName = ((DivideTask) t).getFile().getAbsolutePath();
            divideSettingPanel.setFileTextBox(fileName);
        }
    }

    public void deleteElement(ArrayList<Task> tasks, int index) {
        tasks.remove(index);
    }
}
