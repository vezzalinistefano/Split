package gui;

import gui.panels.JobsPanel;
import gui.panels.SettingsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il frame contenente il programma
 */
public class SplitFrame extends JFrame {
    protected final JobsPanel jobsPanel;
    protected final SettingsPanel settingsPanel;

    public SplitFrame () {
        this.setTitle("Split");

        Container cp = getContentPane();

        // this.add(Box.createVerticalStrut(4));

        jobsPanel = new JobsPanel();
        settingsPanel = new SettingsPanel();

        cp.add(jobsPanel);
        cp.add(settingsPanel);

        this.setVisible(true);
    }
}
