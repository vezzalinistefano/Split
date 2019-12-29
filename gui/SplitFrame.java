package gui;

import gui.panels.DivideSettingPanel;
import gui.panels.JobsPanel;
import gui.panels.MainPanel;
import gui.panels.MergeSettingPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private MainPanel mainPanel;
    private JobsPanel jobsPanel;
    private DivideSettingPanel divideSettingPanel;
    private MergeSettingPanel mergeSettingPanel;

    public static final String DIVIDE_PANEL = "Divide panel";
    public static final String MERGE_PANEL = "Merge panel";

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new MainPanel("Dividi");
        mainPanel.setLayout(cardLayout);

        Container cp = getContentPane();

        divideSettingPanel = new DivideSettingPanel();
        mergeSettingPanel = new MergeSettingPanel();
        jobsPanel = new JobsPanel(mainPanel, cardLayout);

        cp.add(jobsPanel, BorderLayout.PAGE_START);
        cp.add(Box.createHorizontalStrut(4));

        mainPanel.add(divideSettingPanel, DIVIDE_PANEL);
        mainPanel.add(mergeSettingPanel, MERGE_PANEL);
        cardLayout.show(mainPanel, DIVIDE_PANEL);
        cp.add(mainPanel);
        this.pack();
    }
}
