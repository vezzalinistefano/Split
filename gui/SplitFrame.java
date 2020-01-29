package gui;

import gui.panels.DivideSettingPanel;
import gui.panels.TasksPanel;
import gui.panels.MainPanel;
import gui.panels.MergeSettingPanel;
import gui.queueTable.QueueTablePanel;
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

    private CardLayout cardLayout = new CardLayout();
    private JTabbedPane mainPanel;
    private TasksPanel tasksPanel;
    private DivideSettingPanel divideSettingPanel;
    private MergeSettingPanel mergeSettingPanel;
    private QueueTablePanel queueTablePanel;

    public static final String DIVIDE_PANEL = "Dividi";
    public static final String MERGE_PANEL = "Unisci";

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Container cp = getContentPane();
        mainPanel = new JTabbedPane();
        //mainPanel.setLayout(cardLayout);

        queueTablePanel = new QueueTablePanel(tasksQueue);
        ActionListener divideTaskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                divideSettingPanel.AddDivideTask(tasksQueue);
            }
        };
        divideSettingPanel = new DivideSettingPanel(divideTaskListener, queueTablePanel);

        mergeSettingPanel = new MergeSettingPanel();
        //tasksPanel = new TasksPanel(mainPanel, cardLayout);

        //this.add(tasksPanel, BorderLayout.PAGE_START);
        //this.add(Box.createHorizontalStrut(4));

        mainPanel.addTab(DIVIDE_PANEL, divideSettingPanel);
        mainPanel.addTab(MERGE_PANEL, mergeSettingPanel);
        //cardLayout.show(mainPanel, DIVIDE_PANEL);

        this.add(mainPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(queueTablePanel);
        this.add(scrollPane, BorderLayout.CENTER);

        this.pack();
    }
}
