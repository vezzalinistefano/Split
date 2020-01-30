package gui;

import gui.panels.*;
import gui.queueTable.QueueTablePanel;
import gui.rows.TasksManagementRow;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {

    private ArrayList<Task> tasksQueue = new ArrayList<Task>();

    private JTabbedPane mainPanel;
    private DivideSettingPanel divideSettingPanel;
    private MergeSettingPanel mergeSettingPanel;
    private QueueTablePanel queueTablePanel;
    private TasksManagementPanel tasksManagementPanel;

    public static final String DIVIDE_PANEL = "Dividi";
    public static final String MERGE_PANEL = "Unisci";

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JTabbedPane();

        queueTablePanel = new QueueTablePanel(tasksQueue);
        ActionListener divideTaskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                divideSettingPanel.AddDivideTask(tasksQueue);
            }
        };
        divideSettingPanel = new DivideSettingPanel(divideTaskListener, queueTablePanel);

        mergeSettingPanel = new MergeSettingPanel();


        mainPanel.addTab(DIVIDE_PANEL, divideSettingPanel);
        mainPanel.addTab(MERGE_PANEL, mergeSettingPanel);

        this.add(mainPanel, BorderLayout.NORTH);

        this.add(queueTablePanel, BorderLayout.CENTER);

        ActionListener startTasksListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                tasksManagementPanel.startTasks(tasksQueue);
            }
        };
        tasksManagementPanel = new TasksManagementPanel(startTasksListener);
        this.add(tasksManagementPanel, BorderLayout.SOUTH);

        this.pack();
    }
}
