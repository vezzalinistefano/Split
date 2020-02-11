package gui.rows;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TasksManagementRow extends Row {

    private JButton startBtn;
    private JButton modifyBtn;
    private JButton deleteBtn;

    public JButton getStartBtn() {
        return startBtn;
    }

    public JButton getModifyBtn() {
        return modifyBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

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
