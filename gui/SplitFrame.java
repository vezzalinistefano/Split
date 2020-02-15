package gui;

import gui.panels.*;
import gui.panels.QueueTablePanel;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {

    private ArrayList<Task> tasksQueue = new ArrayList<>();

    private JTabbedPane mainPanel;
    private DivideSettingPanel divideSettingPanel;
    private MergeSettingPanel mergeSettingPanel;
    private QueueTablePanel queueTablePanel;
    private TasksManagementPanel tasksManagementPanel;

    public static final String DIVIDE_PANEL = "Dividi";
    public static final String MERGE_PANEL = "Unisci";
    public static boolean performed = false;

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JTabbedPane();

        ActionListener startTasksListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String whichClicked = actionEvent.getActionCommand();
                if(whichClicked.equals(tasksManagementPanel.getStartName()))
                    tasksManagementPanel.startTasks(tasksQueue);
                else if(whichClicked.equals(tasksManagementPanel.getModifyName())) {
                    tasksManagementPanel.modifyElement(tasksQueue, queueTablePanel.getSelectedRow(),
                            divideSettingPanel);
                    queueTablePanel.updateTableModel();
                }
                else if(whichClicked.equals(tasksManagementPanel.getDeleteName())) {
                    tasksManagementPanel.deleteElement(tasksQueue, queueTablePanel.getSelectedRow());
                    queueTablePanel.updateTableModel();
                }
            }
        };
        tasksManagementPanel = new TasksManagementPanel(startTasksListener);

        queueTablePanel = new QueueTablePanel(tasksQueue);

        ActionListener divideTaskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                divideSettingPanel.AddDivideTask(tasksQueue);
            }
        };
        divideSettingPanel = new DivideSettingPanel(divideTaskListener, queueTablePanel);

        ActionListener mergeTaskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mergeSettingPanel.AddMergeTask(tasksQueue);
            }
        };
        mergeSettingPanel = new MergeSettingPanel(mergeTaskListener, queueTablePanel);


        mainPanel.addTab(DIVIDE_PANEL, divideSettingPanel);
        mainPanel.addTab(MERGE_PANEL, mergeSettingPanel);


        this.add(mainPanel, BorderLayout.NORTH);

        this.add(queueTablePanel, BorderLayout.CENTER);

        this.add(tasksManagementPanel, BorderLayout.SOUTH);

        this.pack();
    }
}
