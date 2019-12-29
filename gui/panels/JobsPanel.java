package gui.panels;

import gui.SplitFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che implementa il JPanel della toolbar che permette
 * di scegliere quali operazioni eseguire
 */
public class JobsPanel extends JPanel implements ActionListener {

    private MainPanel currentJob;
    private JToolBar jobsBar;
    private JButton divideBtn, mergeBtn;
    private CardLayout cardLayout;

    public JobsPanel(MainPanel currentJob, CardLayout cardLayout) {
        super();
        this.setLayout(new BorderLayout());
        this.currentJob = currentJob;
        this.cardLayout = cardLayout;

        jobsBar = new JToolBar();
        jobsBar.setFloatable(false);

        divideBtn = new JButton(SplitFrame.DIVIDE_PANEL);
        mergeBtn = new JButton(SplitFrame.MERGE_PANEL);

        divideBtn.addActionListener(this);
        mergeBtn.addActionListener(this);

        jobsBar.add(divideBtn);
        jobsBar.add(mergeBtn);

        this.add(jobsBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SplitFrame.DIVIDE_PANEL)
                && currentJob.getCurrentJob().equals(SplitFrame.MERGE_PANEL)) {
            cardLayout.show(currentJob, SplitFrame.DIVIDE_PANEL);
            currentJob.setCurrentJob(SplitFrame.DIVIDE_PANEL);
        } else if (e.getActionCommand().equals(SplitFrame.MERGE_PANEL)
                && currentJob.getCurrentJob().equals(SplitFrame.DIVIDE_PANEL)) {
            cardLayout.show(currentJob, SplitFrame.MERGE_PANEL);
            currentJob.setCurrentJob(SplitFrame.MERGE_PANEL);
        }
    }
}
