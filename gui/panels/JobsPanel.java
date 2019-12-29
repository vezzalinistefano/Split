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
    private JButton divideBtn, stitchBtn;
    private CardLayout cardLayout;

    public JobsPanel(MainPanel currentJob, CardLayout cardLayout) {
        super();
        this.setLayout(new BorderLayout());
        this.currentJob = currentJob;
        this.cardLayout = cardLayout;

        jobsBar = new JToolBar();
        jobsBar.setFloatable(false);

        divideBtn = new JButton("Dividi");
        stitchBtn = new JButton("Unisci");

        divideBtn.addActionListener(this);
        stitchBtn.addActionListener(this);

        jobsBar.add(divideBtn);
        jobsBar.add(stitchBtn);

        this.add(jobsBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Dividi") && currentJob.getCurrentJob().equals("Unisci")) {
            cardLayout.show(currentJob, SplitFrame.DIVIDE_PANEL);
            currentJob.setCurrentJob("Dividi");
        } else if (e.getActionCommand().equals("Unisci") && currentJob.getCurrentJob().equals("Dividi")) {
            cardLayout.show(currentJob, SplitFrame.MERGE_PANEL);
            currentJob.setCurrentJob("Unisci");
        }
    }
}
