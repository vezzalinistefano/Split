package gui.panels;

import javax.swing.*;

public class MainPanel extends JPanel {
    private String currentJob;

    public MainPanel(String curr) {
        super();
        this.currentJob = curr;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }
}
