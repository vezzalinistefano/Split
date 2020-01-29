package gui;

import gui.panels.DivideSettingPanel;
import gui.panels.TasksPanel;
import gui.panels.MainPanel;
import gui.panels.MergeSettingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {

    private Vector tasksQueue;

    private CardLayout cardLayout = new CardLayout();
    private MainPanel mainPanel;
    private TasksPanel jobsPanel;
    private DivideSettingPanel divideSettingPanel;
    private MergeSettingPanel mergeSettingPanel;

    public static final String DIVIDE_PANEL = "Dividi";
    public static final String MERGE_PANEL = "Unisci";

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new MainPanel(DIVIDE_PANEL);
        mainPanel.setLayout(cardLayout);

        Container cp = getContentPane();

        ActionListener divideTaskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                divideSettingPanel.AddDivideTask(tasksQueue);
            }
        };
        divideSettingPanel = new DivideSettingPanel(divideTaskListener);

        mergeSettingPanel = new MergeSettingPanel();
        jobsPanel = new TasksPanel(mainPanel, cardLayout);

        cp.add(jobsPanel, BorderLayout.PAGE_START);
        cp.add(Box.createHorizontalStrut(4));

        mainPanel.add(divideSettingPanel, DIVIDE_PANEL);
        mainPanel.add(mergeSettingPanel, MERGE_PANEL);
        cardLayout.show(mainPanel, DIVIDE_PANEL);
        cp.add(mainPanel);
        this.pack();
    }
}
