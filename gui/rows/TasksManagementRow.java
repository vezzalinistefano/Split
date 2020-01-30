package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TasksManagementRow extends Row {

    private JButton startBtn;

    public TasksManagementRow(ActionListener onClick) {
        super();

        startBtn = new JButton("Start");
        startBtn.addActionListener(onClick);
        this.add(startBtn);
    }
}
