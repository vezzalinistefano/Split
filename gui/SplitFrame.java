package gui;

import gui.panels.JobsPanel;
import gui.panels.MidPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {
    private Container cp;

    private JobsPanel jobsPanel;
    private MidPanel midPanel;

    public SplitFrame() {
        this.setTitle("Split");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jobsPanel = new JobsPanel();

        this.add(jobsPanel, BorderLayout.PAGE_START);

        midPanel = new MidPanel();

        this.add(midPanel, BorderLayout.CENTER);
        this.pack();

    }
}
