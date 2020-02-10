package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TasksManagementRow extends Row {

    private JButton startBtn;
    private JButton modifyBtn;
    private JButton deleteBtn;

    public TasksManagementRow(ActionListener onClick) {
        super();

        startBtn = new JButton("Start");
        startBtn.addActionListener(onClick);

        modifyBtn = new JButton("Modifica");

        deleteBtn = new JButton("Elimina");

        this.add(startBtn);
        this.add(Box.createHorizontalStrut(15));
        this.add(modifyBtn);
        this.add(Box.createHorizontalStrut(15));
        this.add(deleteBtn);
    }
}
